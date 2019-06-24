package pl.javowe.swirki.zzpjapp.exception;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;

public class ThreadInvalidDataException extends Exception {
    public ThreadInvalidDataException(Thread thread) {
        super("Could not add new Thread = " + thread.getTitle());
    }
}
