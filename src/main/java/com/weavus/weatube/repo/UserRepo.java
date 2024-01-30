package com.weavus.weatube.repo;

import com.weavus.weatube.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, String> {
    User findByIdAndPassword(String id, String password);

    @Query("SELECT distinct u FROM User u join u.movieList")
    List<User> findUserAndMovie();


}