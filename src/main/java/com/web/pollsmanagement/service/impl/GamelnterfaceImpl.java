package com.web.pollsmanagement.service.impl;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.web.pollsmanagement.repository.Gamelnterface;
import com.web.pollsmanagement.service.core.Abstract;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class GamelnterfaceImpl extends Abstract implements Gamelnterface {

    @Override
    public Map<Integer, String> buscar() throws UnirestException {

        return post();

    }
}