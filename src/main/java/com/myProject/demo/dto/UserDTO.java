package com.myProject.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class UserDTO {

    private int id;

    @NotBlank(message = "Il nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "Il cognome non può essere vuoto")
    private String cognome;

    @NotNull(message = "L'anno di nascita non può essere nullo")
    private Date annoNascita;

    @Email(message = "Email non valida")
    @NotBlank(message = "L'email non può essere vuota")
    private String email;

    @NotBlank(message = "La password non può essere vuota")
    private String password;

    @NotBlank(message = "Il codice fiscale non può essere vuoto")
    private String cf;

    public UserDTO() {}

    public UserDTO(int id, String nome, String cognome, Date annoNascita, String email, String password, String cf) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.annoNascita = annoNascita;
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

    public Date getAnnoNascita() {
        return annoNascita;
    }

    public void setAnnoNascita(Date annoNascita) {
        this.annoNascita = annoNascita;
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
        return "UserDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", anno_nascita=" + annoNascita +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cf='" + cf + '\'' +
                '}';
    }
}
