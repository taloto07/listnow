package com.listnow.hosting.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cities database table.
 * 
 */
@Entity
@Table(name="cities")
@NamedQueries({
	@NamedQuery(name="City.findAll", query="SELECT c FROM City c"),
	@NamedQuery(name="City.findByZipcode", query="SELECT c FROM City c WHERE c.zipcode = :zipcode")
})
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int zipcode;

	private String name;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="city")
	private List<Item> items;

	public City() {
	}

	public int getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setCity(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setCity(null);

		return item;
	}

}