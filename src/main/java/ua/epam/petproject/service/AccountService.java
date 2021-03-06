package ua.epam.petproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.petproject.model.Account;
import ua.epam.petproject.repository.AccountRepository;
import ua.epam.petproject.repository.jdbc.JdbcAccountRepository;

import java.util.ArrayList;

public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private AccountRepository accountRepository;

    public AccountService() {
        accountRepository = new JdbcAccountRepository();
    }

    public Account create(Account account) {
        logger.debug("Account service->Create");
        return accountRepository.save(account);
    }

    public ArrayList<Account> read() {
        logger.debug("Account service->Read");
        return accountRepository.getAll();
    }

    public Account readById(long id) {
        logger.debug("Account service->Read by id");
        return accountRepository.getById(id);
    }

    public void update(long id, Account account) {
        logger.debug("Account service->Edit by id");
        accountRepository.update(id, account);
    }

    public void delete(long id) {
        logger.debug("Account service->Delete");
        accountRepository.deleteById(id);
    }
}
