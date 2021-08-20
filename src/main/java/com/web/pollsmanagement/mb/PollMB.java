package com.web.pollsmanagement.mb;

import com.web.pollsmanagement.service.Pollnterface;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "pollMB")
@ViewScoped
public class PollMB {

    @Getter
    @Setter
    @ManagedProperty(value = "#{pollService}")
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
