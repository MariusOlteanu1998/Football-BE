package com.myProject.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class SchedaDTO {

    private int id;

    @NotNull(message = "La data di creazione non può essere nulla")
    private Date data_creazione;

    @NotNull(message = "La data di fine non può essere nulla")
    private Date data_fine;

    @NotNull(message = "L'esercizio non può essere nullo")
    @Size(min = 3, max = 100, message = "Il nome dell'esercizio deve avere tra 3 e 100 caratteri")
    private String esercizio;

    @NotNull(message = "Le ripetizioni non possono essere nulle")
    private String reps;

    @NotNull(message = "Il recupero non può essere nullo")
    private String recupero;

    public SchedaDTO() {}

    public SchedaDTO(int id, Date data_creazione, Date data_fine, String esercizio, String reps, String recupero) {
        this.id = id;
        this.data_creazione = data_creazione;
        this.data_fine = data_fine;
        this.esercizio = esercizio;
        this.reps = reps;
        this.recupero = recupero;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData_creazione() {
        return data_creazione;
    }

    public void setData_creazione(Date data_creazione) {
        this.data_creazione = data_creazione;
    }

    public Date getData_fine() {
        return data_fine;
    }

    public void setData_fine(Date data_fine) {
        this.data_fine = data_fine;
    }

    public String getEsercizio() {
        return esercizio;
    }

    public void setEsercizio(String esercizio) {
        this.esercizio = esercizio;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getRecupero() {
        return recupero;
    }

    public void setRecupero(String recupero) {
        this.recupero = recupero;
    }
}
