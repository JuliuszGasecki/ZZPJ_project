package pl.javowe.swirki.zzpjapp.service.forumservices;

import org.springframework.stereotype.Service;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.ThreadRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class ThreadForumService implements ForumService<Thread> {

    private ThreadRepository repository;

    public ThreadForumService(ThreadRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Thread> getAll() {
       return repository.findAll();
    }

    @Override
    public Thread getById(long id) {

        return repository.existsById(id) ? repository.findById(id).get() : null;
    }

    @Override
    public void add(Thread thread) {

        repository.save(thread);

    }

    @Override
    public boolean remove(Thread thread) {


        if(repository.existsById(thread.getId())){
            repository.deleteById(thread.getId());
            return true;
        }else
            return false;

    }

}
