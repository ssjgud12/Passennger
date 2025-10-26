package ie.atu.service;

import ie.atu.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerServiceTest
{
    private PassengerService service;

    @BeforeEach
    void setup () { service = new PassengerService(); }

    @Test
    void createThenFindById()
    {
        Passenger p = Passenger.builder()
                .PassengerId("A1")
                .name("Alvin")
                .email("Alvin@atu.ie")
                .build();

        service.create(p);

        Optional<Passenger> found = service.findById("A1");
        assertTrue(found.isPresent());
        assertEquals("Alvin", found.get().getName());
    }

    @Test
    void duplicateIdThrows()
    {
        service.create(Passenger.builder()
                .PassengerId("A2")
                .name("Simon")
                .email("Simon@atu.ie")
                .build());

        assertThrows(IllegalArgumentException.class, () ->
                service.create(Passenger.builder()
                        .PassengerId("T1")
                        .name("Theodore")
                        .email("Theodore@atu.ie")
                        .build()));
    }
}
