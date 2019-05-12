package pl.javowe.swirki.zzpjapp.documents;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.core.io.ClassPathResource;
import pl.javowe.swirki.zzpjapp.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CreatorCV {

    public static final String fileNameTemplate = "src/main/resources/static/template.docx";

    public static void createCV(User user) throws IOException {
        File fileTemplate = new File(fileNameTemplate);
        XWPFDocument document= DocumentReader.readTemplateCV(fileTemplate);
        if(document != null){
            List<XWPFParagraph> paragraphs = document.getParagraphs();


            for (XWPFParagraph para : paragraphs) {
                System.out.println(para.getText());
            }
            System.out.println("done");
        }
    }

}
