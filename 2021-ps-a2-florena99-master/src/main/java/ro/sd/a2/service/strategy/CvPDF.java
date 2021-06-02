package ro.sd.a2.service.strategy;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ro.sd.a2.model.entity.CV;
import ro.sd.a2.model.entity.Education;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

public class CvPDF implements CvStrategy {


    public void generate(CV cv) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("CV"+cv.getUsers().getId()+".pdf"));
        document.open();
        PdfPTable table = new PdfPTable(6);

        PdfPTable table2 = new PdfPTable(3);


        Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLACK);
        Paragraph p=new Paragraph("CV",font);
        p.setSpacingAfter(20);
        p.setAlignment(1);
        addTableHeader(table);
        addRows(table,cv);

        addTableHeader2(table2);
        addRows2(table2,cv.getEducation());

        document.add(p);
        document.add(table);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(table2);

        document.close();
    }

    private void addRows(PdfPTable table, CV cv) {




        PdfPCell cell1 = new PdfPCell(new Phrase(cv.getFirstName()));
        cell1.setBackgroundColor(BaseColor.PINK);
        cell1.setBorderWidth(3);
        cell1.setBorderColor(BaseColor.DARK_GRAY);
        table.addCell(cell1);


        PdfPCell cell2 = new PdfPCell(new Phrase(cv.getLastName()));
        cell2.setBackgroundColor(BaseColor.PINK);
        cell2.setBorderWidth(3);
        cell2.setBorderColor(BaseColor.DARK_GRAY);
        table.addCell(cell2);


        PdfPCell cell3 = new PdfPCell(new Phrase(cv.getEmail()));
        cell3.setBackgroundColor(BaseColor.PINK);
        cell3.setBorderWidth(3);
        cell3.setBorderColor(BaseColor.DARK_GRAY);
        table.addCell(cell3);


        PdfPCell cell4 = new PdfPCell(new Phrase(cv.getPhone()));
        cell4.setBackgroundColor(BaseColor.PINK);
        cell4.setBorderWidth(3);
        cell4.setBorderColor(BaseColor.DARK_GRAY);
        table.addCell(cell4);

        PdfPCell cell5 = new PdfPCell(new Phrase(cv.getBirthdate().toString()));
        cell5.setBackgroundColor(BaseColor.PINK);
        cell5.setBorderWidth(3);
        cell5.setBorderColor(BaseColor.DARK_GRAY);
        table.addCell(cell5);


        PdfPCell cell6 = new PdfPCell(new Phrase(cv.getAddress()));
        cell6.setBackgroundColor(BaseColor.PINK);
        cell6.setBorderWidth(3);
        cell6.setBorderColor(BaseColor.DARK_GRAY);
        table.addCell(cell6);





    }
    private void addRows2(PdfPTable table, Education education) {
            PdfPCell cell1 = new PdfPCell(new Phrase(education.getName()));
            cell1.setBackgroundColor(BaseColor.PINK);
            cell1.setBorderWidth(3);
            cell1.setBorderColor(BaseColor.DARK_GRAY);
            table.addCell(cell1);


            PdfPCell cell2 = new PdfPCell(new Phrase(education.getStartDate().toString()));
            cell2.setBackgroundColor(BaseColor.PINK);
            cell2.setBorderWidth(3);
            cell2.setBorderColor(BaseColor.DARK_GRAY);
            table.addCell(cell2);



            PdfPCell cell3 = new PdfPCell(new Phrase(education.getEndDate().toString()));
            cell3.setBackgroundColor(BaseColor.PINK);
            cell3.setBorderWidth(3);
            cell3.setBorderColor(BaseColor.DARK_GRAY);
            table.addCell(cell3);


    }
    private void addTableHeader(PdfPTable table) {
        Stream.of("First Name", "Last Name", "Mail","Phone", "Birthdate", "Address").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.PINK);
            header.setBorderWidth(3);
            header.setBorderColor(BaseColor.DARK_GRAY);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });

    }

    private void addTableHeader2(PdfPTable table) {
        Stream.of("Name", "Start Date", "End Date").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.PINK);
            header.setBorderWidth(3);
            header.setBorderColor(BaseColor.DARK_GRAY);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });

    }



}
