package ua.epam.petproject.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtilLogic {
    private static final Logger logger = LoggerFactory.getLogger(JdbcUtilLogic.class);

    public static void writeToDB(String sql, long id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Jdbc Util Logic->SQLException");
            e.printStackTrace();
        }
    }

    public static void writeToDB(String sql1, String sql2, long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Jdbc Util Logic->SQLException");
            e.printStackTrace();
        }
    }
}