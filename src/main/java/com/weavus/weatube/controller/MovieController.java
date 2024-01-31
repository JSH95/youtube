package com.weavus.weatube.controller;

import com.weavus.weatube.dto.MovieDto;
import com.weavus.weatube.entity.Heart;
import com.weavus.weatube.entity.Movie;
import com.weavus.weatube.repo.HeartRepo;
import com.weavus.weatube.repo.MovieRepo;
import com.weavus.weatube.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MovieController {

    private final MovieService movieService;
    private final MovieRepo movieRepo;
    private final HeartRepo heartRepo;

@GetMapping("/{num}") //카테고리 정리
    public String mainP(@PathVariable String num, Model model) {

            List<Movie> movies = movieRepo.findByCategoryAndMovieStatus(num, "1");
            model.addAttribute("movies", movies);
            return "index";
    }

    @GetMapping("/") //기본 메인화면
    public String mainP(Model model){
        List<Movie> movies = movieRepo.findByMovieStatus("1");
        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("detail/{id}") //영상 상세화면 불러오기
    public String detail(@PathVariable Integer id, Model model){

        Movie movie = movieRepo.findById(id);
        List<Heart> heartInfo = heartRepo.findByMovieId(movie.getId());
        model.addAttribute("heartInfo", heartInfo);
        model.addAttribute("detail", movie);
        return "detail";
    }

    @GetMapping("movieSave/{id}") //영상 저장
    public String WriteForm() {
        return "movieSave";
    }

    @PostMapping("movieSave")
    public String boardWritePro(Movie movie, Model model) throws Exception {
        log.info("movie={}", movie);
       boolean result = movieService.write(movie);
        if(result) {
            model.addAttribute("msg","영상 등록 완료");
            return "redirect:/";
        } else {
            model.addAttribute("msg", "한번 더 확인해 주세요.");
            return "movieSave";
        }
    }

    @GetMapping("movieModify/{id}") //영상 수정
    public String modify(@PathVariable Integer id, Model model){

        Movie movie = movieRepo.findById(id);
        model.addAttribute("modify", movie);
        return "movieModify";
    }

    @PostMapping("movieModify")
    public String modifyPro(Movie movie, Model model, MovieDto movieDto) {

        boolean result = movieService.modify(movie);

        if(result) {
            model.addAttribute("msg", "영상수정완료");
            return "redirect:/detail/" + movie.getId();
        } else {
            model.addAttribute("message", "한번 더 확인해 주세요.");
            model.addAttribute("searchUrl", "/movieModify/" + movie.getId());
            return "message";
        }
    }

    @GetMapping("delete/{id}") //영상 비공개, 공개
    public String delete(@PathVariable Integer id){
        Movie movie = movieRepo.findById(id);
        if(movie.getMovieStatus().equals("1")){
            movie.setMovieStatus("0");
            movieRepo.save(movie);
        } else {
            movie.setMovieStatus("1");
            movieRepo.save(movie);
        }
        return "redirect:/";
    }

}
