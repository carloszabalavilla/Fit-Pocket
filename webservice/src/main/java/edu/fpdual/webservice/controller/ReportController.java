package edu.fpdual.webservice.controller;

import edu.fpdual.webservice.model.persistence.dao.Report;
import edu.fpdual.webservice.model.persistence.manager.impl.ReportManagerImpl;
import edu.fpdual.webservice.service.ReportService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/report")
public class ReportController {
    private final ReportService reportService;

    //@TODO Añadir Javadoc
    public ReportController() {
        this.reportService = new ReportService(new ReportManagerImpl());
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
            return Response.ok().entity(reportService.findAllReports()).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{reportName}")
    public Response findByReportName(@PathParam("reportName") String reportName) {
        try {
            if (reportName == null) {
                return Response.ok().entity(reportService.findAllReports()).build();
            } else {
                if (reportService.findByReportName(reportName).getUserId() <= 0) {
                    return Response.status(400).entity("Report Not Found").build();
                } else {
                    return Response.ok().entity(reportService.findByReportName(reportName)).build();
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
    public Response updateReport(Report report) {
        try {
            Report reportToUpdate = reportService.findByReportName(report.getReportName());
            if (reportToUpdate != null && reportToUpdate.getUserId() > 0) {
                return Response.status(201).entity(reportService.findByReportName(report.getReportName())).build();
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
    public Response createReport(Report report) {
        try {
            if (reportService.findByReportName(report.getReportName()) != null) {
                if (reportService.createReport(report)) {
                    return Response.status(200).entity("Profile created.").build();
                } else {
                    return Response.status(400).entity("Profile not created.").build();
                }
            } else {
                return Response.status(400).entity("Profile already exists.").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction.").build();
        }
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteReport(Report report) {
        try {
            if (reportService.findByReportName(report.getReportName()) != null) {
                if (reportService.deleteReport(report)) {
                    return Response.status(200).entity(report).build();
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
