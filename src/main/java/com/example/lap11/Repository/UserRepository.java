package com.example.lap11.Repository;

import com.example.lap11.Model.User;
import com.example.lap11.Service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findUserByUserId(Integer userId);

 @Query("select u from User u where u.email=?1") //3-extra endpoint
  User pleaseSearchUserByEmail(String email);


 @Query("select u from User u where u.username=?1") //4-extra endpoint
 List<User> pleaseSearchUserByUsername(String username);





}
