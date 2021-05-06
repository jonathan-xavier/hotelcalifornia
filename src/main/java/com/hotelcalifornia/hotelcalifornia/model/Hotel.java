package com.hotelcalifornia.hotelcalifornia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//cria o construtor padrão
@AllArgsConstructor
//Cria um construtor
@NoArgsConstructor
//cria os getters setters equals e hash code.
@Data
//com essa anotação digo a entidade pode ser mapeada pelo spring
@Entity
//Aqui eu digo que o nome da minha tabela é "hotel"
@Table(name = "hotel")
public class Hotel {

	//id da minha classe hotel
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matricula;
	
	
	private String nome;
	
	//colocando o nome da coluna que eu dei na criação do banco de dados.
	@Column(name = "valor_diaria")
	private Float ValorDiaria;
	
	private String cidade;
	
	
	private Float estrelas;
	
}
