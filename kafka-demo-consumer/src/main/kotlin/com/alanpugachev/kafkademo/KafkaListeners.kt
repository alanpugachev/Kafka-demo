package com.alanpugachev.kafkademo

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaListeners {

    @KafkaListener(
        topics = arrayOf("demo_topic"),
        groupId = "groupId"
    )
    fun listener(data: String) {
        println("Listener received: $data")
    }
}