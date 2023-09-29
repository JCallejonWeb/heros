package com.prueba.heros.service;

import static org.junit.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.prueba.heros.config.HerosException;
import com.prueba.heros.entity.Hero;
import com.prueba.heros.repository.HeroRepository;
import com.prueba.heros.service.impl.HeroServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HeroServiceTest {

	
	@Mock
	private HeroRepository heroRepository;
	
	@InjectMocks
	private HeroServiceImpl heroService;
		
	@Test
	void getHerosTest() {
		heroService.getHeros();
	}
	
	@Test
	void getHeroByIdTestOk() {
		Mockito.when(heroRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Hero(1L ,"Juan","velocidad")));
		heroService.getHeroById("1");
	}
	
	@Test
	void getHeroByIdTestKo() {
        
        //ID no valido
        String invalidId = "noValido";
        
        // assertThrows para capturar la excepcion
        assertThrows(HerosException.class, () -> {
            heroService.getHeroById(invalidId);
        });
	}
	
	@Test
	void getHeroContainingTest() {
		heroService.getHeroContaining("man");
	}
	
	@Test
	void updateHeroTest() {
		heroService.updateHero(new Hero(1L,"Juan","Volar"));
	}
	@Test
	void deleteHeroTestOk() {
		Mockito.doNothing().when(heroRepository).deleteById(Mockito.anyLong());
		heroService.deleteHero("1");
	}
	
	@Test
	void deleteHeroTestKo() {
		Mockito.doThrow(EmptyResultDataAccessException.class).when(heroRepository).deleteById(9L);
	
            heroService.deleteHero("9");
        
		
	}
	
}
