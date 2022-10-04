package com.academy.bootspa.model.board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.bootspa.model.domain.Board;

// hibernate가 지원하는 JpaRepository로 정의하자
public interface HibernateBoardRepository extends JpaRepository<Board, Integer> {

}
