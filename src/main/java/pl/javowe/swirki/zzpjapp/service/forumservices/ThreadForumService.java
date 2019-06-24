package pl.javowe.swirki.zzpjapp.service.forumservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javowe.swirki.zzpjapp.exception.PostNotFoundException;
import pl.javowe.swirki.zzpjapp.exception.ThreadNotFoundException;
import pl.javowe.swirki.zzpjapp.model.forumModel.Post;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.PostRepository;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.ThreadRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ThreadForumService implements ForumService<Thread> {

    private ThreadRepository repository;

    @Autowired
    private PostRepository postRepository;

    public ThreadForumService(ThreadRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
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

    public void addPostToThread(Thread thread, Post post) {

            post.setThread(thread);
            postRepository.save(post);

    }

    public void removePostFromThread(long post) throws PostNotFoundException {

        if(!postRepository.existsById(post))
             throw new PostNotFoundException(post);

        postRepository.delete(postRepository.getOne(post));

    }

    public List<Post> getPosts(Thread thread) {
        return postRepository.findAll().stream().filter( e -> e.getThread().getId() == thread.getId()).collect(Collectors.toList());
    }


}
