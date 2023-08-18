package com.boardgame.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "BoardGame")
public class BoardGame {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long gameID;
	private String title;

	@Column(length = 1000)
	private String description;

	private String photoName;

	@Lob
	@Column(length = 3048576)
	private byte[] photoData;

	private Float adminRating;
	private Float averageRating;

	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;

	public BoardGame() {
		super();
	}

	public BoardGame(Long gameID, String title, String description, String photoName, byte[] photoData,
			Float adminRating, Float averageRating, User user) {
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

	public byte[] getPhotoData() {
		return photoData;
	}

	public void setPhotoData(byte[] photoData) {
		this.photoData = photoData;
	}

	public Float getAdminRating() {
		return adminRating;
	}

	public void setAdminRating(Float adminRating) {
		this.adminRating = adminRating;
	}

	public Float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
