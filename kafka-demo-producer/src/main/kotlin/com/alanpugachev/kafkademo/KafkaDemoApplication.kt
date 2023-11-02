  package com.alanpugachev.kafkademo

import com.alanpugachev.kafkademo.config.KafkaProducerConfig
import com.alanpugachev.kafkademo.dto.MessageDto
import org.apache.kafka.clients.producer.KafkaProducer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component

@SpringBootApplication
@EnableKafka
@EnableScheduling
class KafkaDemoApplication

fun main(args: Array<String>) {
    runApplication<KafkaDemoApplication>(*args)
}

@Component
class KafkaProducerEntity() {
    val msgProd = MessageDto (
        message = (0..1000).random().toString()
    )
}
