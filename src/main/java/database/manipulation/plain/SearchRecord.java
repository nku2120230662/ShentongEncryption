package database.manipulation.plain;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchRecord {

    // 查询表student的全部字段
    public static boolean SearchStudents(Connection conn) throws Exception{
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("select * from students");
        // 展开结果集数据库
        while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print("\n");
            }
            stmt.close();
        return true;
    }

    // 查询表student的全部字段
    public static boolean SearchStudentsJoinCourses(Connection conn) throws Exception{
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("select * from students JOIN courses on students.id = courses.s_id");
        // 展开结果集数据库
        while (rs.next()) {
            // 通过字段检索
            int id = rs.getInt("id");

            // 输出数据
            System.out.println("ID: " + id);
//            System.out.println(rs.getMetaData());
        }
        stmt.close();
        return true;
    }

    public static boolean MyPlainJoinQueries(Connection conn, String... condition) throws Exception {
        // 检查条件是否为空
        if (condition.length < 3) {
            throw new IllegalArgumentException("condition should contain at least table names and join condition.");
        }

        String tableA = condition[0]; // 表A的名字
        String tableB = condition[1]; // 表B的名字
        String joinCondition = condition[2]; // 连接条件，类似 "id" 或 "student_id = course_id"

        // 1. 查询tableA
        // 将筛选条件直接构造sql语句
        String queryA = "SELECT * FROM " + tableA;
        Statement stmt = conn.createStatement();
        ResultSet rsA = stmt.executeQuery(queryA);
        List<Map<String, Object>> resultA = convertResultSetToList(rsA);
        String filterA = condition.length > 3 ? condition[3] : "";  // 例如：student_id > 10
        if(!filterA.isEmpty()){
            resultA=FilterCondition(resultA,filterA);
        }

        // 2. 查询tableB
        String queryB = "SELECT * FROM " + tableB ;
        ResultSet rsB = stmt.executeQuery(queryB);
        List<Map<String, Object>> resultB = convertResultSetToList(rsB);
        String filterB = condition.length > 4 ? condition[4] : "";  // 例如：course_name = 'Math'
        if(!filterB.isEmpty()){
            resultB=FilterCondition(resultB,filterB);
        }

        // 3. 在内存中进行连接操作（使用嵌套循环的方式实现 JOIN）
        List<Map<String, Object>> joinResult = new ArrayList<>();

        // 假设joinCondition为相等条件 "id"
        String[] conditionParts = joinCondition.split("=");
        if (conditionParts.length != 2) {
            throw new IllegalArgumentException("Invalid join condition. Expected format 'columnA = columnB'.");
        }

        String columnA = conditionParts[0].split("\\.")[1];  // 表A中的连接列
        String columnB = conditionParts[1].split("\\.")[1];  // 表B中的连接列

        // 内存中嵌套循环进行连接
        for (Map<String, Object> rowA : resultA) {
            for (Map<String, Object> rowB : resultB) {
                // 如果连接列相等，则加入连接结果
                if (MatchRecord(rowA,rowB,columnA,columnB)){
                    Map<String, Object> joinedRow = new HashMap<>();
                    joinedRow.putAll(rowA);  // 将表A的所有列加入结果
                    joinedRow.putAll(rowB);  // 将表B的所有列加入结果
                    joinResult.add(joinedRow);  // 将连接后的行加入到结果集
                }
            }
        }

        // 打印连接后的结果（可以根据需要改成返回或者处理结果）
        printJoinResult(joinResult);
        stmt.close();

        return !joinResult.isEmpty();
    }

    private static List<Map<String, Object>> FilterCondition(List<Map<String, Object>> resultA, String filterA) {
        // 初始化结果列表
        List<Map<String, Object>> result = new ArrayList<>();
        String[] parts = filterA.split(" ");
        String attr = parts[0].trim();
        String condition = parts[2].trim();
        // 判断是否是in查询(attr=val 或 attr In [val1, val2, ...])
        if(filterA.contains("=")){
            for (Map<String, Object> row : resultA) {
                if (MatchValue(row, attr, condition)) {
                    result.add(row);
                }
            }
        }else {
            String[] inValues = condition.substring(1, condition.length() - 1).split(",");
            for (Map<String, Object> row : resultA) {
                if (MatchInValues(row, attr, inValues)) {
                    // 多项式查询
                    result.add(row);
                }
            }
        }
        return result;
    }

    // 判断是否为等值匹配
    private static boolean MatchValue(Map<String, Object> row, String attr, String val) {
        return row.containsKey(attr) && val.equals(String.valueOf(row.get(attr)));
    }

    // 判断是否满足 In 范围条件
    private static boolean MatchInValues(Map<String, Object> row, String attr, String[] inValues) {
        if (!row.containsKey(attr)) {
            return false;
        }
        String value = String.valueOf(row.get(attr));
        for (String inVal : inValues) {
            if (value.equals(inVal.trim())) {
                return true;
            }
        }
        return false;
    }

    private static boolean MatchRecord(Map<String, Object> rowA, Map<String, Object> rowB, String columnA, String columnB) {
        Object valueA = rowA.get(columnA);
        Object valueB = rowB.get(columnB);
        return valueA.equals(valueB);
    }

    // 将ResultSet转化为List<Map<String, Object>>（每一行作为一个Map）
    private static List<Map<String, Object>> convertResultSetToList(ResultSet rs) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList<>();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnName(i), rs.getObject(i));
            }
            resultList.add(row);
        }

        return resultList;
    }

    // 打印连接后的结果
    private static void printJoinResult(List<Map<String, Object>> joinResult) {
        if (joinResult.isEmpty()) {
            System.out.println("No matching records found.");
            return;
        }

        // 打印列名
        Map<String, Object> firstRow = joinResult.get(0);
        for (String columnName : firstRow.keySet()) {
            System.out.print(columnName + "\t");
        }
        System.out.println();

        // 打印每一行
        for (Map<String, Object> row : joinResult) {
            for (Object value : row.values()) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}