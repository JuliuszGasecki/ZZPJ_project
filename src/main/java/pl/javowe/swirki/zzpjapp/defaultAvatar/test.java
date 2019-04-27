package pl.javowe.swirki.zzpjapp.defaultAvatar;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController
public class test {
    @RequestMapping("/avatartest")
    public byte[] index(){

        Random random = new Random();
        AvatarFactory avatarFactory = new AvatarFactory();
        
        return avatarFactory.getDefaultAvatar(random.nextLong());

    }
}
