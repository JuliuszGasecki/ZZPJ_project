package pl.javowe.swirki.zzpjapp.service.forumservices;

import java.util.List;

public interface ForumService<T> {

    List<T> getAll();
    T getById(long id);
    void add(T t);
    boolean remove(T t);

}
