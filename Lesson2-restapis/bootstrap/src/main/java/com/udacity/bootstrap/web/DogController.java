package com.udacity.bootstrap.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.bootstrap.entity.Dog;
import com.udacity.bootstrap.service.DogService;

@RestController
@RequestMapping("/dogs")
public class DogController {
    
private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping
    public Dog create(@RequestBody Dog dog) {
        return dogService.createDog(dog);
    }

    @GetMapping
    public ResponseEntity<List<Dog>> getAll() {
        return new ResponseEntity<List<Dog>>(dogService.getAllDogs(), HttpStatus.OK);
    }

    @GetMapping("/breed/{breed}")
    public ResponseEntity<List<Dog>> getByBreed(@PathVariable String breed) {
        return new ResponseEntity<List<Dog>>(dogService.retrieveDogBreed(breed), HttpStatus.OK);
    }

    @GetMapping("/breeds")
    public ResponseEntity<List<String>> getDogBreeds() {
        return new ResponseEntity<List<String>>(dogService.retrieveDogBreeds(), HttpStatus.OK);
    }

    @GetMapping("/{id}/breed")
    public ResponseEntity<String> getBreedById(@PathVariable Long id) {
        return new ResponseEntity<String>(dogService.retrieveDogBreedById(id), HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getNames() {
        return new ResponseEntity<List<String>>(dogService.retrieveDogNames(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dogService.deleteDog(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }

}
