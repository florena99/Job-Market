package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.model.entity.CV;
import ro.sd.a2.model.entity.Education;
import ro.sd.a2.service.repository.EduRepository;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EduService {

    @Autowired
    private EduRepository eduRepository;

    public Education save(Education education){

        eduRepository.save(education);

        return education;
    }

    public Education findEducations(CV cv){

        return eduRepository.findByCv(cv);
    }

    public void delete(CV cv){
        eduRepository.deleteByCv(cv);
    }

    public void edit(CV cv, Education newEducation){
        Education education= findEducations(cv);
        education.setName(newEducation.getName());
        education.setStartDate(newEducation.getStartDate());
        education.setEndDate(newEducation.getEndDate());
        save(education);
        cv.setEducation(education);
    }
}


//    MimeMessage msg = new MimeMessage(session);
//        try {
//                msg.setSubject(subject);
//                msg.setFrom("johndoe@example.com");
//                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, false));
//                msg.setContent("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
//                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
//                "<head>\n" +
//                "    <title>Learning how to code an HTML email</title>\n" +
//                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
//                "    <meta name=\"viewport\" content=\"width=device-width\" />\n" +
//                "    <style>\n" +
//                "        @media only screen and (max-width:590px){\n" +
//                "            .c1{\n" +
//                "                background-color:white !important;\n" +
//                "            }\n" +
//                "            .c3a,\n" +
//                "            .c3b{\n" +
//                "                width:100% !important;\n" +
//                "            }\n" +
//                "        }\n" +
//                "    </style>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<center>\n" +
//                "    <!-- Email template container-->\n" +
//                "    <table border=\"0\" cellpadding=\"0\" height=\"100\" width=\"100%\">\n" +
//                "        <tr>\n" +
//                "            <td align=\"center\" valign=\"top\" class=\"email-container\">\n" +
//                "\n" +
//                "                <!-- Email content -->\n" +
//                "                <table style=\"background-color: blanchedalmond\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"580\">\n" +
//                "\n" +
//                "                    <!-- Header -->\n" +
//                "                    <tr>\n" +
//                "                        <td align=\"center\" valign=\"middle\" class=\"header\" >\n" +
//                "                            <p style=\"background-color: #f6a192\">BestJobs</p>\n" +
//                "                        </td>\n" +
//                "                    </tr>\n" +
//                "\n" +
//                "                    <!-- Content -->\n" +
//                "                    <tr>\n" +
//                "                        <td align=left\" valign=\"top\" class=\"content\">\n" +
//                "                            <p>Multumim pentru inregistrarea pe siteul nostru.</p>\n" +
//                "\n" +
//                "                            <!-- Button as a separate table -->\n" +
//                "                            <table style=\"background-color: blanchedalmond\" border=\"0\" cellpadding=\"0\" width=\"335\" class=\"button-block\">\n" +
//                "                                <tr>\n" +
//                "                                    <td align=center valign=middle class=\"button\">\n" +
//                "                                      <p style=\"color: darksalmon\" >  Pentru alte informatii si probleme ne puteti contacta la mailul urmator : johndoe@example.com .</p>\n" +
//                "                                    </td>\n" +
//                "                                </tr>\n" +
//                "                            </table>\n" +
//                "\n" +
//                "                        </td>\n" +
//                "                    </tr>\n" +
//                "\n" +
//                "                    <!-- Footer -->\n" +
//                "                    <tr>\n" +
//                "                        <td align=\"center\" valign=\"middle\" class=\"footer\">\n" +
//                "                            <p>Cu drag, echipa BestJob.</p>\n" +
//                "                        </td>\n" +
//                "                    </tr>\n" +
//                "\n" +
//                "                </table>\n" +
//                "\n" +
//                "            </td>\n" +
//                "        </tr>\n" +
//                "    </table>\n" +
//                "</center>\n" +
//                "</body>\n" +
//                "\n" +
//                "</html>\n" +
//                "</body>", "text/html; charset=utf-8");