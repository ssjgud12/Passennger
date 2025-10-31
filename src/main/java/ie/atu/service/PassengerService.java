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
        for (int i = 0; i < store.size(); i++) {
            Passenger existing = store.get(i);
            if (existing.getPassengerId().equals(id)) {
                Passenger updated = Passenger.builder()
                        .PassengerId(id)
                        .name(updatedPassenger.getName())
                        .email(updatedPassenger.getEmail())
                        .build();
                store.set(i, updated);
                return Optional.of(updated);
            }
        }
        return Optional.empty();
    }


}