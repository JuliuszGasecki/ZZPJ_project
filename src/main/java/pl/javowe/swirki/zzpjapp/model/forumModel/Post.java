package pl.javowe.swirki.zzpjapp.model.forumModel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import pl.javowe.swirki.zzpjapp.model.User;
import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@Table (name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @Column(name = "body")
    private String body;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.PERSIST)
    @JoinColumn(name = "thread")
    @JsonBackReference
    private Thread thread;

    @Column(name = "userRating")
    private int userRating = 0;

    @Column(name = "creationDate")
    private Date creationDate;

    public Post()
    {

    }

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
