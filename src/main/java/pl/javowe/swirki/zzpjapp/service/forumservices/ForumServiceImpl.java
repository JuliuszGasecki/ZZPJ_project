package pl.javowe.swirki.zzpjapp.service.forumservices;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javowe.swirki.zzpjapp.exception.PostInvalidDataException;
import pl.javowe.swirki.zzpjapp.exception.PostNotFoundException;
import pl.javowe.swirki.zzpjapp.exception.ThreadInvalidDataException;
import pl.javowe.swirki.zzpjapp.exception.ThreadNotFoundException;
import pl.javowe.swirki.zzpjapp.model.forumModel.Post;
import pl.javowe.swirki.zzpjapp.model.forumModel.PostFilterResponse;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.PostRepository;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.ThreadRepository;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ForumServiceImpl implements ForumService<Thread> {

    private ThreadRepository threadRepository;
    private PostRepository postRepository;
    private Validator validator;
    private ValidatorFactory factory;

    @Autowired
    public ForumServiceImpl(ThreadRepository repository, PostRepository postRepository) {
        this.threadRepository = repository;
        this.postRepository = postRepository;
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    public List<Thread> getAll() {
        return threadRepository.findAll();
    }


    @Override
    public Thread getById(long id) throws ThreadNotFoundException {

        if (threadRepository.existsById(id))
            return threadRepository.findById(id).get();
        else
            throw new ThreadNotFoundException(id);
    }

    @Override
    public void add(Thread thread) throws ThreadInvalidDataException {

        if (validateThread(thread))
            threadRepository.save(thread);
        else
            throw new ThreadInvalidDataException(thread);

    }

    @Override
    public void remove(Thread thread) throws ThreadNotFoundException {

        if (threadRepository.existsById(thread.getId())) {
            threadRepository.deleteById(thread.getId());
        } else
            throw new ThreadNotFoundException(thread.getId());

    }

    public void addPostToThread(Thread thread, Post post) throws PostInvalidDataException {
        post.setThread(thread);
        if(validatePost(post))
        {
            postRepository.save(post);
        }
        else
            throw new PostInvalidDataException(post, thread);
    }

    public void removePostFromThread(long post) throws PostNotFoundException {

        if(!postRepository.existsById(post))
             throw new PostNotFoundException(post);

        postRepository.delete(postRepository.getOne(post));

    }

    public List<Post> getPosts(Thread thread) {
        return postRepository.findAll().stream().filter( e -> e.getThread().getId() == thread.getId()).collect(Collectors.toList());
    }


    public PostFilterResponse getAllPostsContainingWord(String word) {
        List<Post> result = postRepository.findAll().stream()
                .filter(e -> Arrays.asList(e.getBody().toLowerCase().split(" ")).
                        contains(word.toLowerCase())).collect(Collectors.toList());

            return new PostFilterResponse(result);
    }

    public void increasePostRating(long id) {

        postRepository.getOne(id).setUserRating(postRepository.getOne(id).getUserRating() + 1);
    }
    public void decreasePostRating(long id) {

        postRepository.getOne(id).setUserRating(postRepository.getOne(id).getUserRating() - 1);
    }

    public void increaseThreadRating(long id) {

        threadRepository.getOne(id).setUserRating(threadRepository.getOne(id).getUserRating() + 1);
    }

    public void decreaseThreadRating(long id) {

        threadRepository.getOne(id).setUserRating(threadRepository.getOne(id).getUserRating() - 1);
    }

    public PostFilterResponse getAllPostWithRatingBetterThenValue(int value) {

        List<Post> result = postRepository.findAll().stream().filter( e -> e.getUserRating() > value).collect(Collectors.toList());

        return new PostFilterResponse(result);
    }

    public List<Thread> getAllThreadsWithRatingBetterThenValue(int value) {

        return threadRepository.findAll().stream().filter(e -> e.getUserRating() > value).collect(Collectors.toList());
    }

    private boolean validateThread(Thread thread)
    {
        Set<ConstraintViolation<Thread>> violations;
        violations = validator.validate(thread);
        return violations.size() == 0 ? true : false;
    }

    private boolean validatePost(Post post)
    {
        Set<ConstraintViolation<Post>> violations;
        violations = validator.validate(post);
        return violations.size() == 0 ? true : false;
    }
}
