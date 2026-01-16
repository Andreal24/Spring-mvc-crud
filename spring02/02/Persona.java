package com.example.spring01.entities;

import java.util.Objects;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

	@Column(name="nome",nullable=false)
	private String nome;
	
	@Column(name="cognome",nullable=false)
	private String cognome;
	
	@Column(name="codice_fiscale",nullable=false,unique=true)
	private String cf;
	
	@Column(name="dataDiNascita", nullable=false)
	//@DateTimeFormat(iso=ISO.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy") 
	private LocalDate dataDiNascita;
	
}
