package com.peddinti.cityservice.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class LoadResources {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	public Path getResourceStream() throws IOException {
		final Resource resource = resourceLoader.getResource("classpath:cities.txt");
			Path path = Paths.get(resource.getURI());
		    return path;
	}
}
