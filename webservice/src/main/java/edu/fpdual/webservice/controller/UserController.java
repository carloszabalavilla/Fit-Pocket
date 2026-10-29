package edu.fpdual.webservice.controller;

import edu.fpdual.webservice.model.persistence.dao.User;
import edu.fpdual.webservice.model.persistence.manager.impl.UserManagerImpl;
import edu.fpdual.webservice.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/user")
public class UserController {
    private final UserService userService;

    //@TODO Añadir Javadoc
    public UserController() {
        this.userService = new UserService(new UserManagerImpl());
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
            return Response.ok().entity(userService.findAllUsers()).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/id/{user_id}")
    public Response findByUserId(@PathParam("user_id") int userId) {
        try {
            if (userService.findByUserId(userId).getUserId() <= 0) {
                return Response.status(404).entity("User Not Found").build();
            }
            return Response.ok().entity(userService.findByUserId(userId)).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{user_name}")
    public Response findByUserName(@PathParam("user_name") String userName) {
        try {
            if (userName == null) {
                return Response.ok().entity(userService.findAllUsers()).build();
            } else {
                User user = userService.findByUserName(userName);
                if (user == null || user.getUserId() <= 0) {
                    return Response.ok().entity(null).build();
                }
                return Response.ok().entity(user).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/password/{user_password}")
    public Response findByUserPassword(@PathParam("user_password") String userPassword) {
        try {
            if (userService.findByUserPassword(userPassword).getUserId() <= 0) {
                return Response.status(400).entity("User Not Found").build();
            }
            return Response.ok().entity(userService.findByUserPassword(userPassword)).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    /*
         @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/password/{user_attribute}")
        public Response findBy(@PathParam("user_attribute") String userAttribute) {
            try {
                if (isInteger(userAttribute)) {
                    int userId = Integer.parseInt(userAttribute);
                    if (userService.findByUserPassword(userId).getUserId() <= 0) {
                        return Response.status(400).entity("User Not Found").build();
                    }
                    return Response.ok().entity(userService.findByUserPassword(userId)).build();
                } else {
                    if (userService.findByUserName(userAttribute).getUserId() <= 0) {
                        return Response.status(400).entity("User Not Found").build();
                    }
                    return Response.ok().entity(userService.findByUserName(userAttribute)).build();
                }
            } catch (SQLException | ClassNotFoundException e) {
                return Response.status(500).entity("Internal Error During DB Interaction").build();
            }
        }

        private boolean isInteger(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    */
    /*
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response findByUserName(@QueryParam("user_name") String userName) {
            try {
                if (userName == null) {
                    return Response.ok().entity(userService.findAllUsers()).build();
                } else {
                    User user = userService.findByUserName(userName);
                    if (user == null || user.getUserId() <= 0) {
                        return Response.status(404).entity("User Not Found").build();
                    }
                    return Response.ok().entity(user).build();
                }
            } catch (SQLException | ClassNotFoundException e) {
                return Response.status(500).entity("Internal Error During DB Interaction").build();
            }
        }
     */
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        try {
            User userToUpdate = userService.findByUserName(user.getUserName());
            if (userToUpdate != null) {
                return Response.status(200).entity(userService.updateUser(user)).build();
            } else {
                return Response.status(400).entity("User Not Found").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        try {
            User userToFind = userService.findByUserName(user.getUserName());
            if (userToFind == null || user.getUserId() <= 0) {
                return Response.ok().entity(userService.createUser(user)).build();
            } else {
                return Response.status(400).entity("User already exists.").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction.").build();
        }
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(User user) {
        try {
            if (userService.findByUserName(user.getUserName()) != null) {
                return Response.status(200).entity(userService.deleteUser(user)).build();
            } else {
                return Response.status(400).entity("User Not Found.").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }
}
