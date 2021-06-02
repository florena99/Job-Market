package ro.sd.a2.service.strategy;

import com.itextpdf.text.DocumentException;
import ro.sd.a2.model.entity.CV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CVCSV implements CvStrategy {
        public static String convertToCSV(CV cv) {
            StringBuilder sb = new StringBuilder();
            sb.append("\t"+cv.getId());
            sb.append("\t"+cv.getFirstName());
            sb.append("\t"+cv.getLastName());
            sb.append("\t"+cv.getAddress());
            sb.append("\t"+cv.getPhone());
            sb.append("\t"+cv.getEmail());
            sb.append("\t"+cv.getBirthdate().toString());


            return sb.toString();
        }

    @Override
    public void generate(CV cv) throws FileNotFoundException, DocumentException {
        File csvOutputFile = new File("CV"+ cv.getUsers().getId()+".csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println("\tYOUR CV");
            pw.println("\tPersonal information");
            pw.println("\tID\tFirst Name\tLast Name\tAddress\tPhone\tMail\tBirthdate");
            pw.println(convertToCSV(cv));
            pw.println("Education Name\t"+cv.getEducation().getName());
            pw.println("Start date\t" + cv.getEducation().getStartDate().toString());
            pw.println("End date\t" + cv.getEducation().getEndDate().toString());
            pw.close();
            pw.flush();
        }

    }
}

