package com.boardgame.main.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long reviewID;
	private Float rating;
	private String comment;

	private Timestamp timestamp;

	@ManyToOne
	@JoinColumn(name = "gameID")
	private BoardGame game;

	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;

	public Review() {
		super();
	}

	public Review(Long reviewID, Float rating, String comment, Timestamp timestamp, BoardGame game, User user) {
		super();
		this.reviewID = reviewID;
		this.rating = rating;
		this.comment = comment;
		this.timestamp = timestamp;
		this.game = game;
		this.user = user;
	}

	public Long getReviewID() {
		return reviewID;
	}

	public void setReviewID(Long reviewID) {
		this.reviewID = reviewID;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public BoardGame getGame() {
		return game;
	}

	public void setGame(BoardGame game) {
		this.game = game;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
