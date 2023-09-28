package com.prueba.heros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prueba.heros.entity.Hero;

@Service
public interface HeroService {

	//Metodo para obtener toda la lista de super heroes
	List<Hero> getHeros();
	
	//Metodo para obtener un heroe por id
	Optional<Hero> getHeroById(String id);
	
	//Metodo para obtener lista de heroes que contenga una cadena
	List<Hero> getHeroContaining(String Cadena);
	
	//Metodo para actualizar heroe
	Hero updateHero(Hero hero);
	
	//Metodo para eliminar un heroe
	boolean deleteHero(String id);

}
