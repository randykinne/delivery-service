package dev.randykinne.restservice.repository;

import dev.randykinne.restservice.model.Delivery;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepository extends CrudRepository<Delivery, Integer> {
}
