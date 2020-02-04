package ua.epam.petproject.util;

public class JdbcQueryStorageUtil {
    //JdbcSkillQuery
    public static String sqlCreateSkill1 = "INSERT INTO skills(skill_name) VALUES (?);";
    public static String sqlCreateSkill2 = "SELECT * FROM skills WHERE skill_name = ?;";
    public static String sqlReadSkill = "SELECT * FROM skills;";
    public static String sqlReadByIdSkill = "SELECT * FROM skills WHERE id = ";
    public static String sqlUpdateSkill = "UPDATE skills SET skill_name = ? WHERE id = ?;";
    public static String sqlDeleteSkill = "DELETE FROM skills WHERE id = ?;";

    //JdbcAccountQuery
    public static String sqlCreateAccount1 = "INSERT INTO accounts (account_name, account_status) VALUES (?, ?);";
    public static String sqlCreateAccount2 = "SELECT * FROM accounts WHERE account_name = ?;";
    public static String sqlReadAccount = "SELECT * from accounts;";
    public static String sqlReadByIdAccount = "SELECT * from accounts WHERE id = ";
    public static String sqlUpdateAccount = "UPDATE accounts SET account_name = ?, account_status = ? WHERE id = ?;";
    public static String sqlDeleteAccount = "DELETE FROM accounts WHERE id = ?;";

    //JdbcDeveloperQuery
    public static String sqlCreateDeveloper1 = "INSERT INTO developers(developer_name, account_id) VALUES (?, ?);";
    public static String sqlCreateDeveloper2 = "INSERT INTO developers_skills(developer_id, skill_id) VALUES (?, ?);";
    public static String sqlCreateDeveloper3 = "SELECT * FROM developers WHERE developer_name = ?;";
    public static String sqlReadDeveloper = "SELECT d.id, d.developer_name, a.id, a.account_name, a.account_status, s.id, s.skill_name\n" +
                 "FROM developers d\n" +
                 "         JOIN developers_skills ds ON d.id = ds.developer_id\n" +
                 "         JOIN skills s ON ds.skill_id = s.id\n" +
                 "         JOIN accounts a ON d.account_id = a.id;";
    public static String sqlReadByIdDeveloper = "SELECT d.id, d.developer_name, a.id, a.account_name, a.account_status, s.id, s.skill_name\n" +
                 "FROM developers d\n" +
                 "         JOIN developers_skills ds ON d.id = ds.developer_id\n" +
                 "         JOIN skills s ON ds.skill_id = s.id\n" +
                 "         JOIN accounts a ON d.account_id = a.id \n" +
                 "WHERE d.id = ";
    public static String sqlUpdateDeveloper = "UPDATE developers SET developer_name = ?  WHERE id = ?;";
    public static String sqlDeleteDeveloper1 = "DELETE FROM developers_skills WHERE developer_id = ?;";
    public static String sqlDeleteDeveloper2 = "DELETE FROM developers WHERE id = ?;";
}
