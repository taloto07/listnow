package com.listnow.hosting.dao;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the item_image database table.
 * 
 */
@Entity
@Table(name="item_image")
@NamedQueries({
	@NamedQuery(name="ItemImage.findAll", query="SELECT i FROM ItemImage i"),
	@NamedQuery(name="ItemImage.findById", query="SELECT i FROM ItemImage i WHERE i.id = :id")
})
public class ItemImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional one-to-one association to Image
	@OneToOne
	private Image image;

	//bi-directional many-to-one association to Item
	@ManyToOne
	private Item item;

	public ItemImage() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}