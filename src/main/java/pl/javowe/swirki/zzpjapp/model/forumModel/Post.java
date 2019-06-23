package pl.javowe.swirki.zzpjapp.model.forumModel;

import lombok.Data;
import pl.javowe.swirki.zzpjapp.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //primary key generated with TopLink
    private long id;

    @ManyToOne
    private User author;

    private String body;

    @ManyToOne
    private Thread thread;

    private int userRating = 0;

    private Date creationDate;

    public Post(User author, String body) {
        this.author = author;
        this.body = body;
        this.creationDate = Calendar.getInstance().getTime();
    }

    public Post(Post e) {
        this.author = e.author;
        this.body = e.body;
        this.creationDate = e.creationDate;

    }
}
