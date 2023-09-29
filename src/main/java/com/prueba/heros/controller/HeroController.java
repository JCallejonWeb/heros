package com.prueba.heros.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.heros.annotation.RequestExecutionTime;
import com.prueba.heros.config.HerosException;
import com.prueba.heros.entity.Hero;
import com.prueba.heros.model.DeleteOutput;
import com.prueba.heros.model.HerosOutput;
import com.prueba.heros.service.HeroService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@RequestMapping("api/heros")
@Tag(name = "My api heros", description = "Microservices about heros database")
@Slf4j
public class HeroController {
	
	private HeroService heroService;
	
	private static String HEROS = "Lista de heroes";
	
	@Operation(summary = "get all heros", description = "Lists of hero")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Found a list of all heros",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = HerosOutput.class)) }),
    		@ApiResponse(responseCode = "204", description = "no content",
            content = {@Content}),
    		@ApiResponse(responseCode = "400", description = "Bad Request",
            content = { @Content}),
    })
	//Anotación para controlar el timepo que tarda en ejecutarse un endpoint
	@RequestExecutionTime
    @ModelAttribute
    @GetMapping(value = "/get-all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<HerosOutput> getHeros(){
		log.info("Llamada al endpoint getHeros");
		List<Hero> heros = heroService.getHeros();
		HerosOutput output = new HerosOutput(HEROS, heros);
		
		if (heros.isEmpty()) { 
			log.info("204: No content.");
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			log.info("200: "+heros);
			return new ResponseEntity<>(output, HttpStatus.OK);
		}
		
	}
	
	@Operation(summary = "get one hero", description = "Get one hero by id")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Found the hero",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Hero.class)) }),
    		@ApiResponse(responseCode = "204", description = "no content",
            content = { @Content}),
    		@ApiResponse(responseCode = "400", description = "Bad Request",
            content = { @Content}),
    })
	@RequestExecutionTime
    @ModelAttribute
    @GetMapping(value = "/hero/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Hero> getHero(@PathVariable String id){
		log.info("Llamada al endpoint getHero");
		    Optional<Hero> hero = heroService.getHeroById(id);
		    if (!hero.isPresent()) {
		    	log.info("204: No content");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				log.info("200: "+hero.get());
				return new ResponseEntity<>(hero.get(), HttpStatus.OK);
			}
	}
	
	@Operation(summary = "get list of heros", description = "Get one heroa like a string")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Found a list of heros",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = HerosOutput.class)) }),
    		@ApiResponse(responseCode = "204", description = "no content",
            content = { @Content}),
    		@ApiResponse(responseCode = "400", description = "Bad Request",
            content = { @Content}),
    })
	@RequestExecutionTime
    @ModelAttribute
    @GetMapping(value = "/hero-like/{cadena}", produces = { MediaType.APPLICATION_JSON_VALUE })	
	public ResponseEntity<HerosOutput> getHerosByString(@PathVariable String cadena){
		log.info("Llamada al endpoint getHerosByString");
		List<Hero> heros = heroService.getHeroContaining(cadena);
		HerosOutput output = new HerosOutput(HEROS, heros);
		if (heros.isEmpty()) {
			log.info("204: No content");
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			log.info("200: "+heros);
			return new ResponseEntity<>(output, HttpStatus.OK);
		}
	}
	
	@Operation(summary = "update one hero", description = "Update one hero by id")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Update the hero",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Hero.class)) }),
    		@ApiResponse(responseCode = "204", description = "no content",
            content = { @Content}),
    		@ApiResponse(responseCode = "400", description = "Bad Request",
            content = { @Content}),
    })
	@RequestExecutionTime
    @ModelAttribute
    @PostMapping(value = "/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Hero> updateHero(@RequestBody Hero hero){
		log.info("Llamada al endpoint updateHero");
		Optional<Hero> heroid = heroService.getHeroById(String.valueOf(hero.getId()));
		
		 if (heroid.isEmpty()) {
			 log.info("400: No existe el registro para ese ID.");
			 throw new HerosException(HttpStatus.BAD_REQUEST,"No existe el registro para ese ID.");
		} else {
			log.info("200: "+hero);
			return new ResponseEntity<>(heroService.updateHero(hero), HttpStatus.OK);
		}

	}
	
	@Operation(summary = "delete one hero", description = "delete one hero by id")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Delete the hero",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Hero.class)) }),
    		@ApiResponse(responseCode = "204", description = "no content",
            content = { @Content}),
    		@ApiResponse(responseCode = "400", description = "Bad Request",
            content = { @Content}),
    })
	@RequestExecutionTime
    @ModelAttribute
    @PostMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<DeleteOutput> deleteHeros(@PathVariable String id){			
		log.info("Llamada al endpoint deleteHeros");
		if (!heroService.deleteHero(id)) {
			log.info("400: No se ha podido eliminar el registro, prueba con otro ID");
			throw new HerosException(HttpStatus.BAD_REQUEST,"No se ha podido eliminar el registro, prueba con otro ID");
		} else {
			log.info("200: Registro con ID "+id+" eliminado correctamente");
			return new ResponseEntity<>(new DeleteOutput("Registro con ID "+id+" eliminado correctamente"), HttpStatus.OK);
		}
	}
	
}
