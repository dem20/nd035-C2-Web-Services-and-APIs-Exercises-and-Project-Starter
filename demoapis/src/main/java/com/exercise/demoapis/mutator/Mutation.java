package com.exercise.demoapis.mutator;

import com.exercise.demoapis.entity.Dog;
import com.exercise.demoapis.repository.DogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import com.exercise.demoapis.exception.DogNotFoundException;
import java.util.Optional;

@Controller
public class Mutation {
    private final DogRepository dogRepository;
    
    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @MutationMapping
    public boolean deleteDogBreed(@Argument String breed) {
        boolean deleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();
        for (Dog d:allDogs) {
           if (d.getBreed().equals(breed)) {
               dogRepository.delete(d);
               deleted = true;
           }
        }
        
        return deleted;
    }

    @MutationMapping
    public Dog updateDogName(@Argument String newName, @Argument Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);

            Dog dog = optionalDog.orElseThrow(() -> new DogNotFoundException("Dog not found with id ", id));
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
               
    }
}
