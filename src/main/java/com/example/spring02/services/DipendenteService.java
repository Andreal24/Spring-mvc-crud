package com.example.spring02.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring02.entities.Dipendente;
import com.example.spring02.repositories.IDipendenteRepository;

@Service
public class DipendenteService {

    private final IDipendenteRepository repo;

    public DipendenteService(IDipendenteRepository repo) {
        this.repo = repo;
    }

    public Dipendente insert(Dipendente d) {
        return repo.save(d);
    }

    public List<Dipendente> selectAll() {
        return repo.findAll();
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public Dipendente searchByCf(String cf) {
        return repo.findByCf(cf);
    }

    public List<Dipendente> searchByNomeAndCognome(String nome, String cognome) {
        return repo.findByNomeAndCognome(nome, cognome);
    }
    
    public List<Dipendente> searchByDataDiNascita(LocalDate data) {
        return repo.findByDataDiNascita(data);
    }
}
