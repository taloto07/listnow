package com.listnow.hosting.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the items database table.
 * 
 */
@Entity
@Table(name="items")
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private byte delete;

	private String description;

	private byte hide;

	private double price;

	private byte sold;

	private String title;

	//bi-directional many-to-one association to ItemImage
	@OneToMany(mappedBy="item")
	private List<ItemImage> itemImages;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="zipcode")
	private City city;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Item() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getDelete() {
		return this.delete;
	}

	public void setDelete(byte delete) {
		this.delete = delete;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getHide() {
		return this.hide;
	}

	public void setHide(byte hide) {
		this.hide = hide;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte getSold() {
		return this.sold;
	}

	public void setSold(byte sold) {
		this.sold = sold;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ItemImage> getItemImages() {
		return this.itemImages;
	}

	public void setItemImages(List<ItemImage> itemImages) {
		this.itemImages = itemImages;
	}

	public ItemImage addItemImage(ItemImage itemImage) {
		getItemImages().add(itemImage);
		itemImage.setItem(this);

		return itemImage;
	}

	public ItemImage removeItemImage(ItemImage itemImage) {
		getItemImages().remove(itemImage);
		itemImage.setItem(null);

		return itemImage;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}