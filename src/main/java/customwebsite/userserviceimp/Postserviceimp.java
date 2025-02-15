package customwebsite.userserviceimp;

import customwebsite.model.Post;
import customwebsite.model.User;
import customwebsite.userrepository.Postrepository;
import customwebsite.userrepository.Userrepository;
import customwebsite.userservice.Postservice;
import customwebsite.userservice.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class Postserviceimp implements Postservice {


    @Autowired
    Postrepository postrepository;

    @Autowired
    Userservice userservice;

    @Autowired
    Userrepository userrepository;


    @Override
    public Post createNewPost(Post post, Long userid) throws Exception {
        User user= userservice.findUserById(userid);
        Post newpost = new Post();
        newpost.setCaption(post.getCaption());
        newpost.setImage(post.getImage());
        newpost.setVideo(post.getVideo());
        newpost.setCreatedAt(LocalDateTime.now() );
        newpost.setUser(user);
        return postrepository.save(newpost);
    }

    @Override
    public String deletePost(Long postid, Long userid) throws Exception {
        Post post = findPostById(postid);
        User user =userservice.findUserById(userid);
        if(post.getUser().getId() != user.getId()){
            throw new Exception("You cant delete this post");
        }
        postrepository.delete(post);
        return "Post deleted";
    }

    @Override
    public List<Post> findPostByUserId(Long  userid) throws Exception {

        List<Post> post = postrepository.finPostByUserId(userid);
        return post;
    }

    @Override
    public Post findPostById(Long postid) throws Exception {
        Optional<Post> posts= postrepository.findById(postid);
        if(posts.isEmpty()){
            throw new Exception("post mot found"+ postid);
        }
        return posts.get();
    }

    @Override
    public List<Post> findAllPost() {
        List<Post> posts=postrepository.findAll();
        return posts;
    }

    @Override
    public Post savePost(Long postid, Long userid) throws Exception {
        Post post= findPostById(postid);
        User user = userservice.findUserById(userid);
        if (user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }
        {
            user.getSavedPost().add(post);
        }
        userrepository.save(user);
        return post;
    }

    @Override
    public Post likedPost(Long postid, Long userid) throws Exception {
        Post post= findPostById(postid);
        User user = userservice.findUserById(userid);
        if (post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }
        else {
            post.getLiked().add(user);
        }
        return postrepository.save(post);
    }

    @Override
    public Post commentPost(Post post,Long postid) throws Exception {
        Post posts = findPostById(postid);
        posts.setCaption(post.getCaption());
        return postrepository.save(posts);

}}
