package pl.javowe.swirki.zzpjapp.model.forumModel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import pl.javowe.swirki.zzpjapp.model.User;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@Table (name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @NotEmpty
    @Size(max = 500)
    @Column(name = "body", nullable = false)
    private String body;

    //@ManyToOne
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.PERSIST)
    @JoinColumn(name = "thread_id")
    @JsonBackReference
    private Thread thread;

    @Column(name = "user_rating", nullable = false)
    private int userRating = 0;

    @PastOrPresent
    @Column(name = "creation_date", nullable = false)
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
