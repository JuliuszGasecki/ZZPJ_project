package pl.javowe.swirki.zzpjapp.exception;

public class ThreadNotFoundException extends Exception {

    public ThreadNotFoundException(long id) {
        super("thread with id: " + id + " not found" );
    }
}
