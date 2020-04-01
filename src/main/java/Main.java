import Helpers.DataHelper;
import Helpers.KafkaOperations;
import entity.Dog;

import java.util.List;

/**
 * Производится запись данных в Kafka с ключем,
 * эти данные считываются по этому же ключу,
 * кладутся в коллекцию, затем производится десериализация.
 * Если json соответствует структуре класса Dog, он десериализуется
 * в объект класса Dog, с которымможно работать.
 */
public class Main {

    public static void main(String[] args) {
        String key = DataHelper.newRqId();
        //генерация данных с ключем
        KafkaOperations.produce(key);
        //получение данных по ключу в коллекцию
        List<String> messages = KafkaOperations.getMessages(key);
        System.out.println(messages);
        //получение из всех сообщений объекта Dog
        Dog dog = DataHelper.getDogFromAllMessages(messages);
        //работа с объектом
        dog.showAnimal();
    }
}
