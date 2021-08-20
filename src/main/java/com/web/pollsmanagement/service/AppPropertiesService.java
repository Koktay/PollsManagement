package com.web.pollsmanagement.service;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "appPropertiesService")
@ApplicationScoped
@Service
@ConfigurationProperties(prefix="app")
public class AppPropertiesService {

	@Getter	@Setter
	private String url;

}
