package com.web.pollsmanagement.view;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.web.pollsmanagement.model.Poll;
import com.web.pollsmanagement.model.Usuario;
import com.web.pollsmanagement.repository.Gamelnterface;
import com.web.pollsmanagement.service.PollService;
import com.web.pollsmanagement.service.UsuarioService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Scope("view")
public class PollMB {

    @Getter
    @Setter
    @Autowired
    private Gamelnterface gamelnterface;

    @Getter
    @Setter
    private Map<Integer, String> games;

    @Getter
    @Setter
    private List<Integer> keys = new ArrayList<>();

    @Getter
    @Setter
    private String titulo;

    @Getter
    @Setter
    private Poll poll = new Poll();

    @Autowired
    public PollService pollService;

    @Autowired
    public UsuarioService userService;

    @Getter
    @Setter
    private Usuario user = new Usuario();

    @PostConstruct
    public void init() throws UnirestException {
        games = gamelnterface.buscar();
        keys.addAll(games.keySet());
        user = userService.findUser();
    }

    public void votar(){
        System.out.println(user.getNome());
//        poll.setVoto(1);
//        poll.setUsuarios();
//        service.savePoll(poll);
    }

    public void criar(){
        System.out.println(poll.getCategoria());
    }


}
