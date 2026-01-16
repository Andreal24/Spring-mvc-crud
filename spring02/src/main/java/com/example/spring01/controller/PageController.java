package com.example.spring01.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

		
			Persona pnew=persService.insertPersona(p);
			String msg = (p.getId() == 0) ? "Inserimento avvenuto!" : "Modifica avvenuta con successo!";
			model.addAttribute("msg", msg);
			model.addAttribute("pers", pnew);

			return "home";
	}

	@GetMapping("/gestione")
	public String visualizzaLista(Model model) {
		List<Persona> listaPersone = persService.getTutteLePersone();
		model.addAttribute("persone", listaPersone);
		return "elenco"; 
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id, Model model) {
		try {
			persService.deletePersona(id);
			model.addAttribute("msg", "Persona eliminata correttamente");
		} catch (Exception e) {
			model.addAttribute("msg", "Impossibile eliminare la persona");
		}
		return "home"; 
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") int id, Model model) {
		Persona p = persService.getPersonaById(id);
		model.addAttribute("p", p);
		return "form"; 
	}
	
	@PostMapping("/gestione/cerca/cf")
	public String postRicercaCf(String cf) {
		if (cf == null || cf.trim().isEmpty()) {
			return "redirect:/gestione";
		}
		return "redirect:/gestione/cf/" + cf.trim();
	}
	
	@PostMapping("/gestione/cerca/nc")
	public String postRicercaNc(String nome, String cognome) {
		String n = (nome == null || nome.trim().isEmpty()) ? "*" : nome.trim();
		String c = (cognome == null || cognome.trim().isEmpty()) ? "*" : cognome.trim();
		
		return "redirect:/gestione/nc/" + n + "/" + c;
	}
	
	@GetMapping("/gestione/cf/{cf}")
	public String getByCf(@PathVariable("cf") String cf, Model model) {
		List<Persona> listaPersone = persService.ricercaPersone(cf, null, null);
		model.addAttribute("persone", listaPersone);
		return "elenco";
	}
	
	@GetMapping("/gestione/nc/{nome}/{cognome}")
	public String getByNomeCognome(
			@PathVariable(required=false) String nome,
			@PathVariable(required=false) String cognome,
			Model model) {
		
		List<Persona> listaPersone = persService.ricercaPersone(null, nome, cognome);
		model.addAttribute("persone", listaPersone);
		return "elenco";
	}



}
