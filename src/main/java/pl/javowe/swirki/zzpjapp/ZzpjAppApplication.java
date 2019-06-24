package pl.javowe.swirki.zzpjapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import pl.javowe.swirki.zzpjapp.controller.UserController;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import pl.javowe.swirki.zzpjapp.exception.UserInvalidDataException;
import pl.javowe.swirki.zzpjapp.model.Locations;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.model.forumModel.Post;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;
import pl.javowe.swirki.zzpjapp.service.UserService;
import pl.javowe.swirki.zzpjapp.service.forumservices.ForumServiceImpl;

@Slf4j
//@ComponentScan({"pl.javowe.swirki.zzpjapp.repository","pl.javowe.swirki.zzpjapp.model", "pl.javowe.swirki.zzpjapp.exception", "pl.javowe.swirki.zzpjapp.documents", "pl.javowe.swirki.zzpjapp.controller"})
@SpringBootApplication
public class ZzpjAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZzpjAppApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserService service, ForumServiceImpl forumService) {
		return args -> {
			service.addUser(new User("A", "admin1", "abc@gmail.com", 34, Locations.Poland, "Julek", "Gąska", true, "nie lubi w pupe"));
			service.addUser(new User("B","admin1", "example@gmail.com", 22, Locations.Ukraine, "Jan", "Kowalski", false, "Jan Kowalski"));
			service.addUser(new User("C","admin1", "abc@example.com", 30, Locations.Poland, "Anna", "Kowalska", false, "Anna Kowalska"));
            service.addUser(new User("Ziutek", "admin1","abc@gmail.com", 34, Locations.Poland, "Julek", "Gąska", true, "Krul javowych świrków"));
            service.addUser(new User("Ziutek2", "admin1","example@gmail.com", 22, Locations.Ukraine, "Jan", "Kowalski", false, "Jan Kowalski"));
            service.addUser(new User("Ziutek3", "admin1","abc@example.com", 30, Locations.Poland, "Anna", "Kowalska", false, "Anna Kowalska"));
			Thread thread = new Thread(service.getUser(1L), "Thread Test", "Description Test");
            forumService.add(thread);
		};
	}

}
