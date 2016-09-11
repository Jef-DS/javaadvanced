package org.betavzw.javaadvanced;

/**
 * Created by Jef on 24/08/2016.
 */
public class CoContraVarianceGenerics {
    public static void main(String[] args) {
        Shelter<Bird> birdshelter = new Shelter<>();
        saveAnimalInShelter(birdshelter);
        printAnimalFromShelter(birdshelter);
    }

    public static void saveAnimalInShelter(Shelter<? super Chicken> shelter) {
        shelter.setAnimai(new Chicken("Tweety"));
    }

    public static void printAnimalFromShelter(Shelter<? extends Animal> shelter) {
        Animal a = shelter.getAnimai();
        System.out.println(a);

    }
}
class Animal {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Bird extends Animal{
    public Bird(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Bird{" +
                "name='" + getName() + '\'' +
                '}';
    }
}
class Chicken extends Bird{
    public Chicken(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Chicken{" +
                "name='" + getName() + '\'' +
                '}';
    }
}
class Shelter <T> {
    private T animai;

    public T getAnimai() {
        return animai;
    }

    public void setAnimai(T animai) {
        this.animai = animai;
    }

}

class Shelter2<T, U extends T> {
    private T animal;
    public T getAnimal() {
        return animal;
    }

    public void setAnimal(U animal){
        this.animal = animal;
    }
}