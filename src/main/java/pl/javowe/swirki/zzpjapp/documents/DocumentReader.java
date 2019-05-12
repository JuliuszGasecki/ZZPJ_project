package pl.javowe.swirki.zzpjapp.documents;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class DocumentReader {

    public static XWPFDocument readTemplateCV(File file) throws IOException {
        return new XWPFDocument(new FileInputStream(file.getAbsolutePath()));
    }

}
