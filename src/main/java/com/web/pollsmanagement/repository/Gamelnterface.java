package com.web.pollsmanagement.repository;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Map;

public interface Gamelnterface {

    Map<Integer, String> buscar() throws UnirestException;

}
