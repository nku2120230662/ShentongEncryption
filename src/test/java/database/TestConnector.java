package database;

import database.config.Connector;
import org.junit.Test;

public class TestConnector {
    @Test
    public void testConnector() {
        Connector connector = new Connector();
        connector.closeConnection();
    }
}
