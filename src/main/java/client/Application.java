package client;

import client.entities.Animal;
import client.entities.Zoo;
import ormarchivarius.manager.OrmManager;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Application {
    public static void main(String[] args) throws Exception {

        OrmManager ormManager = OrmManager.get("H2.test1"); // by database to work with
        ormManager.prepareRepositoryFor(Animal.class);
        ormManager.prepareRepositoryFor(Zoo.class);

        Zoo newYorkZoo = new Zoo("NewYork");

        Animal alex = new Animal("Alex", 10);
        Animal gloria = new Animal("Gloria", 11);

        newYorkZoo.addAnimal(alex);
        newYorkZoo.addAnimal(gloria);

        ormManager.save(newYorkZoo);

        Animal animal1 = new Animal("AlligatorGena", 2);
        System.out.println(animal1.getId()); // >> null

        ormManager.save(animal1);
        Long id1 = animal1.getId();
        System.out.println(id1); // >> 1

        animal1.setAge(animal1.getAge() + 1);
        System.out.println(animal1.getAge()); // >> 3
        ormManager.update(animal1); // move info into DB

        Animal animal1_1 = ormManager.getById(Animal.class, id1)
                .orElseThrow(NoSuchElementException::new);
        System.out.println(animal1_1.equals(animal1)); // >> true
        System.out.println(animal1_1 == animal1); // >> false

        animal1.setAge(animal1.getAge() + 1);
        Animal animal1_2 = ormManager.getById(Animal.class, id1)
                .orElseThrow(NoSuchElementException::new);;
        System.out.println(animal1_2.equals(animal1)); // >> false

        ormManager.update(animal1);
        List<Animal> allAnimals = ormManager.getAll(Animal.class);
        System.out.println(allAnimals.size()); // >> 1
        System.out.println(animal1.equals(allAnimals.get(0))); // >> true

        ormManager.delete(animal1);
        System.out.println(animal1.getId()); // >> null
        Optional<Animal> animal1_3 = ormManager.getById(Animal.class, id1);
        System.out.println(animal1_3.isEmpty()); // >> true
    }
}
