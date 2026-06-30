package com.udacity.bootstrap.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import com.udacity.bootstrap.entity.Dog;
import com.udacity.bootstrap.service.DogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DogController.class)
@AutoConfigureMockMvc(addFilters = false)
class DogControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DogService dogService;

    @Test
    void createDog_shouldReturnOk() throws Exception {
        when(dogService.createDog(any(Dog.class))).thenReturn(null);

        mockMvc.perform(post("/dogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(dogService, times(1)).createDog(any(Dog.class));
    }

    @Test
    void getAllDogs_shouldReturnOkAndEmptyArray() throws Exception {
        when(dogService.getAllDogs()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/dogs"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(dogService, times(1)).getAllDogs();
    }

    @Test
    void getByBreed_shouldReturnOk() throws Exception {
        String breed = "Labrador";
        when(dogService.retrieveDogBreed(breed)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/dogs/breed/{breed}", breed))
                .andExpect(status().isOk());

        verify(dogService, times(1)).retrieveDogBreed(breed);
    }

    @Test
    void getBreedById_shouldReturnOkAndBreed() throws Exception {
        Long id = 1L;
        when(dogService.retrieveDogBreedById(id)).thenReturn("Labrador");

        mockMvc.perform(get("/dogs/{id}/breed", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Labrador"));

        verify(dogService, times(1)).retrieveDogBreedById(id);
    }

    @Test
    void getDogBreeds_shouldReturnOk() throws Exception {
        when(dogService.retrieveDogBreeds()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/dogs/breeds"))
                .andExpect(status().isOk());

        verify(dogService, times(1)).retrieveDogBreeds();
    }

    @Test
    void deleteDog_shouldReturnNoContent() throws Exception {
        Long id = 2L;
        doNothing().when(dogService).deleteDog(id);

        mockMvc.perform(delete("/dogs/{id}", id))
                .andExpect(status().isNoContent());

        verify(dogService, times(1)).deleteDog(id);
    }
}
