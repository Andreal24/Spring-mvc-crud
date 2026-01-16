package com.example.spring02.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {
	
	
		@ExceptionHandler(IllegalArgumentException.class)
		public String handlerIllegalArgumentException(IllegalArgumentException e, Model model) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}

	
		@ExceptionHandler(DataIntegrityViolationException.class)
		public String handlerDataIntegrityViolationException(DataIntegrityViolationException e, Model model) {
			model.addAttribute("errorMessage", "Errore di integrità dei dati: probabile Codice Fiscale duplicato o dati non validi.");
			return "error";
		}

	
		@ExceptionHandler(Exception.class)
		public String handlerGenericException(Exception e, Model model) {
			model.addAttribute("errorMessage", "Si è verificato un errore imprevisto: " + e.getMessage());
			return "error";
		}

}
