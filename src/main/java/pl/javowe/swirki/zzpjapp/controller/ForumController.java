package pl.javowe.swirki.zzpjapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.javowe.swirki.zzpjapp.exception.PostNotFoundException;
import pl.javowe.swirki.zzpjapp.exception.ThreadNotFoundException;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
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
    public void addThread( @RequestBody ThreadCreationRequest t) throws UserNotFoundException {

        if (userService.getUser(t.getUserID()) != null)
            forumService.add(new Thread(userService.getUser(t.getUserID()),t.getTile(),t.getDescription()));

    }

    @DeleteMapping("forum/thread/{id}")
    public void removeThread(@PathVariable long id) throws ThreadNotFoundException {

        forumService.remove(forumService.getById(id));
    }

    @PostMapping("forum/post")
    public void addPostToThread(@RequestBody PostCreationRequest creationRequest) throws UserNotFoundException, ThreadNotFoundException {

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

    @GetMapping("forum/post/filter/{word}")
    public PostFilterResponse getAllPostsWithWord(@PathVariable String word){

          return forumService.getAllPostsContainingWord(word);
    }
   
}
