package io.pivotal.pal.tracker.timesheets;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestOperations;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

public class ProjectClient {

    private final RestOperations restOperations;
    private final ConcurrentMap<Long, ProjectInfo> cache;
    private final Logger logger = LoggerFactory.getLogger(ProjectClient.class);

    public ProjectClient(RestOperations restOperations) {
    	this.restOperations = restOperations;
    	 cache = new ConcurrentHashMap<>();
    }

    @HystrixCommand(fallbackMethod = "getProjectFromCache")
    public ProjectInfo getProject(long projectId) {
//    	String endpoint = serviceLocator.lookUpServiceUrl("registration-server");
    	
        return restOperations.getForObject("http://registration-server" + "/projects/" + projectId, ProjectInfo.class);
    }
    
    public ProjectInfo getProjectFromCache(long projectId) {
        logger.info("Fell back to using cached project lookup for projectId '{}'", projectId);
        return cache.get(projectId);
    }
}
