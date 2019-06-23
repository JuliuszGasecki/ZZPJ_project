package pl.javowe.swirki.zzpjapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javowe.swirki.zzpjapp.documents.CreatorCV;
import pl.javowe.swirki.zzpjapp.model.User;

import java.io.IOException;

@RestController
public class HelloWorld {

    @RequestMapping("/hello")
    public String hello(){
     /*   CreatorCV creatorCV = new CreatorCV();
        try {
            creatorCV.createCV(new User());
        } catch (IOException e) {
            System.out.println("lol");
            e.printStackTrace();
        }*/
        return "HelloWorld";
    }

    @RequestMapping("/")
    public String index(){
      /*  CreatorCV creatorCV = new CreatorCV();
        try {
            creatorCV.createCV(new User());
        } catch (IOException e) {
            System.out.println("lol");
            e.printStackTrace();
        }*/
        return "HelloWorld";
    }
}
