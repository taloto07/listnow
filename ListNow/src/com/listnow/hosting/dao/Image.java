package com.listnow.hosting.dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the images database table.
 * 
 */
@Entity
@Table(name="images")
@NamedQueries({
	@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i"),
	@NamedQuery(name="Image.findById", query="SELECT i FROM Image i WHERE i.id = :id")
})
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	private String path;
	
	private String extension;

	private int size;
	
	

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="upload_date")
	private Date uploadDate;

	//bi-directional one-to-one association to ItemImage
	@OneToOne(mappedBy="image")
	private ItemImage itemImage;

	//bi-directional one-to-one association to UserImage
	@OneToOne(mappedBy="image")
	private UserImage userImage;

	public Image() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Date getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public ItemImage getItemImage() {
		return this.itemImage;
	}

	public void setItemImage(ItemImage itemImage) {
		this.itemImage = itemImage;
	}

	public UserImage getUserImage() {
		return this.userImage;
	}

	public void setUserImage(UserImage userImage) {
		this.userImage = userImage;
	}

}