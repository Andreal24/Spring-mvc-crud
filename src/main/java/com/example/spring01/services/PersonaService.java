package com.example.spring01.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
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
	
	//personaRepo.findById(null)
	//personaRepo.existsById(null)
	//personaRepo.findAll();
	//personaRepo.count();
	//personaRepo.delete(p);
	//personaRepo.deleteAll();
	
	//personaRepo.findAll(Pageable page);
	
	
	return personaRepo.save(p);

	
}


public List<Persona> selectAll(){
	
	return personaRepo.findAll();
}


public void deleteById(Integer id)
{
	personaRepo.deleteById(id);

}






}
