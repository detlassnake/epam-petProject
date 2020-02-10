package ua.epam.petprojectTest.serviceTest;

import com.google.gson.Gson;
import org.junit.Test;
import ua.epam.petproject.model.Developer;
import ua.epam.petproject.service.AccountService;
import ua.epam.petproject.service.DeveloperService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DeveloperServiceTest {
    DeveloperService developerService = new DeveloperService();
    AccountService accountService = new AccountService();
    Gson gson = new Gson();

    private void developerClear(Developer result) {
        developerService.delete(result.getId());
        accountService.delete(result.getDeveloperAccount().getId());
    }

    @Test
    public void createTest() {
        String developerGson = "{\n" +
                "    \"developerSkills\": [],\n" +
                "    \"developerAccount\": {\n" +
                "        \"account\": \"jojo@gmail.com\",\n" +
                "        \"accountStatus\": \"ACTIVE\"\n" +
                "    },\n" +
                "    \"name\": \"gogos\"\n" +
                "}";
        Developer developer = gson.fromJson(developerGson, Developer.class);
        developer.setDeveloperAccount(accountService.create(developer.getDeveloperAccount()));
        Developer result = developerService.create(developer);
        assertEquals(developer.getName(),result.getName());
        developerClear(result);
    }

    @Test
    public void readTest() {
        List<Developer> developerList = developerService.read();
        assertNotNull(developerList);
    }

    @Test
    public void readByIdTest() {
        Developer result = developerService.readById(123456789L);
        assertNull(result.getName());
    }

    @Test
    public void deleteByIdTest() {
        String developerGson = "{\n" +
                "    \"developerSkills\": [],\n" +
                "    \"developerAccount\": {\n" +
                "        \"account\": \"jojo@gmail.com\",\n" +
                "        \"accountStatus\": \"ACTIVE\"\n" +
                "    },\n" +
                "    \"name\": \"gogos\"\n" +
                "}";
        Developer developer = gson.fromJson(developerGson, Developer.class);
        developer.setDeveloperAccount(accountService.create(developer.getDeveloperAccount()));
        Developer result = developerService.create(developer);
        List<Developer> DeveloperListBefore = developerService.read();
        developerClear(result);
        List<Developer> DeveloperListAfter = developerService.read();
        assertNotEquals(DeveloperListBefore, DeveloperListAfter);
    }
}
