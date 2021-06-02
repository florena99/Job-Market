package ro.sd.a2.service.strategy;

import com.itextpdf.text.DocumentException;
import ro.sd.a2.model.entity.CV;

import java.io.File;
import java.io.FileNotFoundException;

public interface CvStrategy {

    void generate(CV cv) throws FileNotFoundException, DocumentException;
}
