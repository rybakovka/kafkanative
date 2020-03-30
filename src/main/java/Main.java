import Helpers.DataHelper;
import Helpers.KafkaHelper;
import entity.Dog;

import java.util.ArrayList;

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
        KafkaHelper.produce(key);
        //получение данных по ключу в коллекцию
        ArrayList<String> messages = KafkaHelper.getMessages(key);
        System.out.println(messages);
        //десериализация json
        Dog dog = DataHelper.getDogFromJson(messages);
        //работа с объектом
        dog.showAnimal();
    }
}
