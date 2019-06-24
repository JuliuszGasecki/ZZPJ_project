package pl.javowe.swirki.zzpjapp.model.forumModel;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostFilterResponse {

    private List<ThreadToPosts> threadToPosts = new ArrayList<>();

    public PostFilterResponse(List<Post> posts)
    {
        for(Post x : posts)
            threadToPosts.add(new ThreadToPosts(x, x.getThread().getId()));

    }

    @Data
    private class ThreadToPosts
    {
        private Post Post;

        private long threadID;

        public ThreadToPosts(pl.javowe.swirki.zzpjapp.model.forumModel.Post post, long threadID) {
            this.Post = post;
            this.threadID = threadID;
        }
    }
}
