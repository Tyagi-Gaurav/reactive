package org.gt.shipping;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaClient implements MessagingClient {

    private KafkaProducer<String, String> kafkaProducer;

    public KafkaClient(String bootStrapHost) {
        Properties kafkaConfig = new Properties();

        kafkaConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapHost);
        kafkaConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //Create Producer
        kafkaProducer = new KafkaProducer<>(kafkaConfig);
    }

    @Override
    public void sendMessage(String content, MessageMetaData metaData) {
        //TODO Topic name to be derived from metadata
        kafkaProducer.send(new ProducerRecord<>("new_topic", content));
    }

    @Override
    public void close() {
        if (kafkaProducer != null) {
            kafkaProducer.flush();
            kafkaProducer.close();
        }
    }


}
