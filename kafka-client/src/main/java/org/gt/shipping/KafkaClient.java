package org.gt.shipping;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaClient implements MessagingClient {

    private Properties kafkaConfig;
    private KafkaProducer<String, String> kafkaProducer; //TODO create provider

    public KafkaClient() {
        kafkaConfig = new Properties();

        kafkaConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); //TODO read kafka host from config
        kafkaConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //Create Producer
        kafkaProducer = new KafkaProducer<>(kafkaConfig);
    }

    @Override
    public void sendMessage(String content, MessageMetaData metaData) {
        //TODO Topic name to be derived from metadata
        kafkaProducer.send(new ProducerRecord<>("test", content));
    }

    @Override
    public void close() {
        if (kafkaProducer != null) {
            kafkaProducer.flush();
            kafkaProducer.close();
        }
    }


}
