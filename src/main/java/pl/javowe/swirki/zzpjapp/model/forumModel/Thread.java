package pl.javowe.swirki.zzpjapp.model.forumModel;

import lombok.Data;
import pl.javowe.swirki.zzpjapp.model.User;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    //@JoinColumn(name = "author")
    @JoinTable(
            name = "Thread_User",
            joinColumns =  @JoinColumn(
                    name = "thread_id",
                    referencedColumnName = "id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name="user_id",
                    referencedColumnName = "id",
                    nullable = false
            )
    )
    private User author;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 255)
    @Column(name = "description", nullable = false)
    private String description;

    @PastOrPresent
    @Column(name = "creationdate", nullable = false)
    private Date creationDate;

    @Column(name = "isclosed", nullable = false)
    private boolean isClosed = false;

    @Column (name = "userrating", nullable = false)
    private int userRating = 0;
/*
    //@OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "Thread_Post",
            joinColumns =  @JoinColumn(
                    name = "thread_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name="post_id",
                    referencedColumnName = "id"
            )
    )
    private List<Post> posts = new ArrayList<>();

*/

    public Thread(User author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.creationDate = Calendar.getInstance().getTime();
    }

    public Thread() {
    }
}
