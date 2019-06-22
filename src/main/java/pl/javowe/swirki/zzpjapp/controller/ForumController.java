package pl.javowe.swirki.zzpjapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.javowe.swirki.zzpjapp.exception.ThreadAlreadyContainPost;
import pl.javowe.swirki.zzpjapp.exception.ThreadNotContainingPost;
import pl.javowe.swirki.zzpjapp.exception.ThreadNotFoundException;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
import pl.javowe.swirki.zzpjapp.model.forumModel.Post;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;
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
    public List<Thread> getAllThreads()
    {
        return threadForumService.getAll();
    }
    @GetMapping("/threads/{id}")
    public Thread getThread(@PathVariable long id) throws ThreadNotFoundException {
            return threadForumService.getById(id) ;
    }

    @PostMapping("/threads")
    public  void addThread(@RequestBody Thread t) throws UserNotFoundException {

           if( userService.getUser(t.getAuthor().getId()) != null) //this sucks
               threadForumService.add(t);

    }
    @DeleteMapping("/threads/{id}")
    public void removeThread(@PathVariable long id) throws ThreadNotFoundException {

            threadForumService.remove(threadForumService.getById(id));
    }

    @PostMapping("/posts/{id}")
    public void addPostToThread(@PathVariable long id ,@RequestBody Post post) throws UserNotFoundException, ThreadNotFoundException, ThreadAlreadyContainPost {

        if(userService.getUser(post.getId())!= null);//this also
             threadForumService.addPostToThread(getThread(id),post);
    }
    @DeleteMapping("/posts/{threadId}")
    public void removePostFromThread(@PathVariable long threadID,@RequestBody Post postID) throws ThreadNotFoundException, ThreadNotContainingPost {
        threadForumService.removePostFromThread(threadID,postID);

    }
    @GetMapping("posts/{id}")
    public List<Post> getAllPostsForThread(@PathVariable long id) throws ThreadNotFoundException {
        return threadForumService.getPosts(getThread(id));
    }
}
