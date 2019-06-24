package pl.javowe.swirki.zzpjapp.repository.forumrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javowe.swirki.zzpjapp.model.forumModel.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
