package entity;

public class Dog extends Animal {
    private int weight;
    public Dog(String name, int weight){
        this.name = name;
        this.weight = weight;
    }

    @Override
    public void showAnimal() {
        System.out.printf("name = %s, weight = %d%n", name, weight);
    }
}
