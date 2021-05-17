package com.cv.reporting

import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ReportingConsumerConfig {

    @Value("\${rabbitmq.queue.cv1}")
    private lateinit var queueCv1: String

    @Value("\${rabbitmq.queue.cv2}")
    private lateinit var queueCv2: String

    @Bean
    fun queueCv1(): Queue {
        return Queue(queueCv1, true)
    }

    @Bean
    fun queueCv2(): Queue {
        return Queue(queueCv2, true)
    }
}