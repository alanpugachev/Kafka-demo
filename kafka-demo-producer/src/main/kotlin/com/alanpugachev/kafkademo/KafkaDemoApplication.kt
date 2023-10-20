  package com.alanpugachev.kafkademo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

  @SpringBootApplication
@EnableKafka
@EnableScheduling
class KafkaDemoApplication

fun main(args: Array<String>) {
	runApplication<KafkaDemoApplication>(*args)
}

@Component
class KafkaProducerEntity(
  private val kafkaTemplate: KafkaTemplate<String, Any>
) {
  @Scheduled(fixedDelay = 3000)
  fun sendMessage() {
	  kafkaTemplate.send("demo_topic", "aboba")
  }
}
