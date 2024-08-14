package com.example.lap11.Service;

import com.example.lap11.Api.ApiException;
import com.example.lap11.Model.Category;
import com.example.lap11.Model.Post;
import com.example.lap11.Model.User;
import com.example.lap11.Repository.CategoryRepository;
import com.example.lap11.Repository.PostRepository;
import com.example.lap11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<Post> getPost() {
        return postRepository.findAll();
    }

    public void addPost(Post post) {

        User u = userRepository.findUserByUserId(post.getUserId());
        Category c = categoryRepository.findCategoriesByCategoryId(post.getCategoryId());
        if(u==null || c==null){
            throw new ApiException("UserId or CategoryId not found");
        }
        postRepository.save(post);
    }

    public void updatePost(Integer postId  ,Post post) {
        Post p = postRepository.findPostByPostId(postId);
        if(p==null){
            throw new ApiException("Post not found");
        }
        p.setCategoryId(post.getCategoryId());
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        p.setUserId(post.getUserId());
        p.setPublishDate(post.getPublishDate());
        postRepository.save(p);
    }

    public void deletePost(Integer postId ) {
        Post p = postRepository.findPostByPostId(postId);
        if(p==null){
            throw new ApiException("Post not found");
        }
        postRepository.delete(p);
    }

    public List<Post> getPostByUserId(Integer userId) {
       List<Post> posts = postRepository.findPostByUserId(userId);
     if(posts.isEmpty()){
         throw new ApiException("Post by userId not found");
     }
     return posts;
    }

    public Post getPostByTitle(String title) {
        Post p = postRepository.findPostByTitle(title);
        if(p==null){
            throw new ApiException("Post by title not found");
        }
        return p;
    }

    public List<Post> getPostByDate(Date publishDate){
        List<Post> posts=postRepository.findPostByPublishDateBeforeDate(publishDate);
        if(posts.isEmpty()){
            throw new ApiException("Post by date not found");
        }
        return posts;
    }


}
