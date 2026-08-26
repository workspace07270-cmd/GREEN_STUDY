package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Spring Boot 개발환경 테스트");
        model.addAttribute("message", "Spring Boot + Thymeleaf 환경 설정이 완료되었습니다!");
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("springProfile", "default");
        return "index";
    }
}
