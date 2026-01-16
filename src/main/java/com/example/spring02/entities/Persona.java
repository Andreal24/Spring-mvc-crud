package com.example.spring02.entities;

import java.time.LocalDate;


import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;

import jakarta.persistence.MappedSuperclass;
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