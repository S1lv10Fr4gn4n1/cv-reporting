package com.cv.reporting

import com.rabbitmq.client.Channel
import java.util.*
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Component
class ReportingConsumer {

    private val logger = LoggerFactory.getLogger(ReportingConsumer::class.java)

    @RabbitListener(queues = ["\${rabbitmq.queue.cv1}", "\${rabbitmq.queue.cv2}"], ackMode = "MANUAL")
    fun receiveQueueCV(message: Message, channel: Channel) {
        message.messageProperties.consumerQueue
        val body = String(message.body)
        val tag = message.messageProperties.deliveryTag
        val queue = message.messageProperties.consumerQueue
        handler(body, channel, queue, tag)
    }

    private fun handler(message: String, channel: Channel, queue: String, tag: Long) {
        logger.info("Received: $queue -> '$message'")
        try {
            val watch = StopWatch()
            watch.start()
            doWork(message, queue)
            watch.stop()
            logger.info("Finished processing $queue -> '$message', time: ${watch.totalTimeMillis}")
        } catch (e: Exception) {
            channel.basicNack(tag, false, true)
            throw e
        }
        channel.basicAck(tag, false)
    }

    private fun doWork(message: String, queue: String) {
        val processingTime = (Random().nextFloat() * 100).toLong()
        logger.info("Processing time ($processingTime): $queue -> '$message'")
        Thread.sleep(processingTime)
    }
}