package com.myProject.demo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class SchedaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "data_creazione")
    private Date data_creazione;
    @Column(name = "data_fine")
    private Date data_fine;
    @Column(name = "esercizio")
    private String esercizio;
    @Column(name = "reps")
    private String reps;
    @Column(name = "recupero")
    private String recupero;

    public SchedaModel() {}

    public SchedaModel(int id, Date data_creazione, Date data_fine, String esercizio, String reps, String recupero) {
        this.id = id;
        this.data_creazione = data_creazione;
        this.data_fine = data_fine;
        this.esercizio = esercizio;
        this.reps = reps;
        this.recupero = recupero;
    }

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

    @Override
    public String toString() {
        return "SchedaModel{" + "id=" + id + ", data_creazione=" + data_creazione + ", data_fine=" + data_fine +
               ", esercizio='" + esercizio + '\'' + ", reps='" + reps + '\'' + ", recupero='" + recupero + '\'' +'}';
    }
}

//users: User[];