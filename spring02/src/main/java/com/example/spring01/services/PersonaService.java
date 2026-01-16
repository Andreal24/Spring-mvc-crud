package com.example.spring01.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring01.entities.Persona;
import com.example.spring01.repositories.IPersonaRepository;




@Service
public class PersonaService {

	
private final IPersonaRepository personaRepo;

public PersonaService(IPersonaRepository personaRepo)
{
	this.personaRepo=personaRepo;
}


public Persona insertPersona(Persona p)
{
	return personaRepo.save(p);
}

public List<Persona> getTutteLePersone() {
    return personaRepo.findAll();
}

public List<Persona> getPersoneByNomeCognome(String nome, String cognome) {
    return personaRepo.findByNomeStartingWithIgnoreCaseAndCognomeStartingWithIgnoreCase(nome, cognome);
}

public void deletePersona(int id) {
    personaRepo.deleteById(id);
}

public Persona getPersonaById(int id) {
	return personaRepo.findById(id).orElse(null);
}

public List<Persona> ricercaPersone(String cf, String nome, String cognome) {
    if (cf != null && !cf.isEmpty() && !cf.equals("*")) {
        return personaRepo.findByCfIgnoreCase(cf);
    }
    boolean hasNome = (nome != null && !nome.trim().isEmpty() && !nome.equals("*"));
    boolean hasCognome = (cognome != null && !cognome.trim().isEmpty() && !cognome.equals("*"));
    if (hasNome && hasCognome) {
        return personaRepo.findByNomeStartingWithIgnoreCaseAndCognomeStartingWithIgnoreCase(nome, cognome);
    }
    else if (hasNome) {
        return personaRepo.findByNomeStartingWithIgnoreCase(nome);
    }
    else if (hasCognome) {
        return personaRepo.findByCognomeStartingWithIgnoreCase(cognome);
    }
    return personaRepo.findAll();
}


}
