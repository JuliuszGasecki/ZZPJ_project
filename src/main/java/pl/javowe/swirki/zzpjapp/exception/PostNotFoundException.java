package pl.javowe.swirki.zzpjapp.exception;

public class PostNotFoundException extends Exception {
    public PostNotFoundException(long id) { super("Post: " + id + " not found"); }
}
