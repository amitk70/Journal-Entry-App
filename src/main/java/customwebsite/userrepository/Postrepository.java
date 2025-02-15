package customwebsite.userrepository;

import customwebsite.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Postrepository extends JpaRepository<Post, Long> {


    @Query("select a from Post a where a.user.id=:userid")
    public List<Post> finPostByUserId(Long userid);
}
