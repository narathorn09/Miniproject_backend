package com.boardgame.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boardgame.main.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query("SELECT r.reviewID, r.rating, r.comment, r.timestamp, r.user FROM Review r WHERE r.game.gameID = :gameID")
	List<Object[]> findReviewByGameId(@Param("gameID") Long gameID);

	@Query("SELECT r.reviewID, r.rating, r.comment, r.timestamp, r.user FROM Review r WHERE r.user.userID = :userID")
	List<Object[]> findReviewsByUserId(@Param("userID") Long userID);

}
