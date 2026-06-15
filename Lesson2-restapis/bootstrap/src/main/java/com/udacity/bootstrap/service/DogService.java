package com.udacity.bootstrap.service;

import java.util.List;

import com.udacity.bootstrap.entity.Dog;

public interface DogService {
    Dog createDog(Dog dog);

    List<Dog> getAllDogs();

    Dog updateDog(Long id, Dog updatedDog);

    void deleteDog(Long id);

    List<Dog> retrieveDogBreed(String breed);

    String retrieveDogBreedById(Long id);

    List<String> retrieveDogNames();

    List<String> retrieveDogBreeds();
}
