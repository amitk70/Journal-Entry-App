package customwebsite.userservice;


import customwebsite.model.Post;
import customwebsite.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Postservice {

    public Post createNewPost(Post post, Long userid) throws Exception;

    public String deletePost(Long postid, Long userid) throws Exception;

    public List<Post> findPostByUserId(Long userid) throws Exception;

    public Post findPostById(Long postid) throws Exception;

    public List<Post> findAllPost();

    public Post savePost(Long postid, Long userid) throws Exception;

    public Post likedPost(Long postid, Long userid) throws Exception;

    public Post commentPost(Post post,Long postid) throws Exception;

}
