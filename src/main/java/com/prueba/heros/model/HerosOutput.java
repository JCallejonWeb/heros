package com.prueba.heros.model;

import java.util.List;
import com.prueba.heros.entity.Hero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HerosOutput {

	private String name;
	
	private List<Hero> heros;
	
}
