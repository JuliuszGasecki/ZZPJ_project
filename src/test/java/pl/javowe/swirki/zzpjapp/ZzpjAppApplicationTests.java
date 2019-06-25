package pl.javowe.swirki.zzpjapp;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.javowe.swirki.zzpjapp.exception.UserInvalidDataException;
import pl.javowe.swirki.zzpjapp.model.Locations;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.model.forumModel.Post;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.PostRepository;
import pl.javowe.swirki.zzpjapp.repository.forumrepositories.ThreadRepository;
import pl.javowe.swirki.zzpjapp.service.UserService;
import pl.javowe.swirki.zzpjapp.service.UserServiceImpl;
import java.util.ArrayList;
import lombok.Data;
import pl.javowe.swirki.zzpjapp.service.forumservices.ForumServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZzpjAppApplicationTests {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	private static UserRepository userRepository;

	private static UserServiceImpl userService;

	private static User user1;
	private static User user2;
	private static User user3;
	private static User user4;

	@BeforeClass
	public static void init_test_data() {
		user1 = new User("Ziutek", "admin1","test1@example.com", 1, Locations.Ukraine, "Test1", "Pierwszy", true, "Użytkownik testowy nr 1");
		user2 = new User("Ziutek", "admin1","test2@example.com", 2, Locations.Poland, "Test2", "Drugi", false, "Użytkownik testowy nr 2");
		user3 = new User("Ziutek", "admin1","test3@example.com", 1000, Locations.Poland, "Test3", "Trzeci", false, "Użytkownik testowy nr 3 - invalid");
		user4 = new User("Ziutek", "admin1","test4", 10, Locations.Poland, "Test4", "Czwarty", false, "Użytkownik testowy nr 4 - invalid");
	}

	@Before
	public void setup_userService() {
		userService = new UserServiceImpl(userRepository);
		try {
			userService.addUser(user1);
			userService.addUser(user2);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof UserInvalidDataException);
		}
	}

	@Test
	public void userRepository_getUserTest() {
		Mockito.when(userRepository.getOne(1L)).thenReturn(user1);
		User actual = userRepository.getOne(1L);
		Assert.assertEquals("Test1", actual.getFirstName());
		Assert.assertEquals("Użytkownik testowy nr 1", actual.getDescription());
		Assert.assertEquals(Locations.Ukraine, actual.getLocation());
		Mockito.verify(userRepository).getOne(1L);
	}

	@Test
	public void userService_addValidUsersTest()
	{
		Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<User>() {{ add(user1); add(user2); }});
		Assert.assertEquals(2, userService.getAllUsers().size());
		Mockito.verify(userRepository).findAll();
		Mockito.verify(userRepository).save(user1);
		Mockito.verify(userRepository).save(user2);
	}

	@Test
	public void userService_addUserWithInvalidAgeTest()
	{
		try {
			userService.addUser(user3);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof UserInvalidDataException);
		}
		Mockito.verify(userRepository, Mockito.times(0)).save(user3);
	}

	@Test
	public void userService_addUserWithInvalidEmailTest()
	{
		try {
			userService.addUser(user4);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof UserInvalidDataException);
		}
		Mockito.verify(userRepository, Mockito.times(0)).save(user4);
	}

	@Test
	public void contextLoads() {
	}
}
