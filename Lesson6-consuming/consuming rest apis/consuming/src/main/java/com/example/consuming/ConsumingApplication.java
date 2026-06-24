package com.example.consuming;

import com.example.consuming.entity.DictionaryEntry;
import com.example.consuming.entity.Joke;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumingApplication {

	private static final Logger log = LoggerFactory.getLogger(ConsumingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumingApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Joke joke = restTemplate.getForObject(
					"https://official-joke-api.appspot.com/random_joke", Joke.class);
			log.info(joke.toString());

			DictionaryEntry[] dictionaryEntry = restTemplate.getForObject(
					"https://api.dictionaryapi.dev/api/v2/entries/en/hello", DictionaryEntry[].class);
			Arrays.asList(dictionaryEntry).forEach(entry -> log.info(entry.toString()));
		};
	}


}
