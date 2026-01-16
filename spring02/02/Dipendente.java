package com.example.spring01.entities;

@Entity
@Table(name="dipendenti")
public class Dipendente extends Persona {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="stipendio", nullable=false)
	private double stipendio;
	
	@
}
