package ua.epam.petproject.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.petproject.mapper.ObjectMapper;
import ua.epam.petproject.model.Skill;
import ua.epam.petproject.repository.SkillRepository;
import ua.epam.petproject.util.ConnectionUtil;
import ua.epam.petproject.util.JdbcQueryStorageUtil;
import ua.epam.petproject.util.JdbcUtilLogic;

import java.sql.*;
import java.util.ArrayList;

public class JdbcSkillRepository implements SkillRepository {
    private static final Logger logger = LoggerFactory.getLogger(JdbcSkillRepository.class);

    public Skill save(Skill skill) {
        logger.debug("JdbcSkillRepository->Save");
        return skillWriteToDB(JdbcQueryStorageUtil.sqlCreateSkill1, JdbcQueryStorageUtil.sqlCreateSkill2, skill);
    }

    public ArrayList<Skill> getAll() {
        logger.debug("JdbcSkillRepository->Get All");
        return skillReadFromDB(JdbcQueryStorageUtil.sqlReadSkill);
    }

    public Skill getById(Long id) {
        Skill skill = new Skill();
        try {
            logger.debug("JdbcSkillRepository->Get By Id");
            skill = skillReadFromDB(JdbcQueryStorageUtil.sqlReadByIdSkill + id + ";").get(0);
        } catch (IndexOutOfBoundsException e) {
            logger.warn("JdbcSkillRepository->Id not found");
        }
        return skill;
    }

    public void update(Long id, Skill skill) {
        logger.debug("JdbcSkillRepository->Update");
        skillWriteToDB(JdbcQueryStorageUtil.sqlUpdateSkill, skill, id);
    }

    public void deleteById(Long id) {
        logger.debug("JdbcSkillRepository->Delete By Id");
        JdbcUtilLogic.writeToDB(JdbcQueryStorageUtil.sqlDeleteSkill, id);
    }

    private Skill skillWriteToDB(String sql1, String sql2, Skill skill) {
        ArrayList<Skill> skillArrayList = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
                preparedStatement.setString(1, skill.getSkill());
                preparedStatement.executeUpdate();
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
                preparedStatement.setString(1, skill.getSkill());
                ResultSet resultSet = preparedStatement.executeQuery();
                skillArrayList = ObjectMapper.mapToSkill(resultSet);
            }
        } catch (SQLException e) {
            logger.error("JdbcSkillRepository skillWriteToDB->SQLException");
            e.printStackTrace();
        }
        assert skillArrayList != null;
        return skillArrayList.get(0);
    }

    private void skillWriteToDB(String sql, Skill skill, long id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, skill.getSkill());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("JdbcSkillRepository skillWriteToDB->SQLException");
            e.printStackTrace();
        }
    }

    private ArrayList<Skill> skillReadFromDB(String sql) {
        ArrayList<Skill> skillArrayList = null;
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            skillArrayList = ObjectMapper.mapToSkill(resultSet);
        } catch (SQLException e) {
            logger.error("JdbcSkillRepository skillReadFromDB->SQLException");
            e.printStackTrace();
        }
        return skillArrayList;
    }
}

