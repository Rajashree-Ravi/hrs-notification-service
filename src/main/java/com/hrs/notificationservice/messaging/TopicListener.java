package com.hrs.notificationservice.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.hrs.notificationservice.models.CustomerDto;
import com.hrs.notificationservice.services.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {

	@Value("${consumer.config.customer.topic.name}")
	private String customerTopicName;

	@Autowired
	NotificationService notificationService;

	@KafkaListener(id = "${consumer.config.customer.topic.name}", topics = "${consumer.config.customer.topic.name}", groupId = "${consumer.config.group-id}")
	public void consume(ConsumerRecord<String, CustomerDto> payload) {
		log.info("Topic : {}", customerTopicName);
		log.info("Key : {}", payload.key());
		log.info("Headers : {}", payload.headers());
		log.info("Partion : {}", payload.partition());
		log.info("Customer : {}", payload.value());

		CustomerDto customer = payload.value();
		notificationService.sendWelcomeEmail(customer);

	}

}