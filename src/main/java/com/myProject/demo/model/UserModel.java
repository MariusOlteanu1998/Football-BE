package com.myProject.demo.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "anno_nascita")
	private LocalDate anno_nascita;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "cf", unique = true)
	private String cf;

	public UserModel() {}

	public UserModel(int id, String nome, String cognome, LocalDate anno_nascita, String email, String password, String cf) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.anno_nascita = anno_nascita;
		this.email = email;
		this.password = password;
		this.cf = cf;
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getAnno_nascita() {
		return anno_nascita;
	}

	public void setAnno_nascita(LocalDate anno_nascita) {
		this.anno_nascita = anno_nascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	@Override
	public String toString() {
		return "UserModel{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", cognome='" + cognome + '\'' +
				", anno_nascita=" + anno_nascita +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", cf='" + cf + '\'' +
				'}';
	}
}
