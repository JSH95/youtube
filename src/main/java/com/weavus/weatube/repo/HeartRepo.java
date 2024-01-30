package com.weavus.weatube.repo;

import com.weavus.weatube.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepo extends JpaRepository<Heart, String> {
    List<Heart> findByMovieId(Integer id);
}
