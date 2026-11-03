package edu.fpdual.webservice.controller;

import edu.fpdual.webservice.model.persistence.dao.Category;
import edu.fpdual.webservice.model.persistence.manager.impl.CategoryManagerImpl;
import edu.fpdual.webservice.service.CategoryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/category")
public class CategoryController {
    private final CategoryService categoryService;

    //@TODO Añadir Javadoc
    public CategoryController() {
        this.categoryService = new CategoryService(new CategoryManagerImpl());
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
            return Response.ok().entity(categoryService.findAllCategories()).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/allbyid/{userId}")
    public Response findAllByUserId(@PathParam("userId") int userId) {
        try {
            return Response.ok().entity(categoryService.findAllCategoriesByUserId(userId)).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{category}")
    public Response findByCategoryName(@PathParam("category") String category) {
        try {
            if (category == null) {
                return Response.ok().entity(categoryService.findAllCategories()).build();
            } else {
                if (categoryService.findByCategoryName(category).getCategoryId() < 0) {
                    return Response.status(404).entity("Category Not Found.").build();
                }
                return Response.ok().entity(categoryService.findByCategoryName(category)).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction.").build();
        }
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategory(Category category) {
        try {
            if (categoryService.findByCategoryName(category.getCategoryName()) == null) {
                if (categoryService.updateCategory(category)) {
                    return Response.ok().entity("Category Updated.").build();
                } else {
                    return Response.status(400).entity("Category Was Not Updated.").build();
                }
            } else {
                return Response.status(400).entity("Category Not Found.").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction.").build();
        }
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(Category category) {
        try {
            if (categoryService.findByCategoryName(category.getCategoryName()) != null) {
                if (categoryService.createCategory(category)) {
                    return Response.status(200).entity("Category Created.").build();
                } else {
                    return Response.status(400).entity("Category Was Not Created.").build();
                }
            } else {
                return Response.status(400).entity("Category Already Exists.").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction.").build();
        }
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCategory(Category category) {
        try {
            if (findByCategoryName(category.getCategoryName()) == null) {
                if (categoryService.deleteCategory(category)) {
                    return Response.status(200).entity("Category Deleted.").build();
                } else {
                    return Response.status(400).entity("Category Was Not Deleted.").build();
                }
            } else {
                return Response.status(400).entity("Category Not Found.").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction.").build();
        }
    }
}
