package com.alanpugachev.kafkademo.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

data class MessageDto(
    var message: String = "",
    var id: String = (0..1000000).random().toString()
)