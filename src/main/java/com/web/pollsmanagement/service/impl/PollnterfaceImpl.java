package com.web.pollsmanagement.service.impl;

import com.web.pollsmanagement.model.Game;
import com.web.pollsmanagement.service.Pollnterface;
import com.web.pollsmanagement.service.core.Abstract;

public class PollnterfaceImpl extends Abstract implements Pollnterface  {

    @Override
    public Game buscar() {

        return (Game) post();

    }
}