package pl.javowe.swirki.zzpjapp.exception;


import pl.javowe.swirki.zzpjapp.model.forumModel.Post;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;

public class PostInvalidDataException extends Exception {
    public PostInvalidDataException(Post post, Thread thread) {
        super("Could not add new Post = " + post + " to a thread: " + thread.getTitle());
    }
}
