package me.ryeong.ggjm.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("loginForm", new LoginForm());

        return "members/login";
    }


    @Getter
    @Setter
    static class LoginForm {

        @NotBlank(message = "아이디는 필수값입니다.")
        private String username;

        @NotBlank(message = "비밀번호는 필수값입니다.")
        private String password;

    }


}
