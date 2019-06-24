package pl.javowe.swirki.zzpjapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.javowe.swirki.zzpjapp.exception.*;
import pl.javowe.swirki.zzpjapp.model.forumModel.*;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.PostRepository;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.ThreadRepository;
import pl.javowe.swirki.zzpjapp.service.UserService;
import pl.javowe.swirki.zzpjapp.service.UserServiceImpl;
import pl.javowe.swirki.zzpjapp.service.forumservices.ForumServiceImpl;

import java.util.List;

@RestController
public class ForumController {

    private ForumServiceImpl forumService;
    private UserService userService;

    @Autowired
    public ForumController(ThreadRepository repository, UserRepository userRepository, PostRepository postRepository) {
        this.forumService = new ForumServiceImpl(repository,postRepository);
        this.userService = new UserServiceImpl(userRepository);
    }

    @GetMapping("forum/thread")
    public List<Thread> getAllThreads() {
        return forumService.getAll();
    }

    @GetMapping("forum/thread/{id}")
    public Thread getThread(@PathVariable long id) throws ThreadNotFoundException {
        return forumService.getById(id);
    }

    @PostMapping("forum/thread")
    @ResponseStatus(HttpStatus.CREATED)
    public void addThread( @RequestBody ThreadCreationRequest t) throws UserNotFoundException, ThreadInvalidDataException {

        if (userService.getUser(t.getUserID()) != null)
            forumService.add(new Thread(userService.getUser(t.getUserID()),t.getTile(),t.getDescription()));

    }

    @DeleteMapping("forum/thread/{id}")
    public void removeThread(@PathVariable long id) throws ThreadNotFoundException {

        forumService.remove(forumService.getById(id));
    }

    @PostMapping("forum/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPostToThread(@RequestBody PostCreationRequest creationRequest) throws UserNotFoundException, ThreadNotFoundException, PostInvalidDataException {

        if (userService.getUser(creationRequest.getUserID()) != null)
             forumService.addPostToThread(getThread(creationRequest.getThreadID()), new Post(userService.getUser(creationRequest.getUserID()),creationRequest.getBody()));
    }

    @DeleteMapping("forum/post/{postID}")

    public void removePostFromThread(@PathVariable long postID) throws PostNotFoundException {

            forumService.removePostFromThread(postID);
    }

    @GetMapping("forum/post/{id}")
    public List<Post> getAllPostsForThread(@PathVariable long id) throws ThreadNotFoundException {

          return forumService.getPosts(getThread(id));
    }

    @GetMapping("forum/post/filter-word/{word}")
    public PostFilterResponse getAllPostsWithWord(@PathVariable String word){

          return forumService.getAllPostsContainingWord(word);
    }
    @GetMapping("forum/post/filter-rating/{value}")
    public PostFilterResponse getAllPostWithRatingBetterThenValue(@PathVariable int value)
    {
        return forumService.getAllPostWithRatingBetterThenValue(value);
    }
    @GetMapping("forum/thread/filter-rating/{value}")
    public List<Thread> getAllThreadsWithRatingBetterThenValue(@PathVariable int value)
    {
        return forumService.getAllThreadsWithRatingBetterThenValue(value);
    }

    @PutMapping("forum/post/increase-rating/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void increasePostRating(@PathVariable long id){

        forumService.increasePostRating(id);
    }
    @PutMapping("forum/post/decrease-rating/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void decreasePostRating(@PathVariable long id){

        forumService.decreasePostRating(id);
    }

    @PutMapping("forum/thread/increase-rating/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void increaseThreadRating(@PathVariable long id){

        forumService.increaseThreadRating(id);
    }
    @PutMapping("forum/thread/decrease-rating/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void decreaseThreadRating(@PathVariable long id){

        forumService.decreaseThreadRating(id);
    }
}
