package com.databaserelationship.dbrelationonetomany.service;

import com.databaserelationship.dbrelationonetomany.repository.InternDetailsRepository;
import com.databaserelationship.dbrelationonetomany.repository.InternRepository;
import com.databaserelationship.dbrelationonetomany.resources.embed.Address;
import com.databaserelationship.dbrelationonetomany.resources.entity.InternDetails;
import com.databaserelationship.dbrelationonetomany.resources.entity.Interns;
import com.databaserelationship.dbrelationonetomany.resources.enums.Gender;
import com.databaserelationship.dbrelationonetomany.resources.request.InternDetailsRequest;
import com.databaserelationship.dbrelationonetomany.util.exception.InvalidRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InternDetailsServiceTest {

    private InternDetailsRepository internDetailsRepository;
    private InternRepository internRepository;

    private InternDetailsService detailsService;

    @Test
    void createInternDetailsForExceptionChecking() {

        internRepository = Mockito.mock(InternRepository.class);
        internDetailsRepository = Mockito.mock(InternDetailsRepository.class);

        detailsService = new InternDetailsService(internDetailsRepository, internRepository);

        InternDetailsRequest detailsRequest = new InternDetailsRequest(1L, "", 147258);
        Mockito.when(internRepository.findById(detailsRequest.getId())).thenReturn(Optional.empty());

        InvalidRequestException requestException = assertThrows(
                InvalidRequestException.class,
                () -> detailsService.createInternDetails(detailsRequest)
        );

        assertEquals("Intern not found with id 1", requestException.getMessage());

    }

    @Test
    void createInternDetailsToCheckValues() {

        internRepository = Mockito.mock(InternRepository.class);
        internDetailsRepository = Mockito.mock(InternDetailsRepository.class);

        detailsService = new InternDetailsService(internDetailsRepository, internRepository);

        InternDetailsRequest detailsRequest = new InternDetailsRequest(2L, "Kolkata", 147258);
        InternDetails internDetails = new InternDetails(new Address("Kolkata", 147258));

        Interns intern = new Interns("abc", "xyz", Gender.MALE, LocalDateTime.now());
        intern.setInternDetails(internDetails);

        Mockito.when(internRepository.findById(detailsRequest.getId())).thenReturn(Optional.of(intern));

//        InvalidRequestException requestException = assertThrows(
//                InvalidRequestException.class,
//                () -> detailsService.createInternDetails(detailsRequest)
//        );

        assertEquals(intern, detailsService.createInternDetails(detailsRequest));

    }

    @Test
    void getInternDetailsForExceptionChecking() {

        internDetailsRepository = Mockito.mock(InternDetailsRepository.class);
        internRepository = Mockito.mock(InternRepository.class);

        detailsService = new InternDetailsService(internDetailsRepository, internRepository);

        Long id = 1L;
        Mockito.when(internDetailsRepository.findById(id))
                .thenReturn(Optional.empty());

        InvalidRequestException requestException = assertThrows(
                InvalidRequestException.class,
                () -> detailsService.getInternDetails(id)
        );

        assertEquals("Intern details with id 1 not found", requestException.getMessage());
    }

    @Test
    void getInternDetailsToCheckValues() {

        internDetailsRepository = Mockito.mock(InternDetailsRepository.class);
        internRepository = Mockito.mock(InternRepository.class);

        detailsService = new InternDetailsService(internDetailsRepository, internRepository);

        InternDetails internDetails = new InternDetails(new Address("Kolkata", 147258));
        internDetails.setId(1L);

        Long id = 1L;
        Mockito.when(internDetailsRepository.findById(id))
                .thenReturn(Optional.of(internDetails));

        assertEquals(internDetails, detailsService.getInternDetails(id));
    }

    @Test
    void getAllInternDetails() {

        internDetailsRepository = Mockito.mock(InternDetailsRepository.class);
        internRepository = Mockito.mock(InternRepository.class);

        detailsService = new InternDetailsService(internDetailsRepository, internRepository);

        List<InternDetails> internDetailsList = Arrays.asList(
                new InternDetails(new Address("iuygty", 147258)),
                new InternDetails(new Address("zwsxe", 789654)),
                new InternDetails(new Address("nmoijm", 456789)),
                new InternDetails(new Address("resa", 369852)),
                new InternDetails(new Address("svjgh", 741852))
        );
        Mockito.when(internDetailsRepository.findAll())
                .thenReturn(internDetailsList);

        assertEquals(internDetailsList, detailsService.getAllInternDetails());
    }
}