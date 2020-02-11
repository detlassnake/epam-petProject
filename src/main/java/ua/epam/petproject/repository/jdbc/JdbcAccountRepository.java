package ua.epam.petproject.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.petproject.mapper.ObjectMapper;
import ua.epam.petproject.model.Account;
import ua.epam.petproject.repository.AccountRepository;
import ua.epam.petproject.util.ConnectionUtil;
import ua.epam.petproject.util.JdbcQueryStorageUtil;
import ua.epam.petproject.util.JdbcUtilLogic;

import java.sql.*;
import java.util.ArrayList;

public class JdbcAccountRepository implements AccountRepository {
    private static final Logger logger = LoggerFactory.getLogger(JdbcAccountRepository.class);

    public Account save(Account account) {
        logger.debug("JdbcAccountRepository->Save");
        return accountWriteToDB(JdbcQueryStorageUtil.sqlCreateAccount1, JdbcQueryStorageUtil.sqlCreateAccount2, account);
    }

    public ArrayList<Account> getAll() {
        logger.debug("JdbcAccountRepository->Get All");
        return accountReadFromDB(JdbcQueryStorageUtil.sqlReadAccount);
    }

    public Account getById(Long id) {
        Account account = new Account();
        try {
            logger.debug("JdbcAccountRepository->Get By Id");
            account = accountReadFromDB(JdbcQueryStorageUtil.sqlReadByIdAccount + id + ";").get(0);
        } catch (IndexOutOfBoundsException e) {
            logger.warn("JdbcAccountRepository->Id not found");
        }
        return account;
    }

    public void update(Long id, Account account) {
        logger.debug("JdbcAccountRepository->Update");
        accountWriteToDB(JdbcQueryStorageUtil.sqlUpdateAccount, account, id);
    }

    public void deleteById(Long id) {
        logger.debug("JdbcAccountRepository->Delete By Id");
        JdbcUtilLogic.writeToDB(JdbcQueryStorageUtil.sqlDeleteAccount, id);
    }

    private Account accountWriteToDB(String sql1, String sql2, Account account) {
        ArrayList<Account> accountArrayList = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
                preparedStatement.setString(1, account.getAccount());
                preparedStatement.setString(2, account.getAccountStatus().toString());
                preparedStatement.executeUpdate();
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
                preparedStatement.setString(1, account.getAccount());
                ResultSet resultSet = preparedStatement.executeQuery();
                accountArrayList = ObjectMapper.mapToAccount(resultSet);
            }
        } catch (SQLException e) {
            logger.error("JdbcAccountRepository accountWriteToDB->SQLException");
            e.printStackTrace();
        }
        assert accountArrayList != null;
        return accountArrayList.get(0);
    }

    private void accountWriteToDB(String sql, Account account, long id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, account.getAccount());
            preparedStatement.setString(2, account.getAccountStatus().toString());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("JdbcAccountRepository accountWriteToDB->SQLException");
            e.printStackTrace();
        }
    }

    private ArrayList<Account> accountReadFromDB(String sql) {
        ArrayList<Account> accountArrayList = null;
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            accountArrayList = ObjectMapper.mapToAccount(resultSet);
        } catch (SQLException e) {
            logger.error("JdbcAccountRepository accountReadFromDB->SQLException");
            e.printStackTrace();
        }
        return accountArrayList;
    }
}
