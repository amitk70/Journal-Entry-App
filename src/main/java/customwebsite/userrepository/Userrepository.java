package customwebsite.userrepository;

import customwebsite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Userrepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    @Query("select a from User a where a.firstname LIKE %:query% OR a.lastname LIKE %:query% OR a.email LIKE %:query%")
    public List<User> searchUser(@Param("query") String query);
}
