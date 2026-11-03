package edu.fpdual.webapplication.client;

import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.dto.Currency;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Model(type = "Client", version = "1.0", date = "01/06/2023")
public class CurrencyClient extends Client<Currency> {
    private final WebTarget webTarget;
    private final String clientPath = "currency/";

    public CurrencyClient() {
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
    public List<Currency> get() {
        return
                webTarget.path(clientPath + "all")
                        .request(MediaType.APPLICATION_JSON)
                        .get(new GenericType<>() {
                        });
    }

    @Override
    public Currency get(String currencyName) {
        return webTarget.path(clientPath + "name/" + currencyName)
                .request(MediaType.APPLICATION_JSON)
                .get(Currency.class);
    }

    @Override
    public boolean put(Currency currency) {
        return webTarget.path(clientPath + "update")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(currency, MediaType.APPLICATION_JSON), boolean.class);
    }

    @Override
    public boolean post(Currency currency) {
        return webTarget.path(clientPath + "create")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(currency, MediaType.APPLICATION_JSON), boolean.class);
    }

    @Override
    public boolean delete(Currency currency) {
        return webTarget.path(clientPath + "delete")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(currency, MediaType.APPLICATION_JSON), boolean.class);
    }

    public boolean ok(Currency currency) {
        Response response = webTarget.path(clientPath + "delete")
                .request(MediaType.APPLICATION_JSON)
                .delete();

        int statusCode = response.getStatus();
        response.close();

        return statusCode == 204; // Código 204 significa "No Content" y se interpreta como éxito en una operación de borrado.
    }
}
