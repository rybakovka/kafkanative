package entity;

public class Cat extends Animal {
    private float weight;
    public Cat(String name, float weight){
        this.name = name;
        this.weight = weight;
    }


    @Override
    public void showAnimal() {
        System.out.printf("name = %s, weight = %f%n", name, weight);
    }
}
