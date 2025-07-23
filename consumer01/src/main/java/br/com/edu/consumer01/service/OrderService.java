package br.com.edu.consumer01.service;

import br.com.edu.consumer01.record.OrderRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @KafkaListener(topicPartitions = @TopicPartition(topic = "order-processed", partitions = { "1" }), containerFactory = "orderKafkaListenerContainerFactory")
    public void orderListener(OrderRecord order) {
        System.out.println("Received Message: Consumer 01" + order.name());
    }
}

