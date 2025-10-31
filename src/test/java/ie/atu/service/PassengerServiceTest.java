package ie.atu.service;

import ie.atu.model.Passenger;
import ie.atu.service.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PassengerServiceTest {
    private PassengerService service;

    @BeforeEach
    void setup()
    {
        service = new PassengerService();
    }

    @Test
    void createThenFindById() {
        Passenger p = Passenger.builder()
                .PassengerId("P1")
                .name("Paul")
                .email("paul@atu.ie")
                .build();

        service.create(p);

        Optional<Passenger> found = service.findById("P1");
        assertTrue(found.isPresent());
        assertEquals("Paul", found.get().getName());
    }

    @Test
    void duplicateIdThrows()
    {
        service.create(Passenger.builder()
                .PassengerId("P2")
                .name("Bob")
                .email("b@stu.ie")
                .build());

        assertThrows(IllegalArgumentException.class, () ->
                service.create(Passenger.builder()
                        .PassengerId("P2")
                        .name("Bob")
                        .email("Bob@ex.com")
                        .build()));
    }

    @Test
    void updatePassengerSuccess()
    {
        Passenger original = Passenger.builder()
                .PassengerId("P1")
                .name("Og Name")
                .email("og@email.com")
                .build();
        service.create(original);

        Passenger updatedData = Passenger.builder()
                .name("Updated Name")
                .email("updated@email.com")
                .build();

        Optional<Passenger> result = service.update("P1", updatedData);

        assertTrue(result.isPresent());
        assertEquals("P1", result.get().getPassengerId());
        assertEquals("Updated Name", result.get().getName());
        assertEquals("updated@email.com", result.get().getEmail());
    }

    @Test
    void updatePassengerNotFound()
    {
        Passenger updatedData = Passenger.builder()
                .name("New Name")
                .email("new@email.com")
                .build();

        Optional<Passenger> result = service.update("NON_EXISTENT", updatedData);

        assertFalse(result.isPresent());
    }

    @Test
    void deletePassengerSuccess()
    {
        Passenger passenger = Passenger.builder()
                .PassengerId("P1")
                .name("John Doe")
                .email("john@email.com")
                .build();
        service.create(passenger);  // FIXED: save() → create()

        boolean deleted = service.delete("P1");

        assertTrue(deleted);
        Optional<Passenger> found = service.findById("P1");
        assertFalse(found.isPresent());
    }

    @Test
    void deletePassengerNotFound()
    {
        boolean deleted = service.delete("NON_EXISTENT");
        assertFalse(deleted);
    }



}