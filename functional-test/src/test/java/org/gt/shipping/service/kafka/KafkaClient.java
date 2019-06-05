package org.gt.shipping.service.kafka;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static java.time.temporal.ChronoUnit.SECONDS;

public class KafkaClient {

    private KafkaProducer<String, String> kafkaProducer;

    public void sendMessage(String content) {
        Properties producerConfig = new Properties();

        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //Create Producer
        kafkaProducer = new KafkaProducer<>(producerConfig);

        //TODO Topic name to be derived from metadata
        kafkaProducer.send(new ProducerRecord<>("new_topic", content));
        kafkaProducer.close();
    }

    public void close() {
        if (kafkaProducer != null) {
            kafkaProducer.flush();
            kafkaProducer.close();
        }
    }

    public List<String> consumeMessage(String bootStrapHost, String topicName) {
        Properties consumerConfig = new Properties();

        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapHost);
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group-id");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerConfig);
        consumer.subscribe(Collections.singleton(topicName));

        ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.of(10, SECONDS));
        List<String> messages = new ArrayList<>();
        consumerRecords.forEach(consumerRecord -> messages.add(consumerRecord.value()));

        consumer.close();

        return messages;
    }
}