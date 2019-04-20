package pl.javowe.swirki.zzpjapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javowe.swirki.zzpjapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
