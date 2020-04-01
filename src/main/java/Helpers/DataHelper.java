package Helpers;

import entity.Dog;
import java.util.List;
import java.util.UUID;

public class DataHelper {

    public static String newRqId(){
        return UUID.randomUUID().toString();
    }

    /**
     * Получение десериализованного объекта Dog из массива сообщений.
     * @param messages коллекция сообщений
     * @return десериализованный объект "Собака"
     */
    public static Dog getDogFromAllMessages(List<String> messages) {
        Dog dog = null;
        for (String message: messages)
            dog = (Dog) JsonHelper.getObject(message, Dog.class);
        return dog;
    }
}
