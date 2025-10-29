package ie.atu.controller;

import ie.atu.model.Passenger;
import ie.atu.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api passengers")
public class PassengerController
{
    private final PassengerService serivce;  //contructor DI

    public PassengerController(PassengerService serivce)
    {
        this.serivce = serivce;
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAll()
    {
        return ResponseEntity.ok(serivce.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getOne(@PathVariable String id)

    {
        Optional<Passenger> maybe = serivce.findById(id);
        if(maybe.isPresent())
        {
            return ResponseEntity.ok(maybe.get());
        }
        else
        {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("{/Update}")
    {
        public ResponseEntity<Passenger> update(@Valid @RequestBody Passenger p)
        {

        }
    }



    @PostMapping
    public ResponseEntity<Passenger> create(@Valid @RequestBody Passenger p)
    {
        Passenger created = serivce.create(p);
        return ResponseEntity
                .created(URI.create("/api/passengers/"+ created.getPassengerId()))
                .body(created);
    }

}