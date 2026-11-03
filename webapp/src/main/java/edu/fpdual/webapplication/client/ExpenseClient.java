package edu.fpdual.webapplication.client;

import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.dto.Expense;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Model(type = "Client", version = "1.0", date = "01/06/2023")
public class ExpenseClient extends Client<Expense> {
    private final WebTarget webTarget;
    private final String clientPath = "expense/";

    public ExpenseClient() {
        jakarta.ws.rs.client.Client client = ClientBuilder.newClient();
        this.webTarget = client.target(GlobalInfo.URL_WEBTARGET);
    }

    @Override
    public String ping() {
        return webTarget.path(clientPath + "ping")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
    }

    @Override
    public List<Expense> get() {
        return webTarget.path(clientPath + "all")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {
                });
    }

    @Override
    public Expense get(String expenseName) {
        return webTarget.path(clientPath + "name/" + expenseName)
                .request(MediaType.APPLICATION_JSON)
                .get(Expense.class);
    }

    public Expense getById(int expenseId) {
        return webTarget.path(clientPath + "id/" + expenseId)
                .request(MediaType.APPLICATION_JSON)
                .get(Expense.class);
    }

    @Override
    public boolean put(Expense expense) {
        return webTarget.path(clientPath + "update")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(expense, MediaType.APPLICATION_JSON), boolean.class);
    }

    @Override
    public boolean post(Expense expense) {
        return webTarget.path(clientPath + "create")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(expense, MediaType.APPLICATION_JSON), boolean.class);
    }

    @Override
    public boolean delete(Expense expense) {
        return webTarget.path(clientPath + "delete")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(expense, MediaType.APPLICATION_JSON), boolean.class);
    }
}
