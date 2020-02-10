package ua.epam.petprojectTest.serviceTest;

import com.google.gson.Gson;
import org.junit.Test;
import ua.epam.petproject.model.Account;
import ua.epam.petproject.model.Skill;
import ua.epam.petproject.service.SkillService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class SkillServiceTest {
    SkillService skillService = new SkillService();
    Gson gson = new Gson();

    private void skillClear(Skill result) {
        skillService.delete(result.getId());
    }

    @Test
    public void createTest() {
        String skillGson = "{ \"skill\" : \"Oak\" }";
        Skill skill = gson.fromJson(skillGson, Skill.class);
        Skill result = skillService.create(skill);
        assertEquals(result.getSkill(), skill.getSkill());
        skillClear(result);
    }

    @Test
    public void readTest() {
        List<Skill> skillList = skillService.read();
        assertNotNull(skillList);
    }

    @Test
    public void readByIdTest() {
        Skill result = skillService.readById(123456L);
        assertNull(result.getSkill());
    }

    @Test
    public void updateTest() {
        String skillGson1 = "{ \"skill\" : \"Oak\" }";
        String skillGson2 = "{ \"skill\" : \"jawa\" }";
        Skill skill1 = gson.fromJson(skillGson1, Skill.class);
        Skill skill2 = gson.fromJson(skillGson2, Skill.class);

        Skill result = skillService.create(skill1);
        skillService.update(result.getId(), skill2);
        assertEquals(skillService.readById(result.getId()).getSkill(), skill2.getSkill());
        skillClear(result);
    }

    @Test
    public void deleteTest() {
        String skillGson = "{ \"skill\" : \"Oak\" }";
        Skill skill = gson.fromJson(skillGson, Skill.class);
        Skill result = skillService.create(skill);
        List<Skill> skillListBefore = skillService.read();
        skillClear(result);
        List<Skill> skillListAfter = skillService.read();
        assertNotEquals(skillListBefore, skillListAfter);
    }
}
