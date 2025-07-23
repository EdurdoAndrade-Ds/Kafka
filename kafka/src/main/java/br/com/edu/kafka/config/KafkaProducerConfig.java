package br.com.edu.kafka.config;

import br.com.edu.kafka.record.OrderRecord;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducerConfig {

    @Value(value = "{spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    // record esta aq apenas para estudo, ele esta simulando um dto
    public ProducerFactory<String, OrderRecord> orderRecordProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        // para evitar usar o cabecalho do Kafka
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // class -> json e salva em json
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    // cria o template di kafka
    @Bean
    public KafkaTemplate<String, OrderRecord> orderkafkaTemplate() {
        return new KafkaTemplate<>(orderRecordProducerFactory());
    }
}
