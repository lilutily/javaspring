package com.academy.ormsqlmapapp.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int board_id;
	
	private  String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
}
