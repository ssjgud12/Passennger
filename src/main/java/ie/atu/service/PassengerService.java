package ie.atu.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.stereotype.Service;
import ie.atu.model.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class PassengerService {
    private final List<Passenger> store = new ArrayList<>();

    public List<Passenger> findAll()
    {
        return new ArrayList<>(store);
    }

    public Optional<Passenger> findById(String id) {
        for (Passenger p : store) {
            if (p.getPassengerId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
    public Passenger create(@org.jetbrains.annotations.NotNull Passenger p)
    {
        if (findById(p.getPassengerId()).isPresent())
        {
            throw new IllegalArgumentException("Passenger already exists");
        }
        store.add(p);
        return p;
    }

    public Optional<Passenger> update(String id, Passenger updatedPassenger)
    {
        // Loop through all passengers in the store
        for (int i = 0; i < store.size(); i++) {
            Passenger existing = store.get(i);

            // Check if this passenger has the ID we're looking for
            if (existing.getPassengerId().equals(id)) {
                // Create updated passenger with same ID but new name/email
                Passenger updated = Passenger.builder()
                        .PassengerId(id)  // Keep the original ID
                        .name(updatedPassenger.getName())  // New name
                        .email(updatedPassenger.getEmail()) // New email
                        .build();

                // Replace the old passenger with updated one
                store.set(i, updated);

                // Return the updated passenger
                return Optional.of(updated);
            }
        }

        // If we didn't find the passenger, return empty
        return Optional.empty();
    }
    public boolean delete(String id)
    {
        for (int i = 0; i < store.size(); i++)
        {
          Passenger p = store.get(i);

            if(p.getPassengerId().equals(id))
            {
                store.remove(i);
                return true;
            }
        }
          return false;

    }

}