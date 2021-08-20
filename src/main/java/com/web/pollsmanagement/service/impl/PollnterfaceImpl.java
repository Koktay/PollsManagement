package com.web.pollsmanagement.service.impl;

import com.web.pollsmanagement.model.Game;
import com.web.pollsmanagement.service.Pollnterface;
import com.web.pollsmanagement.service.core.Abstract;
import org.springframework.stereotype.Service;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Service
public class PollnterfaceImpl extends Abstract implements Pollnterface  {

    @Override
    public Game buscar() {

        return (Game) post();

    }
}