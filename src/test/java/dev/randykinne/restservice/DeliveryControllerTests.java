package dev.randykinne.restservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.randykinne.restservice.controller.DeliveryController;
import dev.randykinne.restservice.model.Delivery;
import dev.randykinne.restservice.service.DeliveryService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(DeliveryController.class)
@AutoConfigureMockMvc
public class DeliveryControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DeliveryService service;

	@Test
	public void getDeliveryByIdShouldReturnObjectAsJSON() throws Exception {
		// Mock the value from the database
		Delivery delivery = new Delivery();
		delivery.setId(1);
		delivery.setName("delivery name");
		delivery.setAddress("1234 Delivery Lane");
		Optional<Delivery> optionalDelivery = Optional.of(delivery);

		// Mock the service to return the value
		when(service.getDelivery(anyInt())).thenReturn(optionalDelivery);

		this.mockMvc.perform(get("/api/v1/deliveries/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("delivery name"))
				.andExpect(jsonPath("$.address").value("1234 Delivery Lane"));
	}

	@Test
	public void getMissingDeliveryByIdShouldReturn404NotFound() throws Exception {
		// Mock the service to return an empty optional
		when(service.getDelivery(anyInt())).thenReturn(Optional.empty());
		this.mockMvc.perform(get("/api/v1/deliveries/1")).andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	public void getAllDeliveriesShouldReturnMultiple() throws Exception {
		// Mock the values from the database
		List<Delivery> list = new ArrayList<>();
		Delivery delivery = new Delivery();
		delivery.setId(1);
		delivery.setName("delivery name");
		delivery.setAddress("1234 Delivery Lane");

		for (int i=0; i<5; i++) {
			list.add(delivery);
		}

		// Mock the service to return the value
		when(service.getAllDeliveries()).thenReturn(list);

		//TODO: Add check for multiple
		this.mockMvc.perform(get("/api/v1/deliveries")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void createNewDeliveryShouldReturnLocation() throws Exception {
		// Mock the values from the database
		Delivery delivery = new Delivery();
		delivery.setId(50);
		delivery.setName("delivery name");
		delivery.setAddress("1234 Delivery Lane");

		// Mock the service to return the value
		when(service.createDelivery(any(Delivery.class))).thenReturn(delivery);
		when(service.isValid(any(Delivery.class))).thenReturn(true);

		this.mockMvc.perform(
					post("/api/v1/deliveries")
					.contentType("application/json")
					.content(delivery.toJSON())
					)
				.andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("location").value("/api/v1/deliveries/50"));
	}

	@Test
	public void deleteDeliveryByFoundIdShouldReturnGoodStatus() throws Exception {
		// Mock the values from the database
		Delivery delivery = new Delivery();
		delivery.setId(1);
		delivery.setName("delivery name");
		delivery.setAddress("1234 Delivery Lane");

		// Mock the service to return the value
		when(service.deleteDelivery(anyInt())).thenReturn(delivery);

		this.mockMvc.perform(delete("/api/v1/deliveries/1", delivery)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void deleteDeliveryNotFoundByIdShouldReturnBadStatus() throws Exception {
		// Mock the values from the database
		Delivery delivery = new Delivery();
		delivery.setId(1);
		delivery.setName("delivery name");
		delivery.setAddress("1234 Delivery Lane");

		// Mock the service to return the value
		when(service.deleteDelivery(anyInt())).thenReturn(delivery);

		this.mockMvc.perform(delete("/api/v1/deliveries/-1", delivery)).andDo(print()).andExpect(status().isIAmATeapot());
	}

}
