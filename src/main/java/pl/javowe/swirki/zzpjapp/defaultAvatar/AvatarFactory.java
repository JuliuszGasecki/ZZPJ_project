package pl.javowe.swirki.zzpjapp.defaultAvatar;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class AvatarFactory {

    private final String url = "https://avatars.dicebear.com/v2/identicon/";

    private RestTemplateBuilder restTemplate = new RestTemplateBuilder();

    public byte[] getDefaultAvatar(Long ID) {

        URL requestURL = generateURL(ID);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
            HttpEntity<String> entity = new HttpEntity<>(headers);

            assert requestURL != null : "URL is null";
            ResponseEntity<byte[]> response = restTemplate.build()
                    .exchange(requestURL.toString(), HttpMethod.GET, entity, byte[].class);

            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private URL generateURL(Long ID) {
        try {
            return new URL(url + ID.toString() + ".svg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;

    }
}
