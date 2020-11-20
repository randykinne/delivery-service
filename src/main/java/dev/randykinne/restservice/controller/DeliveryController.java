package dev.randykinne.restservice.controller;

import dev.randykinne.restservice.model.Delivery;
import dev.randykinne.restservice.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService service;

    @GetMapping
    public ResponseEntity<Iterable<Delivery>> getAll() {
        return ResponseEntity.ok(this.service.getAllDeliveries());
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity create(@RequestBody Delivery delivery) {
        if (service.isValid(delivery)) {
            Delivery createdDelivery = this.service.createDelivery(delivery);
            URI location = URI.create(String.format("/api/v1/deliveries/%s", createdDelivery.getId()));
            return ResponseEntity
                    .created(location)
                    .body("{\"location\":\"" + location.toString() + "\"}");
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    // Update a task
    @PostMapping("/{id}")
    public ResponseEntity<Delivery> update(@PathVariable("id") int id, @RequestBody Delivery delivery) {
        if (service.isValid(delivery)) {
            return ResponseEntity.ok(this.service.updateDelivery(id, delivery));
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> read(@PathVariable("id") int id) {
        Optional<Delivery> result = this.service.getDelivery(id);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (id > 0) {
            this.service.deleteDelivery(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

}
