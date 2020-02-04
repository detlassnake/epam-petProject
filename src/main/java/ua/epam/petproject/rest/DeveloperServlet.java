package ua.epam.petproject.rest;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.petproject.model.Developer;
import ua.epam.petproject.service.AccountService;
import ua.epam.petproject.service.DeveloperService;
import ua.epam.petproject.service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DeveloperServlet", urlPatterns = "/api/v1/developers")
public class DeveloperServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private DeveloperService developerService = new DeveloperService();
    private AccountService accountService = new AccountService();
    private SkillService skillService = new SkillService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        logger.debug("DeveloperServlet->Get");
        String id = req.getParameter("id");
        if (id == null) {
            List<Developer> developers = developerService.read();
            if (developers == null) {
                resp.sendError(404);
            } else {
                for (Developer developer : developers) {
                    writer.println(gson.toJson(developer));
                }
            }
        } else {
            long developerId = Long.parseLong(id);
            Developer developer = developerService.readById(developerId);
            writer.println(gson.toJson(developer));
        }
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("DeveloperServlet->Post");
        Developer developer = gson.fromJson(req.getReader(), Developer.class);
        developer.setDeveloperSkillsSet(skillService.create(developer.getDeveloperSkillsSet()));
        developer.setDeveloperAccount(accountService.create(developer.getDeveloperAccount()));
        developerService.create(developer);
        resp.sendRedirect("/api/v1/developers");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("DeveloperServlet->Put");
        Developer developer = gson.fromJson(req.getReader(), Developer.class);
        Developer developerWithId = developerService.readById(developer.getId());
        developerService.update(developerWithId.getId(), developer);
        accountService.update(developerWithId.getDeveloperAccount().getId(), developer.getDeveloperAccount());
        skillService.update(developerWithId.getDeveloperSkillsSet(), developer.getDeveloperSkillsSet());
        resp.sendRedirect("/api/v1/developers");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("DeveloperServlet->Delete");
        long id = Long.parseLong(req.getParameter("id"));
        Developer developerWithId = developerService.readById(id);
        developerService.delete(id);
        accountService.delete(developerWithId.getDeveloperAccount().getId());
        resp.sendRedirect("/api/v1/developers");
    }
}