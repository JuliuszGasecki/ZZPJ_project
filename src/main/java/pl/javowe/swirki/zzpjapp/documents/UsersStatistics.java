package pl.javowe.swirki.zzpjapp.documents;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import pl.javowe.swirki.zzpjapp.documents.responseDocuments.UserResponseDocument;
import pl.javowe.swirki.zzpjapp.model.Locations;
import pl.javowe.swirki.zzpjapp.model.User;
import org.json.JSONObject;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UsersStatistics implements DocumentCreator {

    public UsersStatistics(List<User> users, List<Thread> threads) {
        this.users = users;
        this.threads = threads;
    }

    List<User> users;
    List<Thread> threads;

    @Override
    public JSONObject create() {
        JSONObject jsonObject = new JSONObject();

        UserResponseDocument userResponseDocument = fillUserResponseDocument();

        try {
            jsonObject.put("UserDocument", userResponseDocument);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private UserResponseDocument fillUserResponseDocument(){
        UserResponseDocument userResponseDocument = new UserResponseDocument();
        userResponseDocument.setUsers(createUserNamesJson());
        userResponseDocument.setAvgAge(createAverageAge());
        userResponseDocument.setAmountOfUsers(createNumberOfUsers());
        userResponseDocument.setAmountOfPostPerUser(calculateAmountOfPostPerUser());
        userResponseDocument.setAmoutOfUsersFromSpecificLocations(calculateAmoutOfUsersFromSpecificLocations());
        return userResponseDocument;
    }

    private List<String> createUserNamesJson(){
        List<String> usernames = users.stream().map(User::getUsername).collect(Collectors.toList());
        return usernames;
    }


    private double createAverageAge(){
        double avgAge = users.stream().mapToDouble(User::getAge).average().orElse(Double.NaN);
        return avgAge;
    }

    private int createNumberOfUsers(){
        int numberOfUsers = users.size();
        return numberOfUsers;
    }

    private Map<Long, Long> calculateAmountOfPostPerUser(){

        List<Long> usernames = users.stream().map(User::getId).collect(Collectors.toList());
        Map<Long, Long> amoutOfPosts = new HashMap<>();
        List<User> users = threads.stream().map(Thread::getAuthor).collect(Collectors.toList());
        for (Long id: usernames) {
            amoutOfPosts.put(id,
                    users.stream().filter(u -> u.getId() == id).count()
                    );
        }


        return amoutOfPosts;

    }

    private Map<String, Long> calculateAmoutOfUsersFromSpecificLocations() {

        Set<Locations> locations = this.users.stream().map(User::getLocation).collect(Collectors.toSet());
        Map<String, Long> numberOfSpecificLocation = new HashMap<>();
        for (Locations s: locations) {
            numberOfSpecificLocation.put(s.name(),
                    users.stream().filter(u -> (u.getLocation().name() == s.name())).count()
                    );
        }
        return numberOfSpecificLocation;
    }

}

