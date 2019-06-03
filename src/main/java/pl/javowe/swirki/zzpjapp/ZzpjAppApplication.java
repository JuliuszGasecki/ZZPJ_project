package pl.javowe.swirki.zzpjapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import pl.javowe.swirki.zzpjapp.model.Locations;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;

@Slf4j
//@ComponentScan({"pl.javowe.swirki.zzpjapp.repository","pl.javowe.swirki.zzpjapp.model", "pl.javowe.swirki.zzpjapp.exception", "pl.javowe.swirki.zzpjapp.documents", "pl.javowe.swirki.zzpjapp.controller"})
@SpringBootApplication
public class ZzpjAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZzpjAppApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserRepository repository) {
		return args -> {
			repository.save(new User("abc.@gmail.com", 22, Locations.Poland, "Julek", "Gąska", true, "nie lubi w pupe"));
			repository.save(new User("absc.@gmail.com", 34, Locations.Poland, "Jan", "Kowalski", true, "Jestem studentem i kocham sie najebać ;D"));
			repository.save(new User("asd.@gmail.com", 324, Locations.Poland, "Kraszan", "Kubuś", true, "Lol nie"));
		};
	}
}
