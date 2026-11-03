package edu.fpdual.webapplication.utilities.reportpdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.fpdual.webapplication.servlet.dto.ExpenseIncome;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ReportToPdf {

    /**
     * Create a new pdf file based on the report selected
     *
     * @param list the list of all incomes and expenses.
     * @return a {@link String}
     */
    public String createPDF(ArrayList<ExpenseIncome> list) throws IOException, DocumentException {
        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        String filePath = desktopPath + pdfName() + ".pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath + ".pdf"));
        document.open();

        Paragraph paragraph = createParagraph();
        PdfPTable pdfPTable = createTable();
        addTableHeaders(pdfPTable);
        addTableCustomRows(pdfPTable, list);

        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        document.add(pdfPTable);
        document.close();

        return "Se ha creado un reporte en el escritorio.";
    }

    private String pdfName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestampString = now.format(formatter);
        return "Reporte_" + timestampString;
    }

    private Paragraph createParagraph() {
        String text = "Fit-Pocket Report";

        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 16, BaseColor.BLACK);
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(10f);
        return paragraph;
    }

    private PdfPTable createTable() {
        PdfPTable pdfPTable = new PdfPTable(4);
        pdfPTable.setSpacingBefore(10f);
        return pdfPTable;
    }

    private void addTableHeaders(PdfPTable pdfPTable) {
        Stream.of("Fecha", "Gasto o Ingreso", "Cantidad", "Categoria").forEach(nombreColumna -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(nombreColumna));
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(header);
        });
    }

    private void addTableCustomRows(PdfPTable pdfPTable, ArrayList<ExpenseIncome> list) {
        for (ExpenseIncome exin : list) {
            PdfPCell colum1 = new PdfPCell(new Phrase(exin.getFecha()));
            colum1.setBackgroundColor(BaseColor.WHITE);
            colum1.setBorderWidth(1);
            colum1.setHorizontalAlignment(Element.ALIGN_CENTER);
            colum1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(colum1);

            PdfPCell column2 = new PdfPCell(new Phrase(exin.getTipo()));
            column2.setBackgroundColor(BaseColor.WHITE);
            column2.setBorderWidth(1);
            column2.setHorizontalAlignment(Element.ALIGN_CENTER);
            column2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(column2);

            PdfPCell column3 = new PdfPCell(new Phrase(exin.getCantidad()));
            column3.setBackgroundColor(BaseColor.WHITE);
            column3.setBorderWidth(1);
            column3.setHorizontalAlignment(Element.ALIGN_CENTER);
            column3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(column3);

            PdfPCell column4 = new PdfPCell(new Phrase(exin.getCategoria()));
            column4.setBackgroundColor(BaseColor.WHITE);
            column4.setBorderWidth(1);
            column4.setHorizontalAlignment(Element.ALIGN_CENTER);
            column4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(column4);
        }
    }
}
