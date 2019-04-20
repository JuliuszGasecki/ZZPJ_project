package pl.javowe.swirki.zzpjapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data //provides getters, setters, HashCodeAndEquals, RequiredArgsConstructor, to string
//@Entity // <- JPA entity
//@Table("user") //<- JPA table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //primary key generated with TopLink
    private Long id;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private String description;
    //TODO programming languages class with experience level
    private List<String> programmingLanguages;
    //TODO programming techniques and other special abilities
    private List<String> otherAbilities;
    //TODO workEexperience class, which contains period of work, employer and responsibilities.
    private List<String> workExperience;
}
