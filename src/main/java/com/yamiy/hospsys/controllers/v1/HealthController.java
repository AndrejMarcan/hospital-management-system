package com.yamiy.hospsys.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class HealthController {
	private final BuildProperties buildProperties;
	
	public HealthController(@Autowired BuildProperties buildProperties) {
		super();
		this.buildProperties = buildProperties;
	}
	
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public BuildProperties healthcheck() {
        return buildProperties;
    }
}
