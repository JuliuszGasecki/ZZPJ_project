package pl.javowe.swirki.zzpjapp.model;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Locale;

@Data //provides getters, setters, HashCodeAndEquals, RequiredArgsConstructor, to string
@Entity // <- JPA entity
//@Table("user") //<- JPA table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //primary key generated with TopLink
    private Long id;
    private String emailAdress;
    private int age;
    private Locations location; //<- select location from list
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private String description;
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
