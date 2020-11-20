package dev.randykinne.restservice;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
		Optional<Delivery> optionalDelivery = Optional.of(new Delivery());

		// Mock the service to return the value
		when(service.getDelivery(1)).thenReturn(optionalDelivery);

		this.mockMvc.perform(get("/api/v1/deliveries/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("delivery name"))
				.andExpect(jsonPath("$.address").value("1234 Delivery Lane"));
	}
}
