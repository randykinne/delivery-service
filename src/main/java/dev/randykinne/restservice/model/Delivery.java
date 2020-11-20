package dev.randykinne.restservice.model;


import javax.persistence.*;

@Entity
@Table(name = "deliveries")
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String address;

	public Delivery() {
		// Empty as Hibernate annotations use getters and setters instead
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toJSON() {
		return "{ \"id\":\"" + getId() +
				"\", \"name\":\"" + getName() +
				"\", \"address\":\"" + getAddress() +
				"\" }";
	}
}
