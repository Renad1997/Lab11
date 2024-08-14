package com.example.lap11.Repository;

import com.example.lap11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

 Post findPostByPostId(Integer postId);

 List<Post> findPostByUserId(Integer userId);

 Post findPostByTitle(String title);

 List<Post> findPostByPublishDateBeforeDate(Date publishDate);


}
