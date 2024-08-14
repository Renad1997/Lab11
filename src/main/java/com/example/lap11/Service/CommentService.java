package com.example.lap11.Service;

import com.example.lap11.Api.ApiException;
import com.example.lap11.Model.Category;
import com.example.lap11.Model.Comment;
import com.example.lap11.Model.Post;
import com.example.lap11.Model.User;
import com.example.lap11.Repository.CommentRepository;
import com.example.lap11.Repository.PostRepository;
import com.example.lap11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<Comment> getComment() {
        return commentRepository.findAll();
    }

    public void addComment(Comment comment) {
        User u = userRepository.findUserByUserId(comment.getUserId());
        Post p = postRepository.findPostByPostId(comment.getPostId());
        if(u==null || p==null){
            throw new ApiException("UserId or PostId not found");
        }
        commentRepository.save(comment);
    }

    public void updateComment(Integer commentId  ,Comment comment) {
        Comment c = commentRepository.findCommentByCommentId(commentId);
        if(c==null){
            throw new ApiException("Comment not found");
        }
        c.setUserId(comment.getUserId());
        c.setPostId(comment.getPostId());
        c.setContent(comment.getContent());
        c.setCommentDate(comment.getCommentDate());
        commentRepository.save(c);
    }

    public void deleteComment(Integer commentId ) {
        Comment c = commentRepository.findCommentByCommentId(commentId);
        if(c==null){
            throw new ApiException("Comment not found");
        }
        commentRepository.delete(c);
    }

    public List<Comment> getCommentByPostId(Integer postId) {
        List<Comment> comments = commentRepository.findCommentByPostId(postId);
        if(comments.isEmpty()){
            throw new ApiException("Comment by PostId not found");
        }
        return comments;

    }

    public Comment getCommentByContent(String content) { // 2-extra endpoint
        Comment c= commentRepository.pleaseSearchByContent(content);
        if(c==null){
            throw new ApiException("Comment by content not found");
        }
        return c;
    }

}
