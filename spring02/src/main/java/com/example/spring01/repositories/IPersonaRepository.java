package com.example.spring01.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring01.entities.Persona;

public interface IPersonaRepository extends JpaRepository<Persona, Integer>{
	
	List<Persona> findByNomeStartingWithIgnoreCaseAndCognomeStartingWithIgnoreCase(String nome, String cognome);
	List<Persona> findByNomeStartingWithIgnoreCase(String nome);
	List<Persona> findByCognomeStartingWithIgnoreCase(String cognome);
	List<Persona> findByCfIgnoreCase(String cf);	
}
