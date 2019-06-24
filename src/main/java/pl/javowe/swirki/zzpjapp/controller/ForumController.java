package pl.javowe.swirki.zzpjapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.javowe.swirki.zzpjapp.exception.PostNotFoundException;
import pl.javowe.swirki.zzpjapp.exception.ThreadNotFoundException;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
import pl.javowe.swirki.zzpjapp.model.forumModel.Post;
import pl.javowe.swirki.zzpjapp.model.forumModel.PostCreationRequest;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;
import pl.javowe.swirki.zzpjapp.model.forumModel.ThreadCreateRequest;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.PostRepository;
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
    public ForumController(ThreadRepository repository, UserRepository userRepository, PostRepository postRepository) {
        this.threadForumService = new ThreadForumService(repository,postRepository);
        this.userService = new UserServiceImpl(userRepository);
    }

    @GetMapping("/thread")
    public List<Thread> getAllThreads() {
        return threadForumService.getAll();
    }

    @GetMapping("/thread/{id}")
    public Thread getThread(@PathVariable long id) throws ThreadNotFoundException {
        return threadForumService.getById(id);
    }

    @PostMapping("/thread")
    public void addThread( @RequestBody ThreadCreateRequest t) throws UserNotFoundException {

        if (userService.getUser(t.getUserID()) != null)
            threadForumService.add(new Thread(userService.getUser(t.getUserID()),t.getTile(),t.getDescription()));

    }

    @DeleteMapping("/thread/{id}")
    public void removeThread(@PathVariable long id) throws ThreadNotFoundException {

        threadForumService.remove(threadForumService.getById(id));
    }

    @PostMapping("/post")
    public void addPostToThread(@RequestBody PostCreationRequest creationRequest) throws UserNotFoundException, ThreadNotFoundException {

        if (userService.getUser(creationRequest.getUserID()) != null)
             threadForumService.addPostToThread(getThread(creationRequest.getThreadID()), new Post(userService.getUser(creationRequest.getUserID()),creationRequest.getBody()));
    }

    @DeleteMapping("/posts/{postID}")
    public void removePostFromThread(@PathVariable long postID) throws PostNotFoundException {

            threadForumService.removePostFromThread(postID);
    }

    @GetMapping("posts/{id}")
    public List<Post> getAllPostsForThread(@PathVariable long id) throws ThreadNotFoundException {

          return threadForumService.getPosts(getThread(id));
    }
}
