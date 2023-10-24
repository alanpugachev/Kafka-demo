package com.alanpugachev.kafkademo.serialize

import com.alanpugachev.kafkademo.dto.MessageDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer

class MessageDeserializer: Deserializer<MessageDto> {
    private val objectMapper = ObjectMapper()

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {
        super.configure(configs, isKey)
    }

    override fun deserialize(topic: String?, data: ByteArray?): MessageDto? {
        try {
            if (data == null) {
                println("Null received at deserializing")
                return null
            }

            println("Deserializing...")
            return objectMapper.readValue(String(data), MessageDto::class.java)
        } catch (e: Exception) {
            throw SerializationException("Error when deserializing MessageDto")
        }
    }
}