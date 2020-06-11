package com.opensystem.kafka.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opensystem.kafka.model.User;

@RestController
@RequestMapping("kafka")
public class UserResource {

	@Autowired
	KafkaTemplate<String, String> kafkaStringTemplate;
	
	@Autowired
	KafkaTemplate<String, User> kafkaTemplateUser;
	
	private static final String TOPIC_STRING = "Kafka_Demo";
	private static final String TOPIC_USER = "Kafka_Demo_User";
	
	@GetMapping("/publish/{message}")
	public String publishString(@PathVariable("message") final String message) {
		kafkaStringTemplate.send(TOPIC_STRING, message);
		return "Published successfully!";
	}
	
	@PostMapping("/publish")
	public void pusblishUser(@RequestBody User user) {
		kafkaTemplateUser.send(TOPIC_USER, user);
	}
}