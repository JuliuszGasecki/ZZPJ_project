package pl.javowe.swirki.zzpjapp.service.forumservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javowe.swirki.zzpjapp.exception.ThreadAlreadyContainPost;
import pl.javowe.swirki.zzpjapp.exception.ThreadNotContainingPost;
import pl.javowe.swirki.zzpjapp.exception.ThreadNotFoundException;
import pl.javowe.swirki.zzpjapp.model.forumModel.Post;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.ThreadRepository;

import java.util.List;


@Service
@Transactional
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
    public Thread getById(long id) throws ThreadNotFoundException {

        if (repository.existsById(id))
            return repository.findById(id).get();
        else
            throw new ThreadNotFoundException(id);
    }

    @Override
    public void add(Thread thread) {


        repository.save(thread);

    }

    @Override
    public void remove(Thread thread) throws ThreadNotFoundException {


        if (repository.existsById(thread.getId())) {
            repository.deleteById(thread.getId());
        } else
            throw new ThreadNotFoundException(thread.getId());

    }

    public void addPostToThread(Thread thread, Post post) throws ThreadNotFoundException, ThreadAlreadyContainPost {

        if (getById(thread.getId()).getPosts().stream().filter(e -> e.getId() == (post.getId())).findAny().orElse(null) == null)
        {
            getById(thread.getId()).addPost(post);
            System.out.println(getById(thread.getId()).getPosts().size());
        }

        else throw new ThreadAlreadyContainPost(thread, post.getId());
    }

    public void removePostFromThread(long thread, long post) throws ThreadNotFoundException, ThreadNotContainingPost {

        if (getById(thread).getPosts().stream().filter(e -> e.getId() == (post)).findAny().orElse(null) != null)
            repository.findById(thread).get().removePost(getById(thread).getPostByID(post));
        else throw new ThreadNotContainingPost(getById(thread), post);
    }

    public List<Post> getPosts(Thread thread) {
        System.out.println(repository.findById(thread.getId()).get().getPosts().size());
        return repository.findById(thread.getId()).get().getPosts();
    }


}
