package com.listnow.hosting.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.listnow.hosting.dao.City;
import com.listnow.hosting.dao.Group;
import com.listnow.hosting.dao.User;

public class ListnowService {
	@Inject
	private Provider<EntityManager> entityManager;
	
	//-----------------------------------------City------------------------------------------------------------------------------------------------------------
	public List<City> getAllCity(){
		return entityManager.get().createNamedQuery("City.findAll", City.class).getResultList();
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
	
	//-----------------------------------------group-----------------------------------------------------------------------------------------------------------
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

}
