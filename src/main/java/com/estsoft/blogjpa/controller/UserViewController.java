package com.estsoft.blogjpa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class UserViewController extends HttpServlet {

    @GetMapping("/login")
    public String login(){
        return "login"; //login.html
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup"; //signup.html
    }
}
