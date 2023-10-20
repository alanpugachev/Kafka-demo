package com.alanpugachev.kafkademo.serialize

import com.alanpugachev.kafkademo.dto.MessageDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Serializer

class MessageSerializer: Serializer<MessageDto> {
    val objectMapper = ObjectMapper()

    override fun serialize(topic: String?, data: MessageDto?): ByteArray {
        return objectMapper.writeValueAsBytes(data)
    }
}