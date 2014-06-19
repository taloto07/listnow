package com.listnow.hosting.dao;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_image database table.
 * 
 */
@Entity
@Table(name="user_image")
@NamedQuery(name="UserImage.findAll", query="SELECT u FROM UserImage u")
public class UserImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional one-to-one association to Image
	@OneToOne
	private Image image;

	//bi-directional one-to-one association to User
	@OneToOne(mappedBy="userImage")
	private User user;

	public UserImage() {
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}