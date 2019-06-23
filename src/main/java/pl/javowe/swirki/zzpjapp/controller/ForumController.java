package pl.javowe.swirki.zzpjapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.javowe.swirki.zzpjapp.exception.ThreadAlreadyContainPost;
import pl.javowe.swirki.zzpjapp.exception.ThreadNotContainingPost;
import pl.javowe.swirki.zzpjapp.exception.ThreadNotFoundException;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
import pl.javowe.swirki.zzpjapp.model.forumModel.Post;
import pl.javowe.swirki.zzpjapp.model.forumModel.PostCreationRequest;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;
import pl.javowe.swirki.zzpjapp.model.forumModel.ThreadCreateRequest;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.ThreadRepository;
import pl.javowe.swirki.zzpjapp.service.UserService;
import pl.javowe.swirki.zzpjapp.service.UserServiceImpl;
import pl.javowe.swirki.zzpjapp.service.forumservices.ThreadForumService;

import java.util.List;

@RestController
public class ForumController {


    private ThreadForumService threadForumService;
    private UserService userService;

    @Autowired
    public ForumController(ThreadRepository repository, UserRepository userRepository) {
        this.threadForumService = new ThreadForumService(repository);
        this.userService = new UserServiceImpl(userRepository);
    }

    @GetMapping("/threads")
    public List<Thread> getAllThreads() {
        return threadForumService.getAll();
    }

    @GetMapping("/threads/{id}")
    public Thread getThread(@PathVariable long id) throws ThreadNotFoundException {
        return threadForumService.getById(id);
    }

    @PostMapping("/threads")
    public void addThread( @RequestBody ThreadCreateRequest t) throws UserNotFoundException {

        if (userService.getUser(t.getUserID()) != null) //this sucks
            threadForumService.add(new Thread(userService.getUser(t.getUserID()),t.getTile(),t.getDescription()));

    }

    @DeleteMapping("/threads/{id}")
    public void removeThread(@PathVariable long id) throws ThreadNotFoundException {

        threadForumService.remove(threadForumService.getById(id));
    }

    @PostMapping("/posts")
    public void addPostToThread(@RequestBody PostCreationRequest post) throws UserNotFoundException, ThreadNotFoundException, ThreadAlreadyContainPost {

        if (userService.getUser(post.getUserID()) != null) ;//this also
        threadForumService.addPostToThread(getThread(post.getThreadID()), new Post(userService.getUser(post.getUserID()),post.getBody()));
        threadForumService.getPosts(getThread(post.getThreadID()));
        post =post;
    }

    @DeleteMapping("/posts/{threadID}/{postID}")
    public void removePostFromThread(@PathVariable long threadID, @PathVariable long postID) throws ThreadNotFoundException, ThreadNotContainingPost {
        threadForumService.removePostFromThread(threadID, postID);

    }

    @GetMapping("posts/{id}")
    public List<Post> getAllPostsForThread(@PathVariable long id) throws ThreadNotFoundException {
        return threadForumService.getPosts(getThread(id));
    }
}
