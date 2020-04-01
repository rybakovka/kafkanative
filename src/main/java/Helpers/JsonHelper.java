package Helpers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entity.Animal;

public class JsonHelper<T extends Animal> {

    private static Gson gson = new Gson();

    /**
     * Сериализация объекта Cat в Json.
     * @param object сериализуемый объект
     * @return json-строка с объектом
     */
    public static String getJson(Animal object) {
        return gson.toJson(object);
    }

    public static Animal getObject(String s, Class objClass) {
        Animal animal = null;
        try {
            animal = (Animal) gson.fromJson(s, objClass);
            System.out.printf("Строка десериализована: %s%n", s);
        } catch (JsonSyntaxException e) {
            System.out.printf("Строка не десериализована: %s%n", s);
        }
        return animal;
    }
}
