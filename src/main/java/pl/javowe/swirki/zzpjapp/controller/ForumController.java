package pl.javowe.swirki.zzpjapp.controller;

import com.graphbuilder.struc.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.ThreadRepository;
import pl.javowe.swirki.zzpjapp.service.forumservices.ForumService;
import pl.javowe.swirki.zzpjapp.service.forumservices.ThreadForumService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ForumController {


    private ThreadForumService service;

    @Autowired
    public ForumController(ThreadRepository repository) {
        this.service = new ThreadForumService(repository);
    }
    @GetMapping("/threads")
    public List<Thread> getAllThreads()
    {
        return service.getAll();
    }
    @GetMapping("/threads/{id}")
    public Thread getThread(@PathVariable long id)
    {
        return service.getById(id) ;
    }

    public  void addThread(Thread t)
    {
    }
}
