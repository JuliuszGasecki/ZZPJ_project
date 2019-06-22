package pl.javowe.swirki.zzpjapp.model.forumModel;

import lombok.Data;
import pl.javowe.swirki.zzpjapp.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //primary key generated with TopLink
    private long id;

    @OneToOne
    private User author;

    private String title;

    private String description;

    private Date creationDate;

    private boolean isClosed = false;

    @OneToMany(targetEntity=Post.class, fetch=FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();

    private int userRating = 0;

    public Thread(User author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.creationDate = Calendar.getInstance().getTime();
    }
    public Thread(){

    }


}
