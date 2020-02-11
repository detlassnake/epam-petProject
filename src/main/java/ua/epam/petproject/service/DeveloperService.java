package ua.epam.petproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.petproject.model.Developer;
import ua.epam.petproject.repository.DeveloperRepository;
import ua.epam.petproject.repository.jdbc.JdbcDeveloperRepository;

import java.util.ArrayList;

public class DeveloperService {
    private static final Logger logger = LoggerFactory.getLogger(DeveloperService.class);
    private DeveloperRepository developerRepository;

    public DeveloperService() {
        developerRepository = new JdbcDeveloperRepository();
    }

    public Developer create(Developer developer) {
        logger.debug("Developer service->Create");
        return developerRepository.save(developer);
    }

    public ArrayList<Developer> read() {
        logger.debug("Developer service->Read");
        return developerRepository.getAll();
    }

    public Developer readById(long id) {
        logger.debug("Developer service->Read by id");
        return developerRepository.getById(id);
    }

    public void update(long id, Developer developer) {
        logger.debug("Developer service->Edit by id");
        developerRepository.update(id, developer);
    }

    public void delete(long id) {
        logger.debug("Developer service->Delete");
        developerRepository.deleteById(id);
    }
}
