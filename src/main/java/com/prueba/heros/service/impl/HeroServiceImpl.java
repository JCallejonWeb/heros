package com.prueba.heros.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.prueba.heros.config.HerosException;
import com.prueba.heros.entity.Hero;
import com.prueba.heros.repository.HeroRepository;
import com.prueba.heros.service.HeroService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HeroServiceImpl implements HeroService {

	private HeroRepository heroRepository;
	
	@Override
	@Cacheable("repoCache")
	public List<Hero> getHeros() {	
		return heroRepository.findAll();
	}

	@Override
	public Optional<Hero> getHeroById(String id) {
		try {
		    long longId = Long.parseLong(id);
		    return heroRepository.findById(longId);
		} catch (NumberFormatException e) {
			throw new HerosException(HttpStatus.BAD_REQUEST,"ID no valido");
		}
	}

	@Override
	public List<Hero> getHeroContaining(String Cadena) {
		return heroRepository.findByNameContaining(Cadena);
	}

	@Override
	//Aunque pasemos el metodo save, si el objeto lleva un id que ya exista hace un update.
	public Hero updateHero(Hero hero) {
		return heroRepository.save(hero);
	}
	
	@Override
	public boolean deleteHero(String id) {
        try {
            heroRepository.deleteById(Long.parseLong(id));
            // La eliminación se realizó correctamente
            return true;
        } catch (EmptyResultDataAccessException e) {
            // El registro no existía en la base de datos
        	// No se pudo realizar la eliminación
        	return false;
        }
	}

}
