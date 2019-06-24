package pl.javowe.swirki.zzpjapp.documents.responseDocuments;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserResponseDocument {

    List<String> users;
    double amountOfUsers;
    double avgAge;
    Map<Long, Long> amountOfPostPerUser;
    Map<String, Long>amoutOfUsersFromSpecificLocations;
}
