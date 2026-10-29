package edu.fpdual.webservice.controller;

import edu.fpdual.webservice.model.persistence.dao.Profile;
import edu.fpdual.webservice.model.persistence.manager.impl.ProfileManagerImpl;
import edu.fpdual.webservice.service.ProfileService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/profile")
public class ProfileController {
    private final ProfileService profileService;

    //@TODO Añadir Javadoc
    public ProfileController() {
        this.profileService = new ProfileService(new ProfileManagerImpl());
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
            return Response.ok().entity(profileService.findAllProfiles()).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/id/{userId}")
    public Response findByUserId(@PathParam("userId") int userId) {
        try {
            if (userId == 0) {
                return Response.ok().entity(profileService.findAllProfiles()).build();
            } else {
                if (profileService.findByUserId(userId).getUserId() <= 0) {
                    return Response.ok().entity(null).build();
                } else {
                    return Response.ok().entity(profileService.findByUserId(userId)).build();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/email/{email}")
    public Response findByEmail(@PathParam("email") String email) {
        try {
            if (email == null) {
                return Response.ok().entity(profileService.findAllProfiles()).build();
            } else {
                if (profileService.findByEmail(email).getUserId() <= 0) {
                    return Response.ok().entity(null).build();
                } else {
                    return Response.ok().entity(profileService.findByEmail(email)).build();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProfile(Profile profile) {
        try {
            Profile profileToUpdate = profileService.findByUserId(profile.getUserId());
            if (profileToUpdate != null && profileToUpdate.getUserId() > 0) {
                return Response.status(201).entity(profileService.updateProfile(profile)).build();
            } else {
                return Response.status(400).entity(false).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProfile(Profile profile) {
        try {
            if (profileService.findByUserId(profile.getUserId()) != null) {
                return Response.ok().entity(profileService.createProfile(profile)).build();
            } else {
                return Response.status(400).entity(false).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction.").build();
        }
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProfile(Profile profile) {
        try {
            if (profileService.findByUserId(profile.getUserId()) != null) {
                if (profileService.deleteProfile(profile)) {
                    return Response.status(200).entity(profile).build();
                } else {
                    return Response.status(400).entity("Profile Was Not Deleted.").build();
                }
            } else {
                return Response.status(400).entity("Profile Not Found.").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }
}
