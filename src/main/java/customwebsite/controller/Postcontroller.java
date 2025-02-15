package customwebsite.controller;


import customwebsite.Response.RestApiResponse;
import customwebsite.model.Post;
import customwebsite.userservice.Postservice;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Postcontroller {

    @Autowired
    Postservice postservice;


    @PostMapping("/posts/user/{userid}")
    public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Long userid) throws Exception {
        Post createdpost = postservice.createNewPost(post, userid);
        return new ResponseEntity<>(createdpost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postid}/user/{userid}")
    public ResponseEntity<RestApiResponse> deletePost(@PathVariable Long postid, @PathVariable Long userid) throws Exception {
        String message = postservice.deletePost(postid, userid);
        RestApiResponse res = new RestApiResponse(message, true);
        return new ResponseEntity<RestApiResponse>(res, HttpStatus.OK);
    }


    @GetMapping("/posts/{postid}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Long postid) throws Exception {
        Post post = postservice.findPostById(postid);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userid}")
    public ResponseEntity<List<Post>> findUserPostHandler(@PathVariable Long userid) throws Exception {
        List<Post> posts = postservice.findPostByUserId(userid);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPostHandler() throws Exception {
        List<Post> posts = postservice.findAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PutMapping("/posts/save/{postid}/user/{userid}")
    public ResponseEntity<Post> savePostByIdHandler(@PathVariable Long postid,@PathVariable Long userid) throws Exception {
        Post post = postservice.savePost(postid,userid);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @PostMapping("/posts/like/{postid}/user/{userid}")
    public ResponseEntity<Post> likePosts(@PathVariable Long postid, @PathVariable Long userid) throws Exception {
        Post post = postservice.likedPost(postid,userid);
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/comments/{postid}")
    public ResponseEntity<Post> commentPost(@RequestBody Post post,@PathVariable Long postid) throws Exception {
        Post posts = postservice.commentPost(post,postid);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }



}
