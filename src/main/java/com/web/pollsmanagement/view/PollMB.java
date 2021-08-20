package com.web.pollsmanagement.view;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.web.pollsmanagement.service.Pollnterface;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
@Scope("view")
public class PollMB {

    @Getter
    @Setter
    @Autowired
    private Pollnterface pollnterface;

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
    private String selectedGame;

    @PostConstruct
    public void init() throws UnirestException {
        games = pollnterface.buscar();
        keys.addAll(games.keySet());
    }

    public void salvarGame(){
        System.out.println(selectedGame);
    }

    public void salvarTitulo(){
        System.out.println(titulo);
    }


}
