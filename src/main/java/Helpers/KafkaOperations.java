package Helpers;

import entity.Cat;
import entity.Dog;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class KafkaOperations {

    private final static String URI ="192.168.1.143:9092";

    /**
     * Генерация данных для Kafka.
     * @param key ключ сообщений
     */
    public static void produce(String key) {
        Cat john = new Cat("John", 5.3f);
        Dog ben = new Dog("Ben", 12);
        Producer<String, String> producer = KafkaAccess.getProducer();
        producer.send(new ProducerRecord<>("my-topic", key,
                JsonHelper.getJson(john)));
        producer.send(new ProducerRecord<>("my-topic", key,
                JsonHelper.getJson(ben)));
        producer.close();
    }

    /**
     * Чтение данных из Kafka в коллекцию.
     * @param key Ключ сообщений
     * @return коллекция сообщений с заданным кючем
     */
    public static ArrayList<String> getMessages(String key) {
        KafkaConsumer consumer = KafkaAccess.getConsumer();
        consumer.subscribe(Arrays.asList("my-topic"));
        ConsumerRecords<String, String> records;
        ArrayList<String> result = new ArrayList<>();
        do {
            do {
                records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    if (record.key().equals(key))
                        result.add(record.value());
                }
            } while (records.count() > 0); //пока есть сообщения в Kafka
        } while (0 == result.size()); //пока не дождемся нужного ключа
        consumer.close();
        return result;
    }

}
