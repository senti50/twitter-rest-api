package pl.senti.twitterrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.senti.twitterrestapi.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
