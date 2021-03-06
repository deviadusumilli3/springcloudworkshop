package io.pivotal.pal.tracker.restsupport;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.EurekaClient;


@Configuration
public class RestConfig {
	
	private final EurekaClient eurekaClient;

	public RestConfig(EurekaClient eurekaClient) {
	    this.eurekaClient = eurekaClient;
	}
	@Bean
	ServiceLocator getServiceLocator() {
	    return new EurekaServiceLocator(eurekaClient);
	}

	@LoadBalanced
    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
}
