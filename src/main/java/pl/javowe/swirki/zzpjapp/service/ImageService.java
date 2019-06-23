package pl.javowe.swirki.zzpjapp.service;

import java.io.IOException;

public interface ImageService {
    public byte[] saveImageToByte(String filename) throws IOException;
}
