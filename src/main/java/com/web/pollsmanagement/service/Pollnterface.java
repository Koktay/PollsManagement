package com.web.pollsmanagement.service;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Map;

public interface Pollnterface {

    Map<Integer, String> buscar() throws UnirestException;

}
