package io.pivotal.pace;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@RestController
@RefreshScope
public class SBController {
	
	private GreetingRepository greetingRepository;
	
	@Value("${greetingLanguage}")
	private String language;
	
	public SBController(GreetingRepository greetingRepository) {
		this.greetingRepository = greetingRepository;
	}
	
	@RequestMapping("/")
	public String greetingLanguage() {
		return "Greeting language is " + language;
	}
	
	@RequestMapping("/greeting")
	public String greeting() {
		List<Greeting> greeting = greetingRepository.findByLanguage(language);
		if (greeting.isEmpty()) 
			return "Greeting not found for " + language;
		else
			return greeting.get(0).getText();
	}

	@RequestMapping("/language")
	public String language() {
		return language;
	}
}
