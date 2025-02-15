package customwebsite.userserviceimp;

import customwebsite.model.User;
import customwebsite.userrepository.Userrepository;
import customwebsite.userservice.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.AttributeNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class Userserviceimp implements Userservice {

    @Autowired
    Userrepository userrepository;



    @Override
    public User saveUser(User user) {
        User newUser= new User();
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setMobile(user.getMobile());
        newUser.setPassword(user.getPassword());
        newUser.setGender(user.getGender());

        return userrepository.save(newUser);
    }

    @Override
    public User findUserById(Long id) {
        User user = userrepository.findById(id).get();
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userrepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Long userId1, Long userId2) throws Exception {

        User user1=findUserById(userId1);
        User user2=findUserById(userId2);
        user2.getFollowers().add(user1.getId());
        user1.getFollowing().add(user2.getId());
        userrepository.save(user1);
        userrepository.save(user2);

        return user1;
    }

    @Override
    public List<User> searchUser(String query) {
        List<User> searchUser= userrepository.searchUser(query);
        return searchUser;
    }


    @Override
    public User updateUser(User user, Long userId) throws Exception {
        User user1 = userrepository.findById(userId).orElse(()-> new AttributeNotFoundException("bjmvmh"));
        if(user1.isEmpty()){
            throw new Exception("User not exist with id "+userId);
        }
        User oldUser = user1.get();
        if(user.getFirstname()!=null){
            oldUser.setFirstname(user.getFirstname());
        }
        if(user.getLastname()!=null){
            oldUser.setLastname(user.getLastname());
        }
        if(user.getGender()!=null){
            oldUser.setGender(user.getGender());
        }
        User updatedUser = userrepository.save(oldUser);
        return oldUser;
    }

}
