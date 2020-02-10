package ua.epam.petprojectTest.serviceTest;

import com.google.gson.Gson;
import org.junit.Test;
import ua.epam.petproject.model.Account;
import ua.epam.petproject.service.AccountService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AccountServiceTest {
    AccountService accountService = new AccountService();
    Gson gson = new Gson();

    private void accountClear(Account result) {
        accountService.delete(result.getId());
    }

    @Test
    public void createTest() {
        String accountGson = "{ \"account\": \"accounttt@gmail.com\", \"accountStatus\": \"ACTIVE\" }";
        Account account = gson.fromJson(accountGson, Account.class);
        Account result = accountService.create(account);
        assertEquals(account.getAccount(),result.getAccount());
        accountClear(result);
    }

    @Test
    public void readTest() {
        List<Account> accountList = accountService.read();
        assertNotNull(accountList);
    }

    @Test
    public void readByIdTest() {
        Account result = accountService.readById(123456L);
        assertNull(result.getAccount());
    }

    @Test
    public void updateTest() {
        String accountGson1 = "{ \"account\": \"accounttter@gmail.com\", \"accountStatus\": \"ACTIVE\" }";
        String accountGson2 = "{ \"account\": \"accounttterser@gmail.com\", \"accountStatus\": \"ACTIVE\" }";
        Account account1 = gson.fromJson(accountGson1, Account.class);
        Account account2 = gson.fromJson(accountGson2, Account.class);
        Account result = accountService.create(account1);
        accountService.update(result.getId(), account2);
        assertEquals(accountService.readById(result.getId()).getAccount(), account2.getAccount());
        accountClear(result);
    }

    @Test
    public void deleteTest() {
        String accountGson = "{ \"account\": \"accountttler@gmail.com\", \"accountStatus\": \"ACTIVE\" }";
        Account account = gson.fromJson(accountGson, Account.class);

        Account result = accountService.create(account);
        List<Account> AccountListBefore = accountService.read();
        accountClear(result);
        List<Account> AccountListAfter = accountService.read();
        assertNotEquals(AccountListBefore, AccountListAfter);
    }
}
