package com.boardgame.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boardgame.main.model.BoardGame;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long>{

}
