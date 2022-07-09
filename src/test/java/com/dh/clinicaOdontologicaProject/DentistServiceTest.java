package com.dh.clinicaOdontologicaProject;
import com.dh.clinicaOdontologicaProject.entity.Dentist;
import com.dh.clinicaOdontologicaProject.service.IDentistService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class DentistServiceTest {

    @Autowired
    IDentistService dentistService;

    @Test
    @Order(1)
    public void addDentistTest(){
        Dentist dentistTest = new Dentist("Perez", "Roberto", 112233);
        dentistService.saveDentist(dentistTest);
        //search for dentistTest
        Optional<Dentist> dentistTestFound = dentistService.findById(1L);
        assertTrue(dentistTestFound.isPresent());
    }

    @Test
    @Order(2)
    public void findDentistTest(){
        Long idSearch = 1L;
        Optional<Dentist> dentistSearch = dentistService.findById(idSearch);
        assertTrue(dentistSearch.isPresent());
    }

    @Test
    @Order(3)
    public void listDentistsTest(){
        List<Dentist> testList = dentistService.listDentists();
        assertTrue(testList.size() > 0);
    }

    @Test
    @Order(4)
    public void updateDentistTest(){
        Long idSearch = 1L;
        Dentist dentistTest2 = new Dentist(idSearch,"Rodríguez", "Diego", 22233);
    dentistService.updateDentist(dentistTest2);
    Optional<Dentist> dentistSearch = dentistService.findById(idSearch);
    assertEquals("Rodríguez", dentistSearch.get().getSurname());
    }
}
