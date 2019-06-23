package pl.javowe.swirki.zzpjapp.model.forumModel;

import lombok.Data;

@Data
public class PostCreationRequest {

    private long threadID;

    private long userID;

    private String body;

    public PostCreationRequest(long threadID, long userID, String body) {
        this.threadID = threadID;
        this.userID = userID;
        this.body = body;
    }
}
