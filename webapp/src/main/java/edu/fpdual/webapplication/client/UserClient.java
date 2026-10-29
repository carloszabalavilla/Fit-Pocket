package edu.fpdual.webapplication.client;

import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.dto.User;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Model(type = "Client", version = "1.0", date = "01/06/2023")
public class UserClient extends Client<User> {
    private final WebTarget webTarget;
    private final String clientPath = "user/";

    public UserClient() {
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
    public List<User> get() {
        return webTarget.path(clientPath + "all")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {
                });
    }

    public User get(int userId) {
        return webTarget.path(clientPath + "id/" + userId)
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
    }

    @Override
    public User get(String userName) {
        return webTarget.path(clientPath + "name/" + userName)
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
    }

    public User getPassword(String userPassword) {
        return webTarget.path(clientPath + "password/" + userPassword)
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
    }

    /*
        public User get(String userName) {
            try {
                URI uri = UriBuilder.fromPath(clientPath)
                        .queryParam("user_name", userName)
                        .build();

                return webTarget
                        .path(uri.toString())
                        .request(MediaType.APPLICATION_JSON)
                        .get(User.class);
            } catch (BadRequestException e) {
                // Controlar el caso cuando se recibe un status 400
                return null;
            }
        }
    */
    @Override
    public boolean put(User user) {
        return webTarget.path(clientPath + "update")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(user, MediaType.APPLICATION_JSON), boolean.class);
    }

    @Override
    public boolean post(User user) {
        return webTarget.path(clientPath + "create")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON), boolean.class);
    }

    @Override
    public boolean delete(User user) {
        return webTarget.path(clientPath + "delete")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON), boolean.class);
    }
}
