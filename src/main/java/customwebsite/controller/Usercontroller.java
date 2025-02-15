package customwebsite.controller;

import customwebsite.model.User;
import customwebsite.userrepository.Userrepository;
import customwebsite.userservice.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Usercontroller {


    @Autowired
    Userservice userservice;

    @Autowired
    Userrepository userrepository;


    @GetMapping("/user")
    public List<User> getAllUser(){
        return userrepository.findAll();
    }


    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userservice.findUserById(id);
    }

//    @GetMapping("/user/{email}")
//    public Optional<User> getUser(@PathVariable("email") String email){
//        return userservice.findUserByEmail(email);
//    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        User saveUseruser=userservice.saveUser(user);
        return saveUseruser;
    }

    @PutMapping("/user/{id}")
    public User updateUSer(@RequestBody User user, @PathVariable Long id) throws Exception {
        User updatedUser = userservice.updateUser(user , id);
        return updatedUser;
    }

    @PutMapping("/user/{userid1}/{userid2}")
    public User followUserHandler(@PathVariable Long userid1,@PathVariable Long userid2) throws Exception {
        User user = userservice.followUser(userid1,userid2);
        return user;
    }

    @GetMapping("/user/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> searchUser = userservice.searchUser(query);
        return searchUser;
    }

    @DeleteMapping("/user/{userid}")
    public void deleteUser(@PathVariable Long userid){
        userrepository.deleteById(userid);
    }
}
