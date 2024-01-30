package com.weavus.weatube.repo;

import com.weavus.weatube.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, String> {

    List<Movie> findByMovieStatus(String status);

    List<Movie> findByCategoryAndMovieStatus(String num, String status);

    List<Movie> findByUserId(String id);
    Movie findById(Integer id);

}