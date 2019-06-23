package pl.javowe.swirki.zzpjapp.model.forumModel;

import lombok.Data;
import pl.javowe.swirki.zzpjapp.exception.ThreadNotContainingPost;
import pl.javowe.swirki.zzpjapp.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "thread")
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name = "isclosed")
    private boolean isClosed = false;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @Column (name = "userrating")
    private int userRating = 0;

    public Thread(User author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.creationDate = Calendar.getInstance().getTime();
    }

    public Thread() {
    }

    public void addPost(Post e) {

        posts.add(e);
        e.setThread(this);

    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    public Post getPostByID(long id) throws ThreadNotContainingPost {
        return posts.stream().filter(e -> e.getId() == (id)).findFirst().orElseThrow(() -> new ThreadNotContainingPost(this, id));
    }


}
