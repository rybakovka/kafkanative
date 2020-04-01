package Helpers;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

public class KafkaAccess {

    private final static String URI ="192.168.1.143:9092";

    public static KafkaConsumer<String, String> getConsumer() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", URI);
        props.setProperty("group.id", DataHelper.newRqId());
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "100");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return new KafkaConsumer<>(props);
    }

    public static Producer<String, String> getProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", URI);
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new KafkaProducer<>(props);
    }


}
