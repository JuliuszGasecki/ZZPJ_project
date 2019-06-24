package pl.javowe.swirki.zzpjapp.documents;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import pl.javowe.swirki.zzpjapp.model.User;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsersStatistics implements DocumentCreator {

    public UsersStatistics(List<User> users) {
        this.users = users;
    }

    List<User> users;

    @Override
    public JSONObject create() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("userNames", createUserNamesJson());
            jsonObject.put("averageAge", createAverageAge());
            jsonObject.put("numberOfUsers", createNumberOfUsers());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jsonObject;
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

}

