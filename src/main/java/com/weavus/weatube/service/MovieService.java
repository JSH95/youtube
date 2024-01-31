package com.weavus.weatube.service;

import com.weavus.weatube.entity.Movie;
import com.weavus.weatube.repo.MovieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepo movieRepo;

    public boolean write(Movie movie) { //영상 저장 서비스

        movie.setFilePath("https://img.youtube.com/vi/" + movie.getFileUrl() + "/0.jpg"); //유튜브 썸네일 저장
        movie.setFileUrl("https://youtube.com/embed/" + movie.getFileUrl()); //유튜브 영상 url저장
        try {
            movieRepo.save(movie);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean modify(Movie movie) {
        try {
            movieRepo.save(movie);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
