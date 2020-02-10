package ua.epam.petprojectTest.jdbcTest;

import org.junit.Before;
import org.junit.Test;
import ua.epam.petproject.model.Account;
import ua.epam.petproject.model.AccountStatus;
import ua.epam.petproject.repository.jdbc.JdbcAccountRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JdbcAccountRepositoryTest {
    Account account1;
    Account account2;
    JdbcAccountRepository jdbcAccountRepository = new JdbcAccountRepository();

    private void accountClear(Account result) {
        jdbcAccountRepository.deleteById(result.getId());
    }

    @Before
    public void setUp() {
        account1 = new Account();
        account1.setAccount("testaccount1@gmail.com");
        account1.setAccountStatus(AccountStatus.ACTIVE);
        account2 = new Account();
        account2.setAccount("testaccount2@gmail.com");
        account2.setAccountStatus(AccountStatus.ACTIVE);
    }

    @Test
    public void saveTest() {
        setUp();
        Account result = jdbcAccountRepository.save(account1);
        assertEquals(account1.getAccount(),result.getAccount());
        accountClear(result);
    }

    @Test
    public void getAllTest() {
        List<Account> accountList = jdbcAccountRepository.getAll();
        assertNotNull(accountList);
    }

    @Test
    public void getByIdTest() {
        Account result = jdbcAccountRepository.getById(123456L);
        assertNull(result.getAccount());
    }

    @Test
    public void updateTest() {
        setUp();
        Account result = jdbcAccountRepository.save(account1);
        jdbcAccountRepository.update(result.getId(), account2);
        assertEquals(jdbcAccountRepository.getById(result.getId()).getAccount(), account2.getAccount());
        accountClear(result);
    }

    @Test
    public void deleteByIdTest() {
        setUp();
        Account result = jdbcAccountRepository.save(account1);
        List<Account> AccountListBefore = jdbcAccountRepository.getAll();
        accountClear(result);
        List<Account> AccountListAfter = jdbcAccountRepository.getAll();
        assertNotEquals(AccountListBefore, AccountListAfter);
    }
}
