package dev.randykinne.restservice.service;

import dev.randykinne.restservice.model.Delivery;
import dev.randykinne.restservice.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository repository;

    public Boolean isValid(final Delivery delivery) {
        return delivery != null && !delivery.getName().isEmpty() && !delivery.getAddress().isEmpty();
    }

    public Iterable<Delivery> getAllDeliveries() {
        return this.repository.findAll();
    }

    public Delivery createDelivery(final Delivery delivery) {
        return this.repository.save(delivery);
    }

    public Optional<Delivery> getDelivery(final int id) {
        return this.repository.findById(id);
    }

    @Transactional
    public Delivery updateDelivery(final int id, final Delivery delivery) {
        Optional<Delivery> result = this.repository.findById(id);
        Delivery updatedDelivery = result.get();
        updatedDelivery.setName(delivery.getName());
        updatedDelivery.setAddress(delivery.getAddress());
        return this.repository.save(updatedDelivery);
    }

    @Transactional
    public Delivery deleteDelivery(final int id){
        Optional<Delivery> result = this.repository.findById(id);
        Delivery delivery = result.get();
        this.repository.delete(delivery);
        return delivery;
    }

}
