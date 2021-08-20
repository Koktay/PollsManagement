package com.web.pollsmanagement.view;

import com.web.pollsmanagement.service.Pollnterface;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("view")
public class PollMB {

    @Getter
    @Setter
    @Autowired
    private Pollnterface pollnterface;

    @Getter
    @Setter
    private String titulo;

    @PostConstruct
    public void init(){
        pollnterface.buscar();
    }

}
