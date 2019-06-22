package pl.javowe.swirki.zzpjapp.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class ImageService {

    public ImageService() {}

    public byte[] saveImageToByte(String filename) throws IOException {
        BufferedImage bImage = ImageIO.read(new File("UsersImages/" + filename + ".jpg"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        byte[] data = bos.toByteArray();
        return data;
    }
}
