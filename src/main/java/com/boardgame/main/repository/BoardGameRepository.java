package com.boardgame.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boardgame.main.model.BoardGame;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long>{
	
	 @Query("SELECT b FROM BoardGame b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
	 List<BoardGame> findBoardGameByTitle(@Param("title") String title);
	 
	 @Query("SELECT b FROM BoardGame b")
	 List<BoardGame> findAllBoardGame();
}
