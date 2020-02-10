package ua.epam.petprojectTest.jdbcTest;

import org.junit.Before;
import org.junit.Test;
import ua.epam.petproject.model.Account;
import ua.epam.petproject.model.AccountStatus;
import ua.epam.petproject.model.Developer;
import ua.epam.petproject.repository.jdbc.JdbcAccountRepository;
import ua.epam.petproject.repository.jdbc.JdbcDeveloperRepository;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcDeveloperRepositoryTest {
    Developer developer1;
    Account account1;
    JdbcDeveloperRepository jdbcDeveloperRepository = new JdbcDeveloperRepository();
    JdbcAccountRepository jdbcAccountRepository = new JdbcAccountRepository();

    private void developerClear(Developer result) {
        jdbcDeveloperRepository.deleteById(result.getId());
        jdbcAccountRepository.deleteById(result.getDeveloperAccount().getId());
    }

    @Before
    public void setUp() {
        account1 = new Account();
        account1.setAccount("newtestaccount11@gmail.com");
        account1.setAccountStatus(AccountStatus.ACTIVE);
        developer1 = new Developer();
        developer1.setName("testName1");
    }

    @Test
    public void saveTest() {
        setUp();
        developer1.setDeveloperAccount(jdbcAccountRepository.save(account1));
        Developer result = jdbcDeveloperRepository.save(developer1);
        assertEquals(developer1.getName(),result.getName());
        developerClear(result);
    }

    @Test
    public void getAllTest() {
        List<Developer> developerList = jdbcDeveloperRepository.getAll();
        assertNotNull(developerList);
    }

    @Test
    public void getByIdTest() {
        Developer result = jdbcDeveloperRepository.getById(123456789L);
        assertNull(result.getName());
    }

    @Test
    public void deleteByIdTest() {
        setUp();
        developer1.setDeveloperAccount(jdbcAccountRepository.save(account1));
        Developer result = jdbcDeveloperRepository.save(developer1);
        List<Developer> DeveloperListBefore = jdbcDeveloperRepository.getAll();
        developerClear(result);
        List<Developer> DeveloperListAfter = jdbcDeveloperRepository.getAll();
        assertNotEquals(DeveloperListBefore, DeveloperListAfter);
    }
}
