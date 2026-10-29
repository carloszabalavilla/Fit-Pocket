package edu.fpdual.webapplication.client;

import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.dto.Category;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Model(type = "Client", version = "1.0", date = "01/06/2023")
public class CategoryClient extends Client<Category> {
    private final WebTarget webTarget;
    private final String clientPath = "category/";

    public CategoryClient() {
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
    public List<Category> get() {
        return webTarget.path(clientPath + "all")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {
                });
    }

    public List<Category> getAllBy(int userId) {
        return webTarget.path(clientPath + "allbyid/" + userId)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {
                });
    }

    @Override
    public Category get(String categoryName) {
        return webTarget.path(clientPath + "name/" + categoryName)
                .request(MediaType.APPLICATION_JSON)
                .get(Category.class);
    }

    @Override
    public boolean put(Category category) {
        return webTarget.path(clientPath + "update")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(category, MediaType.APPLICATION_JSON), boolean.class);
    }

    @Override
    public boolean post(Category category) {
        return webTarget.path(clientPath + "create")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(category, MediaType.APPLICATION_JSON), boolean.class);
    }

    @Override
    public boolean delete(Category category) {
        return webTarget.path(clientPath + "delete")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(category, MediaType.APPLICATION_JSON), boolean.class);
    }
}
