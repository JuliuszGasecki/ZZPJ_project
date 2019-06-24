package pl.javowe.swirki.zzpjapp.documents;

import org.json.JSONException;
import org.json.JSONObject;
import pl.javowe.swirki.zzpjapp.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class AdminStatistics implements DocumentCreator {

    public AdminStatistics(List<User> users){
        this.users = users;
    }

    List<User> users;

    @Override
    public JSONObject create() {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("userNames", createAdminNamesJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jsonObject;
    }

    private List<String> createAdminNamesJson(){
        List<String> usernames = users.stream().filter(u -> u.isAdmin()).map(User::getUsername).collect(Collectors.toList());
        return usernames;
    }
}
