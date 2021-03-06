package pl.javowe.swirki.zzpjapp.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javowe.swirki.zzpjapp.documents.AdminStatistics;
import pl.javowe.swirki.zzpjapp.documents.DocumentStrategy;
import pl.javowe.swirki.zzpjapp.documents.ForumStatistics;
import pl.javowe.swirki.zzpjapp.documents.UsersStatistics;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.PostRepository;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.ThreadRepository;
import pl.javowe.swirki.zzpjapp.service.UserService;
import pl.javowe.swirki.zzpjapp.service.UserServiceImpl;
import pl.javowe.swirki.zzpjapp.service.forumservices.ForumServiceImpl;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collection;
import java.util.List;

@RestController
public class DocumentCreatorController {

    private UserRepository repository;
    private UserService userService;
    private ValidatorFactory factory;
    private Validator validator;
    private DocumentStrategy documentStrategy;
    private ForumServiceImpl forumService;

    @Autowired
    public DocumentCreatorController(UserRepository repository, ThreadRepository thredRepository, PostRepository postRepository) {

        this.repository = repository;
        this.forumService = new ForumServiceImpl(thredRepository,postRepository);
        userService = new UserServiceImpl(repository);
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        documentStrategy = new DocumentStrategy();
    }

    @GetMapping("/createCV") //<- method for http Get request
    public String createCV(){

        return "a";
    }

    @GetMapping("/createStatisticsUser") //<- method for http Get request
    public String createDocumentUser(){
        documentStrategy.documentCreator = new UsersStatistics(userService.getAllUsers(), forumService.getAll());
        JSONObject document = documentStrategy.documentCreator.create();
        return document.toString();

    }

    @GetMapping("/createStatisticsAdmin") //<- method for http Get request
    public String createDocumentAdmin(){
        documentStrategy.documentCreator = new AdminStatistics(userService.getAllUsers());
        JSONObject document = documentStrategy.documentCreator.create();
        return document.toString();
    }

    @GetMapping("/createStatisticsForum") //<- method for http Get request
    public String createDocumentForum(){
        documentStrategy.documentCreator = new ForumStatistics(userService.getAllUsers(), forumService);
        JSONObject document = documentStrategy.documentCreator.create();
        return document.toString();
    }

}
