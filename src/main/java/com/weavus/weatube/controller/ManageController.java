package com.weavus.weatube.controller;

import com.weavus.weatube.entity.Heart;
import com.weavus.weatube.entity.Movie;
import com.weavus.weatube.repo.HeartRepo;
import com.weavus.weatube.repo.MovieRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ManageController {

    private final MovieRepo movieRepo;
    private final HeartRepo heartRepo;

    @GetMapping("movieManage/{id}")
    public String manage(@PathVariable String id, Model model){
        List<Movie> movies = movieRepo.findByUserId(id);
        model.addAttribute("movies", movies);
        return "movieManage";
    }

    @PostMapping("addHeart")
    public String addItemToCart(@RequestParam Integer movie_id, Model model, Heart heart) {

        heart.setMovieId(movie_id);
        try {
            heartRepo.save(heart);
            model.addAttribute("message", "영상에 좋아요를 눌렀습니다.");
            model.addAttribute("searchUrl", "detail/" + heart.getMovieId());
            return "message";
        } catch (Exception e){
            model.addAttribute("message", "다시 시도해 주세요");
            return "message";
        }
    }

}
