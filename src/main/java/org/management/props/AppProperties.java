package org.management.props;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "user-api")
@Configuration
public class AppProperties {

	Map<String, String> messages = new HashMap<>();

	public Map<String, String> getMessages() {
		return messages;
	}
	
	
	

}
