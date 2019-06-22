package pl.javowe.swirki.zzpjapp.exception;

import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;

public class ThreadNotContainingPost extends Throwable {
    public ThreadNotContainingPost(Thread thread, Long postID) {
        super("Thread: " + thread.getId() + "does not contain post: " + postID.toString());
    }
}
