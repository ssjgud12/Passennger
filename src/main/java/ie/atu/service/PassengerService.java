package ie.atu.service;

import ie.atu.model.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PassengerService {
    private final List<Passenger> store = new ArrayList<>();

    public List<Passenger> findAll()
    {
        return new ArrayList<>(store);
    }

    public Optional<Passenger> findById(String id) {
        for (Passenger p : store) {
            if (p.getPassengerID().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
    public Passenger create(Passenger p)
    {
        if (findById(p.getPassengerID()).isPresent())
        {
            throw new IllegalArgumentException("Passenger already exists");
        }
        store.add(p);
        return p;
    }
}
