package ua.epam.petproject.rest;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.petproject.model.Account;
import ua.epam.petproject.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AccountServlet", urlPatterns = "/api/v1/accounts")
public class AccountServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private AccountService accountService = new AccountService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        logger.debug("AccountServlet->Get");
        String id = req.getParameter("id");
        if (id == null) {
            List<Account> accountList = accountService.read();
            if (accountList == null) {
                resp.sendError(404);
            } else {
                for (int i = 0; i < accountList.size(); i++) {
                    Account account = accountList.get(i);
                    writer.println(gson.toJson(account));
                }
            }
        } else {
            long accountId = Long.parseLong(id);
            Account account = accountService.readById(accountId);
            writer.println(gson.toJson(account));
        }
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("AccountServlet->Post");
        Account account = gson.fromJson(req.getReader(), Account.class);
        accountService.create(account);
        resp.sendRedirect("/api/v1/accounts");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("AccountServlet->Put");
        Account account = gson.fromJson(req.getReader(), Account.class);
        accountService.update(account.getId(), account);
        resp.sendRedirect("/api/v1/accounts");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("AccountServlet->Delete");
        accountService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("/api/v1/accounts");
    }
}
