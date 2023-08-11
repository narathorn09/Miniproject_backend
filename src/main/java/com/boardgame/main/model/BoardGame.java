package com.boardgame.main.model;

import com.mysql.cj.jdbc.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "BoardGame" )
public class BoardGame {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long gameID;
	private String title;
	private String description;
	private String photoName;
	private java.sql.Blob photoData;
	private Integer adminRating;
	private Integer averageRating;
	
	@ManyToOne
    @JoinColumn(name = "userID")
    private User user;
	
	public BoardGame() {
		super();
	}

	public BoardGame(Long gameID, String title, String description, String photoName, Blob photoData,
			Integer adminRating, Integer averageRating, User user) {
		super();
		this.gameID = gameID;
		this.title = title;
		this.description = description;
		this.photoName = photoName;
		this.photoData = photoData;
		this.adminRating = adminRating;
		this.averageRating = averageRating;
		this.user = user;
	}

	public Long getGameID() {
		return gameID;
	}

	public void setGameID(Long gameID) {
		this.gameID = gameID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public java.sql.Blob getPhotoData() {
		return photoData;
	}

	public void setPhotoData(Blob photoData) {
		this.photoData = photoData;
	}

	public Integer getAdminRating() {
		return adminRating;
	}

	public void setAdminRating(Integer adminRating) {
		this.adminRating = adminRating;
	}

	public Integer getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Integer averageRating) {
		this.averageRating = averageRating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
