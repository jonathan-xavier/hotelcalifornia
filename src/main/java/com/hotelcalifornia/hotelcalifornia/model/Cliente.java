package com.hotelcalifornia.hotelcalifornia.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Cria os construtores padrão
@AllArgsConstructor
//Cria um construtor
@NoArgsConstructor
//Cria os getters e setters equals e hash code.
@Data
//com essa anotação digo a entidade pode ser mapeada pelo spring
@Entity
//Aqui eu digo que o nome da minha tabela é "cliente" 
@Table(name = "cliente")
public class Cliente {
	//id da minha tabela
	@Id
	private String cpf;
	
	private String nome;
	private int idade;
	private String telefone;
	
	private String email;
	
	//Ligando o hotel ao cliente @ManyToOne == Muitos clientes para um Hotel
	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
}
