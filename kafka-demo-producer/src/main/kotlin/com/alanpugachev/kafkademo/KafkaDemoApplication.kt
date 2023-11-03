  package com.alanpugachev.kafkademo

import com.alanpugachev.kafkademo.config.KafkaProducerConfig
import com.alanpugachev.kafkademo.dto.MessageDto
import com.alanpugachev.kafkademo.serialize.MessageSerializer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component

@SpringBootApplication
@EnableKafka
@EnableScheduling
class KafkaDemoApplication

fun main(args: Array<String>) {
    runApplication<KafkaDemoApplication>(*args)

    val kafkaProducerEntity = KafkaProducerEntity()
    kafkaProducerEntity.sendKafkaMessage()
}

@Component
class KafkaProducerEntity() {
    val msgProd = MessageDto (
        message = (0..1000).random().toString()
    )

    val producer = KafkaProducerConfig().createKafkaProducer()

    @Bean
    fun sendKafkaMessage() {
        producer.send(ProducerRecord<String, MessageDto>("demo_topic", "1", msgProd))
        println("Message sent $msgProd")
        producer.close()
    }
}
