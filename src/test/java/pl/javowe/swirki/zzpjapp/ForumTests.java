package pl.javowe.swirki.zzpjapp;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.javowe.swirki.zzpjapp.exception.PostInvalidDataException;
import pl.javowe.swirki.zzpjapp.exception.ThreadInvalidDataException;
import pl.javowe.swirki.zzpjapp.exception.ThreadNotFoundException;
import pl.javowe.swirki.zzpjapp.exception.UserInvalidDataException;
import pl.javowe.swirki.zzpjapp.model.Locations;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.model.forumModel.Post;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.PostRepository;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.ThreadRepository;
import pl.javowe.swirki.zzpjapp.service.UserService;
import pl.javowe.swirki.zzpjapp.service.UserServiceImpl;
import pl.javowe.swirki.zzpjapp.service.forumservices.ForumServiceImpl;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ForumTests {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private static UserRepository userRepository;
    @Mock
    private static PostRepository postRepository;
    @Mock
    private static ThreadRepository threadRepository;

    private static UserService userService;
    @InjectMocks
    private static ForumServiceImpl forumService;
    private static User user1;
    private static Thread correctThread;
    private static Thread correctThread2;
    private static Thread incorrectThread;
    private static Post correctPost;
    private static Post incorrectPost;


    @BeforeClass
    public static void init_test_data() {
        user1 = new User("Ziutek", "admin1","test1@example.com", 1, Locations.Ukraine, "Test1", "Pierwszy", true, "Użytkownik testowy nr 1");
        user1.setId(1L);
        correctThread = new Thread(user1, "Thread Title", "Thread Description");
        correctThread.setId(10L);
        correctThread2 = new Thread(user1, "Thread Title2", "Thread Description2");
        incorrectThread = new Thread(null, "Incorrect Thread Title", "Incorrect Thread Description");
        correctPost = new Post(user1, "Post Body");
        incorrectPost = new Post(null, "Incorrect Post Body");
    }

    @Before
    public void setup_forumService() {
        userService = new UserServiceImpl(userRepository);

        try {
            userService.save(user1);
        } catch (UserInvalidDataException e) {
            e.printStackTrace();
        }
        forumService = new ForumServiceImpl(threadRepository, postRepository);
        try {
            forumService.add(correctThread);
            forumService.addPostToThread(correctThread, correctPost);
        } catch (ThreadInvalidDataException|PostInvalidDataException e) {
            System.out.println("Halo nie dodało :<");
            System.out.println(e);
        }

    }

    @Test
    public void threadRepository_get_test() {
        Mockito.when(threadRepository.getOne(10L)).thenReturn(correctThread);
        Thread actualThread = threadRepository.getOne(10L);
        Assert.assertEquals("Thread Title", actualThread.getTitle());
        Assert.assertEquals("Thread Description", actualThread.getDescription());
        Mockito.verify(threadRepository).getOne(10L);
    }

    @Test
    public void postRepository_get_test() {
        Mockito.when(postRepository.getOne(100L)).thenReturn(correctPost);
        Post actualPost = postRepository.getOne(100L);
        Assert.assertEquals("Post Body", actualPost.getBody());
        Assert.assertEquals("Thread Title", actualPost.getThread().getTitle());
        Mockito.verify(postRepository).getOne(100L);
    }

    @Test
    public void forumService_addIncorrectThread_test()
    {
        try {
            forumService.add(incorrectThread);
        } catch (ThreadInvalidDataException e) {
            Assert.assertTrue(e instanceof ThreadInvalidDataException);
        }
        Mockito.verify(threadRepository, Mockito.times(0)).save(incorrectThread);
    }

    @Test
    public void forumService_addIncorrectPostToAThread_test()
    {
        try {
            forumService.addPostToThread(correctThread, incorrectPost);
        } catch (PostInvalidDataException e) {
            Assert.assertTrue(e instanceof  PostInvalidDataException);
        }
        Mockito.verify(postRepository, Mockito.times(0)).save(incorrectPost);
    }

}