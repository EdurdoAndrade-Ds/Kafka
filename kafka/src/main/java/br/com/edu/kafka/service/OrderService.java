package br.com.edu.kafka.service;

import br.com.edu.kafka.record.OrderRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.Random;

@Service
public class OrderService {
    private final KafkaTemplate<String, OrderRecord> kafkaTemplateOrder;

    private final Random random = new Random();

    public OrderService(KafkaTemplate<String, OrderRecord> kafkaTemplateOrder) {
        this.kafkaTemplateOrder = kafkaTemplateOrder;
    }

    // estou mandando como random, mas tbm pode criar uma forma sequencial ou load balance
    @SuppressWarnings("null")
    public void sendMessageOrder(OrderRecord order) {
        int partition = random.nextInt(2);
        System.out.println("Sent message to partition: " + partition);
        System.out.println("Sent Order: " + order);
        kafkaTemplateOrder.send("order-processed", partition, null, order);
    }
}
