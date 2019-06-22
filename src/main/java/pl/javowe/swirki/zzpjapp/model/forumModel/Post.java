package pl.javowe.swirki.zzpjapp.model.forumModel;

import pl.javowe.swirki.zzpjapp.model.User;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private User author;

    private String body;

    private ArrayList<Post> anserws;

    public Post(User author, String body) {
        this.author = author;
        this.body = body;
    }
}
