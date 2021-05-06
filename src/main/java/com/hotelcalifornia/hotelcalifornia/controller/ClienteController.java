package com.hotelcalifornia.hotelcalifornia.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import com.hotelcalifornia.hotelcalifornia.model.Cliente;
import com.hotelcalifornia.hotelcalifornia.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	//listar todos os clientes http://localhost:8090/cliente
	@GetMapping
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	//listar por id http://localhost:8090/cliente/{id}
	@GetMapping(value = "{id}")
	public ResponseEntity<Cliente> findById(@PathVariable String id){
		return clienteRepository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//salvar cliente http://localhost:8090/cliente
	@PostMapping
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente, HttpServletResponse response){
		Cliente clienteSalvo = clienteRepository.save(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clienteSalvo.getCpf()).toUri();
			response.setHeader("Location", uri.toASCIIString());
			
			return ResponseEntity.created(uri).body(clienteSalvo);
	}
	
	//atualizar cliente http://localhost:8090/cliente/{id}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> update(@PathVariable String id, @RequestBody Cliente cliente){
		return clienteRepository.findById(id)
				.map(record ->{
					record.setCpf(cliente.getCpf());
					record.setNome(cliente.getNome());
					record.setIdade(cliente.getIdade());
					record.setTelefone(cliente.getTelefone());
					record.setEmail(cliente.getEmail());
					record.setHotel(cliente.getHotel());
					Cliente update = clienteRepository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	//deletar cliente http://localhost:8090/cliente/{id}
	@DeleteMapping(value = "{id}")
	public ResponseEntity delete(@PathVariable String id) {
		return clienteRepository.findById(id)
				.map(record ->{
					clienteRepository.deleteById(id);
					return ResponseEntity.ok().body("Deletado com sucesso!");
				}).orElse(ResponseEntity.notFound().build());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//deletar cliente
	
	//falta fazer isso e as validações.
}
