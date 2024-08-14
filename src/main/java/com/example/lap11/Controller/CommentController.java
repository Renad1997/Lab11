package com.example.lap11.Controller;

import com.example.lap11.Model.Comment;
import com.example.lap11.Model.Post;
import com.example.lap11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity getComment() {
        return ResponseEntity.status(200).body(commentService.getComment());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@Valid @RequestBody Comment comment, Errors errors ) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.addComment(comment);
        return ResponseEntity.status(200).body("Comment Added");
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity updateComment(@PathVariable Integer commentId,@Valid @RequestBody  Comment comment, Errors errors ){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.updateComment(commentId,comment);

        return ResponseEntity.status(200).body("Comment Updated");
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(200).body("Comment Deleted");
    }
    @GetMapping("/get/comment/{postId}")
    public ResponseEntity getCommentByPostId(@PathVariable Integer postId) {
        List<Comment> comments = commentService.getCommentByPostId(postId);
       return ResponseEntity.status(200).body(comments);
    }

    @GetMapping("/get/content/{content}")
    public ResponseEntity getCommentByContent(@PathVariable String content) { // 2-extra endpoint
     return ResponseEntity.status(200).body(commentService.getCommentByContent(content));
    }

}
