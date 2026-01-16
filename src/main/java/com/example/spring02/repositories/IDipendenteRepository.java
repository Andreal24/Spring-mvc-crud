package com.example.spring02.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring02.entities.Dipendente;

public interface IDipendenteRepository extends JpaRepository<Dipendente, Long> {

    Dipendente findByCf(String cf);

    List<Dipendente> findByNome(String nome);

    List<Dipendente> findByNomeAndCognome(String nome, String cognome);
    
    List<Dipendente> findByDataDiNascita(LocalDate dataDiNascita);
}