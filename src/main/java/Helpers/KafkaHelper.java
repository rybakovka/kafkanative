package Helpers;

import entity.Cat;
import entity.Dog;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class KafkaHelper {

    private final static String URI ="192.168.1.143:9092";

    /**
     * Генерация данных для Kafka.
     * @param key ключ сообщений
     */
    public static void produce(String key) {
        Properties props = new Properties();
        props.put("bootstrap.servers", URI);
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        Cat john = new Cat("John", 5.3f);
        Dog ben = new Dog("Ben", 12);

        producer.send(new ProducerRecord<>("my-topic", key,
                JsonHelper.getJson(john)));
        producer.send(new ProducerRecord<>("my-topic", key,
                JsonHelper.getJson(ben)));
        producer.close();
    }

    /**
     * Чтение данных из Kafka в коллекцию.
     * @param key Ключ тообщений
     * @return коллекция сообщений сзада
     */
    public static ArrayList<String> getMessages(String key) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", URI);
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "100");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList("my-topic"));
        ArrayList<String> result = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                if(record.key().equals(key))
                    result.add(record.value());
            }
            if (records.count() > 0) break;
        }
        return result;
    }

}
