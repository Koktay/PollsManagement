package com.web.pollsmanagement.repository;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Map;

public interface IGDBInterface {

    Map<Integer, String> buscar() throws UnirestException;

}
