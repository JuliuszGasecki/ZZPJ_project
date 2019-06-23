package pl.javowe.swirki.zzpjapp.model.forumModel;

import lombok.Data;

@Data
public class ThreadCreateRequest {

    private long userID;

    private String tile;

    private String description;

    public ThreadCreateRequest(long userID, String title, String description) {
        this.userID = userID;
        this.tile = title;
        this.description = description;
    }
}
