package edu.fpdual.webapplication.service;

import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.client.ReportClient;
import edu.fpdual.webapplication.dto.Report;

import java.util.List;

@Model(type = "Service", version = "1.0", date = "01/06/2023")
public class ReportService {

    private final ReportClient reportClient;


    public ReportService(ReportClient reportClient) {
        this.reportClient = reportClient;
    }

    /**
     * Proves if the service is available.
     *
     * @return the following string : "Service online"
     */
    public String ping() {
        return reportClient.ping();
    }

    public List<Report> getAllReports() {
        return reportClient.get();
    }

    public Report getReport(String reportName) {
        return reportClient.get(reportName);
    }

    public boolean updateReport(Report report) {
        return reportClient.put(report);
    }

    public boolean createReport(Report report) {
        return reportClient.post(report);
    }

    public boolean deleteReport(Report report) {
        return reportClient.delete(report);
    }
}
