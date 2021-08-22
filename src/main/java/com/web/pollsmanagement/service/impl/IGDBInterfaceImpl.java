package com.web.pollsmanagement.service.impl;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.web.pollsmanagement.repository.IGDBInterface;
import com.web.pollsmanagement.repository.JogoRepository;
import com.web.pollsmanagement.service.core.Abstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class IGDBInterfaceImpl extends Abstract implements IGDBInterface {

    @Autowired
    public JogoRepository jogoRepository;

    @Override
    public Map<Integer, String> buscar() throws UnirestException {

        return post();

    }
}