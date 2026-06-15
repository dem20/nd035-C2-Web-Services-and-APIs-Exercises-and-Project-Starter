package com.udacity.bootstrap.service;


import org.springframework.stereotype.Service;

import com.udacity.bootstrap.entity.Dog;
import com.udacity.bootstrap.exceptions.DogNotFoundException;
import com.udacity.bootstrap.repository.DogRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Dog createDog(Dog dog) {
        return dogRepository.save(dog);
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    public Dog updateDog(Long id, Dog updatedDog) {
        return dogRepository.findById(id)
                .map(dog -> {
                    dog.setName(updatedDog.getName());
                    dog.setBreed(updatedDog.getBreed());
                    return dogRepository.save(dog);
                })
                .orElseThrow(DogNotFoundException::new);
    }

    public void deleteDog(Long id) {
        dogRepository.deleteById(id);
    }

    public List<Dog> retrieveDogBreed(String breed) {
        return dogRepository.findByBreed(breed);
    }

    public String retrieveDogBreedById(Long id) {
        Optional<String> optionalBreed = Optional.ofNullable(dogRepository.findBreedById(id));
        String breed = optionalBreed.orElseThrow(DogNotFoundException::new);
        return breed;
    }

    public List<String> retrieveDogNames() {
        return dogRepository.findAll()
                .stream()
                .map(Dog::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> retrieveDogBreeds() {
        return dogRepository.findAll()
                .stream()
                .map(Dog::getBreed)
                .collect(Collectors.toList());
    }
}

