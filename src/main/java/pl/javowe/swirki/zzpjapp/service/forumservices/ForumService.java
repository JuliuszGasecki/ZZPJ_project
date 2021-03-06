package pl.javowe.swirki.zzpjapp.service.forumservices;

import pl.javowe.swirki.zzpjapp.exception.ThreadInvalidDataException;
import pl.javowe.swirki.zzpjapp.exception.ThreadNotFoundException;

import java.util.List;

public interface ForumService<T> {

    List<T> getAll();

    T getById(long id) throws ThreadNotFoundException;

    void add(T t) throws ThreadInvalidDataException;

    void remove(T t) throws ThreadNotFoundException;

}
