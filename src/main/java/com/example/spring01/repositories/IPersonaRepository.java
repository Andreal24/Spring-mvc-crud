package com.example.spring01.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring01.entities.Persona;

public interface IPersonaRepository extends JpaRepository<Persona, Integer>{

	List<Persona> findByNome(String nome);
	
	List<Persona> findByNomeAndCognome(String nome, String cognome);
	
	
	Persona findByCf(String cf);
	
	
}
