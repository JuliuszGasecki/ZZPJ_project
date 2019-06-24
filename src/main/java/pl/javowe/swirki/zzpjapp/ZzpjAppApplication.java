package pl.javowe.swirki.zzpjapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.javowe.swirki.zzpjapp.model.Locations;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;
import pl.javowe.swirki.zzpjapp.service.UserService;
import pl.javowe.swirki.zzpjapp.service.forumservices.ForumServiceImpl;

@Slf4j
//@ComponentScan({"pl.javowe.swirki.zzpjapp.repository","pl.javowe.swirki.zzpjapp.model", "pl.javowe.swirki.zzpjapp.exception", "pl.javowe.swirki.zzpjapp.documents", "pl.javowe.swirki.zzpjapp.controller"})
@SpringBootApplication
public class ZzpjAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZzpjAppApplication.class, args);
	}

	/*
	@Bean
	CommandLineRunner initDatabase(UserRepository repository) {
		return args -> {
			repository.save(new User("abc@gmail.com", 34, Locations.Poland, "Julek", "Gąska", true, "nie lubi w pupe"));
			repository.save(new User("example@gmail.com", 22, Locations.Ukraine, "Jan", "Kowalski", false, "Jan Kowalski"));
			repository.save(new User("abc@example.com", 30, Locations.Poland, "Anna", "Kowalska", false, "Anna Kowalska"));
		};
	}
	*/

	@Bean
	CommandLineRunner initDatabase(UserService service) {
		return args -> {
				service.addUser(new User("abc@gmail.com", 34, Locations.Poland, "Julek", "Gąska", true, "Krul javowych świrków"));
				service.addUser(new User("example@gmail.com", 22, Locations.Ukraine, "Jan", "Kowalski", false, "Jan Kowalski"));
				service.addUser(new User("abc@example.com", 30, Locations.Poland, "Anna", "Kowalska", false, "Anna Kowalska"));
		};
	}
	@Bean
	CommandLineRunner initDatabase2(ForumServiceImpl forumService, UserService userService) {
		return e -> {
			Thread thread = new Thread(userService.getUser(1L),"Why java is so bad ","Why?");
			forumService.add(thread);
//			try {
//				forumService.addPostToThread(thread,new Post(userService.getUser(1L),"Dont know 1"));
//				forumService.addPostToThread(thread,new Post(userService.getUser(1L),"Dont know 2"));
//				forumService.addPostToThread(thread,new Post(userService.getUser(1L),"Dont know 3"));
//				//System.out.println("CZY DZIAŁA? " +  forumService.getPosts(thread).size());
//			} catch (ThreadAlreadyContainPost threadAlreadyContainPost) {
//				threadAlreadyContainPost.printStackTrace();
//			}
		};
	}
}
