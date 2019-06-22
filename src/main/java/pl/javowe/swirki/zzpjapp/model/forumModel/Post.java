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
    private Long id;

    @OneToOne
    private User author;

    private String body;

    private int userRating = 0;

    private Date creationDate;

    private ArrayList<Post> anserws;

    public Post(User author, String body) {
        this.author = author;
        this.body = body;
        this.creationDate = Calendar.getInstance().getTime();
    }

    public Post() {
    }
}
