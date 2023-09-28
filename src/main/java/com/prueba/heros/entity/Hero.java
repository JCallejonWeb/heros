package com.prueba.heros.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "Hero")
@NoArgsConstructor
@AllArgsConstructor
public class Hero { 
	@Id
	private Long id;
	private String name;
	private String poder;
}
