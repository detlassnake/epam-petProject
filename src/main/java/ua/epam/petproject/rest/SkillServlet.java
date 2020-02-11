package ua.epam.petproject.rest;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.petproject.model.Skill;
import ua.epam.petproject.service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SkillServlet", urlPatterns = "/api/v1/skills")
public class SkillServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SkillServlet.class);
    private SkillService skillService = new SkillService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        logger.debug("SkillServlet->Get");
        String id = req.getParameter("id");
        if (id == null) {
            List<Skill> skillList = skillService.read();
            if (skillList == null) {
                resp.sendError(404);
            } else {
                writer.println(gson.toJson(skillList));
            }
        }
        else {
            long skillId = Long.parseLong(id);
            Skill skill = skillService.readById(skillId);
            writer.println(gson.toJson(skill));
        }
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("SkillServlet->Post");
        Skill skill = gson.fromJson(req.getReader(), Skill.class);
        skillService.create(skill);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("SkillServlet->Put");
        Skill skill = gson.fromJson(req.getReader(), Skill.class);
        skillService.update(skill.getId(), skill);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("SkillServlet->Delete");
        if (req.getParameter("id") == null){
            resp.sendError(400, "Invalid parameter id");
        } else {
            skillService.delete(Long.parseLong(req.getParameter("id")));
        }
    }
}