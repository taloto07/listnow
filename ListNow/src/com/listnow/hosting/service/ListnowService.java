package com.listnow.hosting.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.listnow.hosting.dao.Category;
import com.listnow.hosting.dao.City;
import com.listnow.hosting.dao.Group;
import com.listnow.hosting.dao.Image;
import com.listnow.hosting.dao.Item;
import com.listnow.hosting.dao.ItemImage;
import com.listnow.hosting.dao.User;
import com.listnow.hosting.dao.UserImage;

public class ListnowService {
	@Inject
	private Provider<EntityManager> entityManager;
	
	//-----------------------------------------City------------------------------------------------------------------------------------------------------------
	public List<City> getAllCity(){
		return entityManager.get().createNamedQuery("City.findAll", City.class).getResultList();
	}
	
	public List<City> getAllCitySort(){
		return entityManager.get().createNamedQuery("City.findAllSort", City.class).getResultList();
	}
	
	public City getCityByZipcode(int zipcode){
		try{
			return entityManager.get().createNamedQuery("City.findByZipcode", City.class).setParameter("zipcode", zipcode).getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//-----------------------------------------User------------------------------------------------------------------------------------------------------------
	public List<User> getAllUser(){
		return entityManager.get().createNamedQuery("User.findAll", User.class).getResultList();
	}
	
	public User getUserByEmail(String email){
		try{
			return entityManager.get().createNamedQuery("User.findByEmail", User.class).setParameter("email", email).getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
	
	public boolean addUser(User user){
		User userFromDB = this.getUserByEmail(user.getEmail());
		
		if (userFromDB != null) return false;
		
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(user);
		entityManager.get().getTransaction().commit();
		
		return true;
	}
	
	public boolean removeUserByEmail(String email){
		User u = this.getUserByEmail(email);
		if (u == null) return false;
		entityManager.get().getTransaction().begin();
		entityManager.get().remove(u);
		entityManager.get().getTransaction().commit();
		return true;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//-----------------------------------------Item------------------------------------------------------------------------------------------------------------
	public List<Item> getAllItem() {
		return entityManager.get().createNamedQuery("Item.findAll", Item.class)
				.getResultList();
	}

	public Item getItemById(int id) {
		try {
			return entityManager.get()
					.createNamedQuery("Item.findById", Item.class)
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean addItem(Item item) {
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(item);
		entityManager.get().getTransaction().commit();
		return true;
	}
	
	public boolean removeItem(Item item){
		entityManager.get().getTransaction().begin();
		entityManager.get().remove(item);
		entityManager.get().getTransaction().commit();
		return true;
	}
	
	public List<Item> searchItemByTitle(String title){
		return entityManager.get().createNamedQuery("Item.searchItemByTitle", Item.class).setParameter("title", "%"+title+"%").getResultList();
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//-----------------------------------------Image-----------------------------------------------------------------------------------------------------------
	public List<Image> getAllImage(){
		return entityManager.get().createNamedQuery("Image.findAll", Image.class).getResultList();
	}
	
	public Image getImageById(int id){
		try{
			return entityManager.get().createNamedQuery("Image.findById", Image.class).setParameter("id", id).getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
	
	public boolean addImage(Image image){
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(image);
		entityManager.get().getTransaction().commit();
		return true;
	}
	
	public boolean removeImage(Image image){
		Image dbImage = this.getImageById(image.getId());
		
		if (dbImage == null) return false;
		
		entityManager.get().getTransaction().begin();
		entityManager.get().remove(image);
		entityManager.get().getTransaction().commit();
		return true;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//-----------------------------------------UserImage-------------------------------------------------------------------------------------------------------
	public List<UserImage> getAllUserImage(){
		return entityManager.get().createNamedQuery("UserImage.findAll", UserImage.class).getResultList();
	}
	
	public UserImage getUserImageById(int id){
		try{
			return entityManager.get().createNamedQuery("UserImage.findById", UserImage.class).setParameter("id", id).getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
	
	public boolean addUserImage(UserImage userImage){
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(userImage);
		entityManager.get().getTransaction().commit();
		return true;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	// -----------------------------------------UserImage-------------------------------------------------------------------------------------------------------
	public List<ItemImage> getAllItemImage() {
		return entityManager.get()
				.createNamedQuery("ItemImage.findAll", ItemImage.class)
				.getResultList();
	}

	public ItemImage getItemImageById(int id) {
		try {
			return entityManager.get()
					.createNamedQuery("ItemImage.findById", ItemImage.class)
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean addItemImage(ItemImage ItemImage) {
		entityManager.get().getTransaction().begin();
		entityManager.get().persist(ItemImage);
		entityManager.get().getTransaction().commit();
		return true;
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//-----------------------------------------Group-----------------------------------------------------------------------------------------------------------
	public List<Group> getAllGroup(){
		return entityManager.get().createNamedQuery("Group.findAll", Group.class).getResultList();
	}
	
	public Group getGroupByName(String name){
		try{
			return entityManager.get().createNamedQuery("Group.findByName", Group.class).setParameter("name", name).getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
	
	public boolean addGroup(Group group){
		try{
			entityManager.get().getTransaction().begin();
			entityManager.get().persist(group);
			entityManager.get().getTransaction().commit();
		}catch (PersistenceException e){
			return false;
		}
		return true;
	}
	
	public boolean removeGroupByName(String name) {
		Group group = this.getGroupByName(name);
		if (group == null) return false;
		entityManager.get().getTransaction().begin();
		entityManager.get().remove(group);
		entityManager.get().getTransaction().commit();
		return true;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------

	//-----------------------------------------Category--------------------------------------------------------------------------------------------------------
	public List<Category> getAllCategory(){
		return entityManager.get().createNamedQuery("Category.findAll", Category.class).getResultList();
	}
	
	public List<Category> getAllCategorySort(){
		return entityManager.get().createNamedQuery("Category.findAllSort", Category.class).getResultList();
	}
	
	public Category getCategoryById(int id){
		try{
			return entityManager.get().createNamedQuery("Category.findById", Category.class).setParameter("id",id).getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
