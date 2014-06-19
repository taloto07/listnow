package com.listnow.hosting.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.findByEmail", query="SELECT u FROM User u WHERE u.email = :email")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String password;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="user")
	private List<Item> items;

	//bi-directional many-to-one association to UserGroup
	@OneToMany(mappedBy="user")
	private List<UserGroup> userGroups;

	//bi-directional many-to-many association to Group
	@ManyToMany
	@JoinTable(
		name="user_group"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="group_id")
			}
		)
	private List<Group> groups;

	//bi-directional one-to-one association to UserImage
	@OneToOne
	@JoinColumn(name="user_image_id")
	private UserImage userImage;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setUser(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setUser(null);

		return item;
	}

	public List<UserGroup> getUserGroups() {
		return this.userGroups;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public UserGroup addUserGroup(UserGroup userGroup) {
		getUserGroups().add(userGroup);
		userGroup.setUser(this);

		return userGroup;
	}

	public UserGroup removeUserGroup(UserGroup userGroup) {
		getUserGroups().remove(userGroup);
		userGroup.setUser(null);

		return userGroup;
	}

	public List<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public UserImage getUserImage() {
		return this.userImage;
	}

	public void setUserImage(UserImage userImage) {
		this.userImage = userImage;
	}

}