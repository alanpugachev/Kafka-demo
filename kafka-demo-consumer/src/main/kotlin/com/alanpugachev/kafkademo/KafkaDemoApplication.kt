package com.alanpugachev.kafkademo

import com.alanpugachev.kafkademo.config.KafkaConsumerConfig
import com.alanpugachev.kafkademo.dto.MessageDto
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*
import java.util.concurrent.atomic.AtomicReference

@SpringBootApplication
class KafkaDemoApplication

fun main(args: Array<String>) {
	runApplication<KafkaDemoApplication>(*args)

	val kafkaConsumerEntity = KafkaConsumerEntity()
	kafkaConsumerEntity.receiveKafkaMessage()
}

@Component
class KafkaConsumerEntity() {
	val consumer = KafkaConsumerConfig().createKafkaConsumer()
	val msgCons = AtomicReference<MessageDto>()

	@Bean
	fun receiveKafkaMessage() {
		consumer.subscribe(listOf("demo_topic"))

		val records: ConsumerRecords<String, MessageDto> = consumer.poll(Duration.ofSeconds(1))
		records.forEach { record ->
			msgCons.set(record.value())
			println("Message received ${record.value()}")
		}
	}
}