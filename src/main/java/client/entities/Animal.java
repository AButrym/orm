package client.entities;

import ormarchivarius.annotations.Column;
import ormarchivarius.annotations.Entity;
import ormarchivarius.annotations.Id;
import ormarchivarius.annotations.ManyToOne;

import java.util.Objects;

@Entity("Animals")
public class Animal {
    @Id
    Long id;

    @Column("fullname")
    String name = "";

    int age;

    @ManyToOne("zoo_id")
    Zoo zoo;

    public Animal() {
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age && Objects.equals(id, animal.id) && name.equals(animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
