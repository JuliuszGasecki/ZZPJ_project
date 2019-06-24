package pl.javowe.swirki.zzpjapp.repository.forumrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javowe.swirki.zzpjapp.model.forumModel.Thread;

@Repository
public interface ThreadRepository extends JpaRepository<Thread,Long> {
}
