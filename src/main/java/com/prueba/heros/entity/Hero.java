package com.prueba.heros.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "Hero")
@NoArgsConstructor
@AllArgsConstructor
public class Hero { 
	@Id
	@Schema(description = "The hero ID")
	private Long id;
	@Schema(description = "The hero Name")
	private String name;
	@Schema(description = "The hero power")
	private String power;
}
