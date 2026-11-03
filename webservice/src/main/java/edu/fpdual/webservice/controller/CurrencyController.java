package edu.fpdual.webservice.controller;


import edu.fpdual.webservice.model.persistence.dao.Currency;
import edu.fpdual.webservice.model.persistence.manager.impl.CurrencyManagerImpl;
import edu.fpdual.webservice.service.CurrencyService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController() {
        this.currencyService = new CurrencyService(new CurrencyManagerImpl());
    }

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service online").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response findAll() {
        try {
            return Response.ok().entity(currencyService.findAllCurrencies()).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{currency}")
    public Response findByCurrencyName(@PathParam("currency") String currency) {
        try {
            if (currency == null) {
                return Response.ok().entity(currencyService.findAllCurrencies()).build();
            } else {
                if (currencyService.findByCurrencyName(currency).getCurrencyId() < 0) {
                    return Response.status(404).entity("Currency Not Found.").build();
                }
                return Response.ok().entity(currencyService.findByCurrencyName(currency)).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction.").build();
        }
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCurrency(Currency currency) {
        try {
            Currency currencyToUpdate = currencyService.findByCurrencyName(currency.getCurrencyName());
            if (currencyToUpdate != null) {
                if (currencyService.updateCurrency(currency)) {
                    return Response.status(200).entity(currencyService.findByCurrencyName(currency.getCurrencyName())).build();
                } else {
                    return Response.status(400).entity("Internal Error During Budget Update").build();
                }
            } else {
                return Response.status(400).entity("Budget Not Found").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(400).entity("Internal Error During DB Interaction").build();
        }
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCurrency(Currency currency) {
        try {
            if (currencyService.findByCurrencyName(currency.getCurrencyName()) != null) {
                if (currencyService.createCurrency(currency)) {
                    return Response.status(200).entity("Currency Created.").build();
                } else {
                    return Response.status(400).entity("Currency Was Not Created.").build();
                }
            } else {
                return Response.status(400).entity("Currency Already Exists.").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction.").build();
        }
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCurrency(Currency currency) {
        try {
            if (findByCurrencyName(currency.getCurrencyName()) == null) {
                if (currencyService.deleteCurrency(currency)) {
                    return Response.status(200).entity("Currency Deleted.").build();
                } else {
                    return Response.status(400).entity("Currency Was Not Deleted.").build();
                }
            } else {
                return Response.status(400).entity("Currency Not Found.").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction.").build();
        }
    }
}
