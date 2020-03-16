package com.databaserelationship.dbrelationonetomany.service;

import com.databaserelationship.dbrelationonetomany.repository.InternRepository;
import com.databaserelationship.dbrelationonetomany.resources.entity.Interns;
import com.databaserelationship.dbrelationonetomany.resources.enums.Gender;
import com.databaserelationship.dbrelationonetomany.resources.request.InternRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InternServiceTest {

    private InternRepository internRepository;

    @Test
    void createIntern() {

        internRepository = Mockito.mock(InternRepository.class);
        InternService internService = new InternService(internRepository);

        LocalDateTime dateTime = LocalDateTime.of(2020, 3, 10, 15, 25, 50);

        Interns intern = new Interns("abc", "xyz", Gender.MALE, dateTime);
        intern.setId(1L);

        InternRequest internRequest = new InternRequest();
        internRequest.setFirstName("abc");
        internRequest.setLastName("xyz");
        internRequest.setGender(Gender.MALE);

        Mockito.when(internRepository.save(intern)).thenReturn(intern);

        assertEquals(intern, internService.createIntern(internRequest));

    }

    @Test
    void updateIntern() {
    }

    @Test
    void getAllInterns() {
    }

    @Test
    void getIntern() {
    }
}