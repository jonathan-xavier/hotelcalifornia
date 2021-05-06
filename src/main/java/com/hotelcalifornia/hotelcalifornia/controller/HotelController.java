package com.hotelcalifornia.hotelcalifornia.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hotelcalifornia.hotelcalifornia.model.Hotel;
import com.hotelcalifornia.hotelcalifornia.repository.HotelRepository;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelRepository hotelRepository;

	
	//listar todos os hoteis
	@GetMapping
	public List find(){
		return hotelRepository.findAll();
	}
	
	//buscar pelo id
	@GetMapping(value = "{id}")
	public ResponseEntity<Hotel> findById(@PathVariable Long id){
		return hotelRepository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	//criar hotel
	@PostMapping
	public ResponseEntity<Hotel> create(@RequestBody Hotel hotel, HttpServletResponse response) {
		Hotel hotelSalvo =  hotelRepository.save(hotel);
		
		//Pegando o caminho atual e adicionando o id e depois setando no header essa uri.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(hotelSalvo.getMatricula()).toUri();
				response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(hotelSalvo);
	}
	
	//remover hotel
	@DeleteMapping(value = "/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		return hotelRepository.findById(id)
				.map(record ->{
					hotelRepository.deleteById(id);
					return ResponseEntity.ok().body("Deletado com sucesso!");
					
				}).orElse(ResponseEntity.notFound().build());
	}
	
	//atualizar hotel
	@PutMapping(value = "/{id}")
	public ResponseEntity<Hotel> update(@PathVariable Long id, @RequestBody Hotel hotel){
		return hotelRepository.findById(id)
				.map(record ->{
					record.setNome(hotel.getNome());
					record.setCidade(hotel.getCidade());
					record.setValorDiaria(hotel.getValorDiaria());
					record.setEstrelas(hotel.getEstrelas());
					Hotel update = hotelRepository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
