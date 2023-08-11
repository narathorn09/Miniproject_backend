package com.boardgame.main.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "Review" )
public class Review {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Long reviewID;
	private int rating;
	private String comment;
	private Date timestamp;
	
    @ManyToOne
    @JoinColumn(name = "gameID")
    private BoardGame game;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    
	public Review() {
		super();
	}

	public Review(Long reviewID, int rating, String comment, Date timestamp, BoardGame game, User user) {
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
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
