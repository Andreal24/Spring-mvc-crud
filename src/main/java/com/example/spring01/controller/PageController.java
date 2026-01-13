package com.example.spring01.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring01.entities.Persona;
import com.example.spring01.services.PersonaService;




//componente controller per la gestione delle richieste HTTP
// e risposte

@Controller
public class PageController {
	
	
	private final PersonaService persService;
	
	public PageController(PersonaService persService) {
		this.persService=persService;
	}
	
	
	
	@GetMapping("/")
	public String root()
	{
		return "redirect:home";
	}
	
	@GetMapping("/home")
	public String home()
	{
		return "home"; //richiama la view home.html
	}
	
	
	@GetMapping("/new")
	public String create(Model model)
	{
		model.addAttribute("p",new Persona());
		
		return "form"; //richiama la view home.html
	}
	
	
	//dopo aver cliccato su invia
	@PostMapping("/new")
	public String create(@ModelAttribute("p") Persona p, Model model)
	{
		//System.out.println(p);
		try
		{
		Persona pnew=persService.insertPersona(p);
		
		model.addAttribute("msg", "Inserimento avvenuto!");
		model.addAttribute("pers", pnew);
		
		return "home";
		}catch (DataIntegrityViolationException e) {
			
			model.addAttribute("msg", "Errore! inserimento non avvenuto cf già presente!"+e.getMessage());
			return "error";
		}
		
		
		
	}
	
	
	@GetMapping("/gestione")
	public String selectAll(Model model)
	{
		
		model.addAttribute("item", persService.selectAll());
		return "gestione";
		
	}
	
	
	@GetMapping("/{id}/delete")
	public String deleteById(Model model, @PathVariable Integer id)
	{
		
		//System.out.println(id);
		persService.deleteById(id);
		model.addAttribute("msg", "Persona eliminata correttamente!");
		return "home";
		
	}
	
	
	
	

}
