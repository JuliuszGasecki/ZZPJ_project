package pl.javowe.swirki.zzpjapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import pl.javowe.swirki.zzpjapp.controller.UserController;
import pl.javowe.swirki.zzpjapp.exception.UserInvalidDataException;
import pl.javowe.swirki.zzpjapp.model.Locations;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;
import pl.javowe.swirki.zzpjapp.service.UserService;

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
}
