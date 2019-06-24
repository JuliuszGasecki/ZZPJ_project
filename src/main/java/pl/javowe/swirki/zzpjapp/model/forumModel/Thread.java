package pl.javowe.swirki.zzpjapp.model.forumModel;

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
@Table(name = "thread")
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "thread_id", nullable = false)
    private long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "author_id")
    private User author;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 255)
    @Column(name = "description", nullable = false)
    private String description;

    @PastOrPresent
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "is_closed", nullable = false)
    private boolean isClosed = false;

    @Column (name = "user_rating", nullable = false)
    private int userRating = 0;

    public Thread(User author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.creationDate = Calendar.getInstance().getTime();
    }

    public Thread() {
    }
}
