package domain.model;

import lombok.Data;

@Data
public class Animal {
    private int id;
    private String nombre;
    private String species;
    private int age;
    private int habitatId;

    public Animal(int id, String nombre, String species, int age, int habitatId) {
        this.id = id;
        this.nombre = nombre;
        this.species = species;
        this.age = age;
        this.habitatId = habitatId;
    }
}
