package ua.epam.petproject.util;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);
    private static BasicDataSource ds = new BasicDataSource();
    static {

        ds.setUrl(UtilLogic.getProperties().get(0));
        ds.setUsername(UtilLogic.getProperties().get(1));
        ds.setPassword(UtilLogic.getProperties().get(2));
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        logger.debug("ConnectionUtil->Get connection");
        return ds.getConnection();
    }

    private ConnectionUtil() {
    }
}
