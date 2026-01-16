package com.example.spring02.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.spring02.entities.Dipendente;
import com.example.spring02.entities.Ruolo;
import com.example.spring02.services.DipendenteService;

@Controller
public class PageController {

    private final DipendenteService dipService;

    public PageController(DipendenteService dipService) {
        this.dipService = dipService;
    }

   
    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

  
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("d", new Dipendente());    
        model.addAttribute("ruoli", Ruolo.values());   
        return "form";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("d") Dipendente d, Model model) {
        try {
            Dipendente dNew = dipService.insert(d);

            model.addAttribute("msg", "Inserimento avvenuto!");
            model.addAttribute("pers", dNew);

            return "home";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("msg", "Errore! Inserimento non avvenuto: CF già presente. " + e.getMessage());
            return "error";
        }
    }


    @GetMapping("/gestione")
    public String gestione(Model model) {
        model.addAttribute("item", dipService.selectAll());
        return "gestione";
    }

    
    @GetMapping("/gestione/cf")
    public String gestioneByCf(@RequestParam String cf, Model model) {
        List<Dipendente> risultati = new ArrayList<>();

        if (cf != null && !cf.trim().isEmpty()) {
            Dipendente d = dipService.searchByCf(cf.trim());
            if (d != null) risultati.add(d);
        }

        if (risultati.isEmpty()) {
            model.addAttribute("msg", "Nessun risultato trovato.");
        }

        model.addAttribute("open", "cf");
        model.addAttribute("item", risultati);
        return "gestione";
    }

 
    @GetMapping("/gestione/nome-cognome")
    public String gestioneByNomeCognome(@RequestParam String nome,
                                        @RequestParam String cognome,
                                        Model model) {

        List<Dipendente> risultati = new ArrayList<>();

        if (nome != null && !nome.trim().isEmpty() && cognome != null && !cognome.trim().isEmpty()) {
            risultati = dipService.searchByNomeAndCognome(nome.trim(), cognome.trim());
        }

        if (risultati.isEmpty()) {
            model.addAttribute("msg", "Nessun risultato trovato.");
        }

        model.addAttribute("open", "nc");
        model.addAttribute("item", risultati);
        return "gestione";
    }
    
    @GetMapping("/gestione/data-nascita")
    public String gestioneByDataDiNascita(@RequestParam("data") LocalDate data, Model model) {

        List<Dipendente> risultati = dipService.searchByDataDiNascita(data);

        if (risultati == null || risultati.isEmpty()) {
            model.addAttribute("msg", "Nessun risultato trovato.");
        }

        model.addAttribute("open", "dn");      
        model.addAttribute("item", risultati);
        return "gestione";
    }

    
    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id, Model model) {
        dipService.deleteById(id);
        model.addAttribute("msg", "Dipendente eliminato correttamente!");
        return "home";
    }
}
