package pl.javowe.swirki.zzpjapp.model.forumModel;

import lombok.Data;
import pl.javowe.swirki.zzpjapp.model.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Data
@Entity
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private User author;

    private String title;

    private String description;

    private ArrayList<Post> posts = new ArrayList<>();

    public Thread(User author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
    }
}
