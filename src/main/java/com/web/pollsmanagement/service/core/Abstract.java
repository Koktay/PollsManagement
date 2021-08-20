package com.web.pollsmanagement.service.core;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.pollsmanagement.response.ResponseWS;
import com.web.pollsmanagement.service.AppPropertiesService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.faces.bean.ManagedProperty;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Abstract {

	@Getter
	@Setter
	@Autowired
	protected AppPropertiesService appPropertiesService;



	protected Object post() {

		String url = appPropertiesService.getUrl();
		HttpEntity<Object> request = new HttpEntity<>(postHeader());

		ResponseWS response = new RestTemplate().postForObject(url, request, ResponseWS.class);
		if (isArrayList(response)) {
			return listConverter((List<?>) response.getResult());
		}
		return Optional.of(converter(response.getResult())).orElse(null);
	}

	private MultiValueMap<String, String> postHeader() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Client-ID", "w5o4sm8h87mngl0lb9lag0vb3dare1");
		headers.add("Authorization", "Bearer q3qi6v21hir5bdiei8yf7d2pu1vlt7");
		return headers;
	}

	private boolean isArrayList(ResponseWS responseWS) {
		return responseWS.getResult().getClass().getTypeName().equals("java.util.ArrayList");
	}

	private List<?> listConverter(List<?> results) {
		return results.stream().map(this::converter).collect(Collectors.toList());
	}

	private Object converter(Object object) {
		return new ObjectMapper().convertValue(object, (Class<?>) com.web.pollsmanagement.model.Game.class);
	}

}
