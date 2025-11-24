package com.mikael.safetynet.Controller;

import com.mikael.safetynet.controller.PersonController;
import com.mikael.safetynet.model.Person;
import com.mikael.safetynet.Service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonControllerTest {

    @Test
    void testPersonController() throws IOException {
        PersonService mockService = Mockito.mock(PersonService.class);
        PersonController controller = new PersonController(mockService);

        Person p = new Person(); p.setFirstName("John"); p.setLastName("Boyd");
        Mockito.when(mockService.allPerson()).thenReturn(List.of(p));
        var all = controller.all();
        assertEquals(1, all.size());
        Mockito.verify(mockService).allPerson();

        Mockito.when(mockService.getByName("John","Boyd")).thenReturn(p);
        var byName = controller.getByName("John","Boyd");
        assertEquals("Boyd", byName.getLastName());
        Mockito.verify(mockService).getByName("John","Boyd");

        Mockito.when(mockService.create(Mockito.any())).thenReturn(p);
        var created = controller.addPerson(p);
        assertEquals("John", created.getFirstName());
        Mockito.verify(mockService).create(p);

        Mockito.when(mockService.update(Mockito.any())).thenReturn(p);
        var updated = controller.updatePerson(p);
        assertEquals("Boyd", updated.getLastName());
        Mockito.verify(mockService).update(p);

        controller.deletePerson("John","Boyd");
        Mockito.verify(mockService).delete("John","Boyd");
    }
}
