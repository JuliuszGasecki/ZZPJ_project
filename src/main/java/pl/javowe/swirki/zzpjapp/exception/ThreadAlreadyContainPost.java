package pl.javowe.swirki.zzpjapp.exception;

import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;

public class ThreadAlreadyContainPost extends Throwable {

        public ThreadAlreadyContainPost(Thread thread, Long postID) {
            super("Thread: " + thread.getId() + "already contain contain post: " + postID.toString());
        }
}
