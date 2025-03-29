package database.model;

public class Course {
    private int course_id;
    private int student_id;//学生id
    private float grade;//成绩
    Course(int course_id, int student_id, float grade) {
        this.course_id = course_id;
        this.student_id = student_id;
        this.grade = grade;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
