package client.entities;

import ormarchivarius.annotations.Column;
import ormarchivarius.annotations.Entity;
import ormarchivarius.annotations.Id;
import ormarchivarius.annotations.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Zoo {
    @Id
    Long id;

    @Column
    String address;

    @OneToMany(mappedBy = "zoo_id")
    List<Animal> animals = new ArrayList<>();

    public Zoo() {
    }

    public Zoo(String address) {
        this.address = address;
    }

    public void addAnimal(Animal animal) {
        animal.zoo = this;
        animals.add(animal);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
