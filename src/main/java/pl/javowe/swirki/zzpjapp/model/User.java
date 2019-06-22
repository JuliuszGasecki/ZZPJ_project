package pl.javowe.swirki.zzpjapp.model;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Locale;

@Data //provides getters, setters, HashCodeAndEquals, RequiredArgsConstructor, to string
@Entity // <- JPA entity
//@Table("user") //<- JPA table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //primary key generated with TopLink
    @Column(name = "id", nullable =  false)
    private Long id;

    @Column(name = "emailAddress", nullable = false)
    @Email
    private String emailAdress;

    @Column(name = "age", nullable =  false)
    @Min(0)
    @Max(100)
    private int age;

    @Column(name = "location", nullable = false)
    private Locations location; //<- select location from list

    @Column(name = "firstName", nullable = false, length = 25)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 25)
    private String lastName;

    @Column(name = "isAdmin", nullable = false)
    private boolean isAdmin;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "picture", length = 10000000)
    private byte[] loadedPicture;

   /* //TODO programming languages class with experience level
    private List<String> programmingLanguages;
    //TODO programming techniques and other special abilities
    private List<String> otherAbilities;
    //TODO workEexperience class, which contains period of work, employer and responsibilities.
    private List<String> workExperience;*/

    public User() {
    }
    /*
    public User(String emailAdress, long age) {
        this.emailAdress = emailAdress;
        this.age = age;
    }*/

    /*
    public User(String emailAdress, int age, Locations location, String firstName, String lastName, boolean isAdmin, String description, List<String> programmingLanguages, List<String> otherAbilities, List<String> workExperience) {
        this.emailAdress = emailAdress;
        this.age = age;
        this.location = location;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.description = description;
        this.programmingLanguages = programmingLanguages;
        this.otherAbilities = otherAbilities;
        this.workExperience = workExperience;
    }*/

    public User(String emailAdress, int age, Locations location, String firstName, String lastName, boolean isAdmin, String description) {
        this.emailAdress = emailAdress;
        this.age = age;
        this.location = location;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.description = description;
    }
}
