package com.weavus.weatube.controller;

import com.weavus.weatube.dto.UserDto;
import com.weavus.weatube.entity.User;
import com.weavus.weatube.repo.UserRepo;
import com.weavus.weatube.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserRepo userRepo;
    private final LoginService loginService;

    @GetMapping("/login")
    private String login(){

        return "login";
    }

    @PostMapping("/login")
    private String login(String id, String password, Model model, HttpServletRequest request){
       User user = userRepo.findByIdAndPassword(id, password);

       if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            log.info("user={}", user);
            return "redirect:/";
        } else {
            model.addAttribute("msg", "아이디 또는 비밀번호를 확인해 주세요.");
            return "login";
        }
    }

    @GetMapping("/logout")
    private String logout(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        session.invalidate();
        model.addAttribute("msg", "로그아웃 되었습니다.");
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String moveSignup(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(UserDto userDto, Model model){
        boolean result = loginService.signup(userDto);
        if (result){
//            model.addAttribute("msg","회원가입이 완료 되었습니다.");
            model.addAttribute("message", "회원가입이 완료되었습니다.");
            model.addAttribute("searchUrl", "/login");
            return "message";
        }else {
            model.addAttribute("msg", "한번 더 확인해 주세요.");

            return "signup";
        }
    }

    @GetMapping("/idCheck")//카멜식 바꾸기
    private String moveIdCheck(){
        return "idCheck";
    }

    @PostMapping("/idCheck")
    private String idCheck(String id, Model model){
//        User user = userRepo.findById(id);
//        if (user != null){
//            model.addAttribute("msg","중복된 아이디 입니다.");
//        }else {
//            model.addAttribute("msg","사용 가능한 아이디 입니다.");
//        }
        return "idCheck";
    }
}