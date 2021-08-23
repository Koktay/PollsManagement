package com.web.pollsmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    static final String INDEX = "index.xhtml";
    static final String CRIAR = "criarPoll.xhtml";
    static final String VOTAR = "votarPoll.xhtml";
    static final String VER = "verPoll.xhtml";
    static final String HISTORICO = "historico.xhtml";

    @GetMapping
    public String init(){
        return INDEX;
    }

    @RequestMapping("/criar")
    public String criar(){
        return CRIAR;
    }

    @RequestMapping("/votar")
    public String votar(){
        return VOTAR;
    }

    @RequestMapping("/ver")
    public String ver(){
        return VER;
    }

    @RequestMapping("/historico")
    public String historico(){
        return HISTORICO;
    }
}
