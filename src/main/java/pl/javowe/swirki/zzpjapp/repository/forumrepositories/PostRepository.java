package pl.javowe.swirki.zzpjapp.repository.forumrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javowe.swirki.zzpjapp.model.forumModel.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
}
