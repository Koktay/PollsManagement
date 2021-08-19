package com.web.pollsmanagement;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

import javax.servlet.ServletContext;

@RewriteConfiguration
public class FacesRewriteConfigurationProvider extends HttpConfigurationProvider {

	@Override
	public int priority(){
		return 10;
	}

	@Override
	public Configuration getConfiguration(final ServletContext context){
		return ConfigurationBuilder.begin()
				.addRule(Join.path("/index").to("/index.jsf"));
	}
}
