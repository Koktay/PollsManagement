package com.web.pollsmanagement.service.core;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.web.pollsmanagement.service.AppPropertiesService;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public abstract class Abstract {

	@Getter
	@Setter
	@Autowired
	protected AppPropertiesService appPropertiesService;



	protected Map<Integer, String> post() throws UnirestException {

		Map<Integer, String> games = new HashMap<>();

		HttpResponse<JsonNode> jsonResponse = Unirest.post(appPropertiesService.getUrl())
				.header("Client-ID", appPropertiesService.getId())
				.header("Authorization", appPropertiesService.getAuth())
				.header("Accept", "application/json")
				.body("fields name;")
				.asJson();

		JSONArray json = jsonResponse.getBody().getArray();
		for(int i=0;i<json.length();i++){
			String nome = json.getJSONObject(i).getString("name");
			Integer id = json.getJSONObject(i).getInt("id");
			games.put(id, nome);
		}

		return games;
	}

}
