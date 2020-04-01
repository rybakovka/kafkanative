package Helpers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entity.Dog;

import java.util.ArrayList;
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
    public static Dog getDogFromJson(ArrayList<String> messages) {
        Dog dog = null;
        for (String message: messages)
            dog = (Dog) JsonHelper.getObject(message, Dog.class);
        return dog;
    }
}
