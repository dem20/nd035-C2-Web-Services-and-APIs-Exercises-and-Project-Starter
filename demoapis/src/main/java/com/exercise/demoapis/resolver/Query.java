package com.exercise.demoapis.resolver;

import com.exercise.demoapis.entity.Dog;
import com.exercise.demoapis.repository.DogRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class Query implements GraphQLQueryResolver {
    private final DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @QueryMapping
    public Iterable<Dog> findAllDogs() {
        return dogRepository.findAll();
    }

    @QueryMapping
    public Dog findDogById(@Argument Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        return optionalDog.get();
    }
}
