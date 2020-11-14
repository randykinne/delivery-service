package dev.randykinne.restservice.controller;

import dev.randykinne.restservice.model.Delivery;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    @GetMapping
    public Delivery getDeliveries(
            @RequestParam(value = "name", defaultValue = "-") String name,
            @RequestParam(value="address", defaultValue = "-") String address) {
        return new Delivery(10, name, address);
    }

    @PostMapping
    public ResponseEntity createDelivery(@RequestBody Delivery delivery) {

        // Validate delivery input data

        // Create new delivery with call to database

        // Get ID that is auto incremented, return here
        try {
            URI loc = new URI("api/v1/deliveries/1");
            return ResponseEntity.created(loc).contentType(MediaType.APPLICATION_JSON).body("{\"location\":\""   + loc.toString() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/{id}")
    public Delivery readDelivery(@PathVariable("id") int id) {
        return new Delivery(id, "Pepsi", "1234 Soft Drink Lane");
    }

    @PostMapping("/{id}")
    public Delivery updateDelivery(@PathVariable("id") int id, @RequestBody Delivery delivery) {
        // Update

        return delivery;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDelivery(@PathVariable("id") int id) {
        // Send call to delete
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"deleted\":\"ok\"}");
    }

}
