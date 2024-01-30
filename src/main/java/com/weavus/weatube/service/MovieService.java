package com.weavus.weatube.service;

import com.weavus.weatube.dto.HeartDto;
import com.weavus.weatube.entity.Heart;
import com.weavus.weatube.entity.Movie;
import com.weavus.weatube.repo.HeartRepo;
import com.weavus.weatube.repo.MovieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepo movieRepo;
    private final HeartRepo heartRepo;

    public boolean write(Movie movie) throws Exception {
//        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\";
//
//        UUID uuid = UUID.randomUUID();

//        String fileName = uuid + "_" + file.getOriginalFilename();

//        File saveFile = new File(projectPath, fileName);

//        file.transferTo(saveFile);


        movie.setFilePath("https://img.youtube.com/vi/" + movie.getFileUrl() + "/0.jpg");
        movie.setFileUrl("https://youtube.com/embed/" + movie.getFileUrl());
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
