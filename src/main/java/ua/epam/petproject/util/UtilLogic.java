package ua.epam.petproject.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.petproject.model.AccountStatus;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class UtilLogic {
    private static final Logger logger = LoggerFactory.getLogger(UtilLogic.class);

    public static ArrayList<String> getProperties () {
        logger.debug("UtilLogic->Get properties");
        ArrayList<String> applicationPropertiesArrayList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("src/main/resources/liquibase/liquibase.properties")) {
            Properties property = new Properties();
            property.load(fis);
            applicationPropertiesArrayList.add(property.getProperty("url"));
            applicationPropertiesArrayList.add(property.getProperty("username"));
            applicationPropertiesArrayList.add(property.getProperty("password"));
        } catch (IOException e) {
            logger.error("UtilLogic getProperties->IOException");
            e.printStackTrace();
        }
        return applicationPropertiesArrayList;
    }

    public static AccountStatus accountStatusCheck(String AccountStatusStr) {
        if (AccountStatus.ACTIVE.toString().equals(AccountStatusStr))
            return AccountStatus.ACTIVE;
        else if (AccountStatus.BANNED.toString().equals(AccountStatusStr))
            return AccountStatus.BANNED;
        else if (AccountStatus.DELETED.toString().equals(AccountStatusStr))
            return AccountStatus.DELETED;
        return null;
    }
}
