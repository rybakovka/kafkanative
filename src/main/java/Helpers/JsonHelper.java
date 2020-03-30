package Helpers;

import com.google.gson.Gson;
import entity.Cat;
import entity.Dog;

public class JsonHelper {
    /**
     * Сериализация объекта Cat в Json.
     * @param name имя кошки
     * @param weight в формате Float
     * @return json-строка с объектом
     */
    public static String getJsonCat(String name, float weight) {
        return new Gson().toJson(new Cat(name, weight));
    }

    /**
     * Сериализация объекта Dog в Json.
     * @param name имя собаки
     * @param weight вес в формате int
     * @return json-строка с объектом
     */
    public static String getJsonDog(String name, int weight) {
        return new Gson().toJson(new Dog(name, weight));
    }
}
