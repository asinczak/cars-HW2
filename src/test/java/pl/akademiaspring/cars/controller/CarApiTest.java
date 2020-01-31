package pl.akademiaspring.cars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.akademiaspring.cars.model.Car;
import pl.akademiaspring.cars.service.CarService;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarApiTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void getCars() throws Exception {
        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    public void getCarById() throws Exception {
        mockMvc.perform(get("/cars/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));
    }

    @Test
    public void getCarByColour() throws Exception {
        mockMvc.perform(get("/cars/byColour").param("colour", "RED"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.colour").value("RED"));
    }

    @Test
    public void addCar() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/cars")
                .content(asJsonString(new Car(4, "Toyota", "Avensis", "BLACK")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void modCar() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .put("/cars")
                .content(asJsonString(new Car(2, "Mazda", "3", "WHITE")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mark").value("Mazda"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("3"));
    }

    @Test
    public void modCarElement() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .patch("/cars/{id}/{colour}", 2, "GREEN"))
                .andExpect(status().isOk());
    }

    @Test
    public void removeCar() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders.delete("/cars/{id}", 1) )
                .andExpect(status().isOk());
    }
}