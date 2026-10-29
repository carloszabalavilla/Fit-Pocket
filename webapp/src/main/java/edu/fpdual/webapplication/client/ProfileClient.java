package edu.fpdual.webapplication.client;

import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.dto.Profile;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Model(type = "Client", version = "1.0", date = "01/06/2023")
public class ProfileClient extends Client<Profile> {
    private final WebTarget webTarget;
    private final String clientPath = "profile/";

    public ProfileClient() {
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
    public List<Profile> get() {
        return webTarget.path(clientPath + "all")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {
                });
    }

    public Profile get(int userId) {
        return webTarget.path(clientPath + "id/" + userId)
                .request(MediaType.APPLICATION_JSON)
                .get(Profile.class);
    }

    @Override
    public Profile get(String email) {
        return webTarget.path(clientPath + "email/" + email)
                .request(MediaType.APPLICATION_JSON)
                .get(Profile.class);
    }

    @Override
    public boolean put(Profile profile) {
        return webTarget.path(clientPath + "update")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(profile, MediaType.APPLICATION_JSON), boolean.class);
    }

    @Override
    public boolean post(Profile profile) {
        return webTarget.path(clientPath + "create")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(profile, MediaType.APPLICATION_JSON), boolean.class);
    }

    @Override
    public boolean delete(Profile profile) {
        return webTarget.path(clientPath + "delete")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(profile, MediaType.APPLICATION_JSON), boolean.class);
    }
}
