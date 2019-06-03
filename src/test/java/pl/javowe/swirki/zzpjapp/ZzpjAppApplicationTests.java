package pl.javowe.swirki.zzpjapp;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.javowe.swirki.zzpjapp.controller.UserController;
import pl.javowe.swirki.zzpjapp.model.Locations;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZzpjAppApplicationTests {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	private static UserRepository userRepository;

	private static User user1;
	private static User user2;
	private static User user3;
	private static User user4;
	private UserController userController;

	@BeforeClass
	public static void init_test_data() {
		user1 = new User("test1@example.com", 1, Locations.Ukraine, "Test1", "Pierwszy", true, "Użytkownik testowy nr 1");
		user2 = new User("test2@example.com", 2, Locations.Poland, "Test2", "Drugi", false, "Użytkownik testowy nr 2");
		user3 = new User("test3@example.com", 1000, Locations.Poland, "Test3", "Trzeci", false, "Użytkownik testowy nr 3 - invalid");
		user4 = new User("test4", 10, Locations.Poland, "Test4", "Czwarty", false, "Użytkownik testowy nr 4 - invalid");
	}

	@Before
	public void set_up_userController() {
		userController = new UserController(userRepository);
		userController.validateAndAddUser(user1);
		userController.validateAndAddUser(user2);
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
	public void userController_addValidUsersTest()
	{
		Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>() {{ add(user1); add(user2); }});
		Assert.assertEquals(2, userController.getAllUsers().size());
		Mockito.verify(userRepository).findAll();
	}

	@Test
	public void userController_addUserWithInvalidAgeTest()
	{
		Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>() {{ add(user1); add(user2); }});
		userController.validateAndAddUser(user3);
		Assert.assertEquals(2, userController.getAllUsers().size());
		Mockito.verify(userRepository).findAll();
	}

	@Test
	public void userController_addUserWithInvalidEmailTest()
	{
		Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>() {{ add(user1); add(user2); }});
		userController.validateAndAddUser(user4);
		Assert.assertEquals(2, userController.getAllUsers().size());
		Mockito.verify(userRepository).findAll();
	}

	@Test
	public void contextLoads() {
	}

}
