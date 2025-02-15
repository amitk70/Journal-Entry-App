package customwebsite.userservice;

import customwebsite.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface Userservice {

    public User saveUser(User user);

    public User findUserById(Long id);

    public User findUserByEmail(String email);

    public User followUser(Long userId1, Long userId2) throws Exception;

    public List<User> searchUser(String query);

    public User updateUser(User user, Long userid) throws Exception;

}