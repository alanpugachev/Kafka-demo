package com.alanpugachev.kafkademo.serialize

import com.alanpugachev.kafkademo.dto.MessageDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer

class MessageSerializer: Serializer<MessageDto> {
    private val objectMapper = ObjectMapper()

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {
        super.configure(configs, isKey)
    }

    override fun serialize(topic: String?, data: MessageDto?): ByteArray? {
        try {
            if (data == null) {
                println("Null received at serializing")
                return null
            }

            println("Serializing...")
            return objectMapper.writeValueAsBytes(data)
        } catch (e: Exception) {
            throw SerializationException("Error when serializing MessageDto")
        }
    }
}