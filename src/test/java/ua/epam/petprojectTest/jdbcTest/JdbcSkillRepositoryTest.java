package ua.epam.petprojectTest.jdbcTest;

import org.junit.Before;
import org.junit.Test;
import ua.epam.petproject.model.Skill;
import ua.epam.petproject.repository.jdbc.JdbcSkillRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JdbcSkillRepositoryTest {
    Skill skill1;
    Skill skill2;
    JdbcSkillRepository jdbcSkillRepository = new JdbcSkillRepository();

    private void skillClear(Skill result) {
        jdbcSkillRepository.deleteById(result.getId());
    }

    @Before
    public void setUp() {
        skill1 = new Skill();
        skill1.setSkill("testSkill1");
        skill2 = new Skill();
        skill2.setSkill("testSkill2");
    }

    @Test
    public void saveTest() {
        setUp();
        Skill result = jdbcSkillRepository.save(skill1);
        assertEquals(skill1.getSkill(), result.getSkill());
        skillClear(result);
    }

    @Test
    public void getAllTest() {
        List<Skill> skillList = jdbcSkillRepository.getAll();
        assertNotNull(skillList);
    }

    @Test
    public void getByIdTest() {
        Skill result = jdbcSkillRepository.getById(123456L);
        assertNull(result.getSkill());
    }

    @Test
    public void updateTest() {
        setUp();
        Skill result = jdbcSkillRepository.save(skill1);
        jdbcSkillRepository.update(result.getId(), skill2);
        assertEquals(jdbcSkillRepository.getById(result.getId()).getSkill(), skill2.getSkill());
        skillClear(result);
    }

    @Test
    public void deleteByIdTest() {
        setUp();
        Skill result = jdbcSkillRepository.save(skill1);
        List<Skill> skillListBefore = jdbcSkillRepository.getAll();
        skillClear(result);
        List<Skill> skillListAfter = jdbcSkillRepository.getAll();
        assertNotEquals(skillListBefore, skillListAfter);
    }
}
