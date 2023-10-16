package com.alanpugachev.kafkademo

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class KafkaProducerEntity(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    @Scheduled(fixedDelay = 3000)
    fun sendMessage() {
        kafkaTemplate.send("demo_topic", "aboba")
    }
}