package com.example.lap11.Controller;

import com.example.lap11.Model.Post;
import com.example.lap11.Model.User;
import com.example.lap11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity getPost() {
        return ResponseEntity.status(200).body(postService.getPost());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post , Errors errors ) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postService.addPost(post);
        return ResponseEntity.status(200).body("Post Added");
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity updatePost(@PathVariable Integer postId,@Valid @RequestBody  Post post, Errors errors ){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postService.updatePost(postId,post);

        return ResponseEntity.status(200).body("Post Updated");
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(200).body("Post Deleted");

    }
    @GetMapping("/get/post/{userId}")
    public ResponseEntity getPostByUserId(@PathVariable Integer userId){
        List<Post> posts = postService.getPostByUserId(userId);
        return ResponseEntity.status(200).body(posts);

    }
    @GetMapping("/get/title/{title}")
    public ResponseEntity getPostByTitle(@PathVariable String title){
    return ResponseEntity.status(200).body(postService.getPostByTitle(title));
    }

    @GetMapping("/get/date/{publishDate}")
    public ResponseEntity getPostByDate(@PathVariable Date publishDate){
        List<Post> posts = postService.getPostByDate(publishDate);
        return ResponseEntity.status(200).body(posts);
    }



}
