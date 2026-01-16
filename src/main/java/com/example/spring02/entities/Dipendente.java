package com.example.spring02.entities;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="dipendenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dipendente extends Persona {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="stipendio", nullable=false)
	private double stipendio;
	
	@Column(name="data_di_assunzione",nullable=false)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDate dataDiAssunzione;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Ruolo ruolo;
}
