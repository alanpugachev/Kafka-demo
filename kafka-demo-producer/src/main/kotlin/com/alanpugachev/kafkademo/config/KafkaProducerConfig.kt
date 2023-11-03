package com.alanpugachev.kafkademo.config

import com.alanpugachev.kafkademo.dto.MessageDto
import com.alanpugachev.kafkademo.serialize.MessageSerializer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig {

    //@Value("\${spring.kafka.bootstrap-servers}")
    //lateinit var bootstrapServers: String
    val bootstrapServers = "localhost:9092"

    @Bean
    fun producerFactory(producerConfig: HashMap<String, String> ): ProducerFactory<String, Any> {
        return DefaultKafkaProducerFactory(
            mapOf(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG to 500,
                ProducerConfig.ACKS_CONFIG to "all",
                ProducerConfig.RETRIES_CONFIG to 2,
            )
        )
    }

    @Bean
    fun createKafkaProducer(): KafkaProducer<String, MessageDto> {
        return KafkaProducer(
            mapOf(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to MessageSerializer::class.java,
                ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG to 500
            )
        )
    }

    @Bean
    fun kafkaTemplate(
        producerFactory: ProducerFactory<String, Any>
    ): KafkaTemplate<String, Any> {
        return KafkaTemplate(producerFactory)
    }
}