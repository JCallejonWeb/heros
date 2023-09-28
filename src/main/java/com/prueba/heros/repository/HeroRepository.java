package com.prueba.heros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.heros.entity.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> { 

	List<Hero> findByNameContaining(String name);
	
}
