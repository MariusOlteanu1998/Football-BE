package com.myProject.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.myProject.demo.model.SchedaModel;
import com.myProject.demo.repository.SchedaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedaService {

    private static final Logger logger = LoggerFactory.getLogger(SchedaService.class);

    @Autowired
    private SchedaRepository schedaRepo;

    public List<SchedaModel> getAllScheda(){
        logger.info("Fetching all schedas");
        return schedaRepo.findAll();
    }

    public Optional<SchedaModel> getAllSchedaById(int id){
        logger.info("Fetching schedas with id: {}", id);
        return schedaRepo.findById(id);
    }

    public SchedaModel updateSchedaById(int id, SchedaModel updateScheda){
        logger.info("Updating scheda with id: {}", id);
        return schedaRepo.findById(id)
                .map(existingScheda -> {
                    // Aggiorna solo i campi che sono stati forniti
                    if (updateScheda.getData_creazione() != null) {
                        existingScheda.setData_creazione(updateScheda.getData_creazione());
                    }
                    if (updateScheda.getData_fine() != null) {
                        existingScheda.setData_fine(updateScheda.getData_fine());
                    }
                    if (updateScheda.getEsercizio() != null) {
                        existingScheda.setEsercizio(updateScheda.getEsercizio());
                    }
                    if (updateScheda.getReps() != null) {
                        existingScheda.setReps(updateScheda.getReps());
                    }
                    if (updateScheda.getRecupero() != null) {
                        existingScheda.setRecupero(updateScheda.getRecupero());
                    }

                    // Salva l'oggetto aggiornato
                    SchedaModel savedScheda = schedaRepo.save(existingScheda);

                    // Ottieni le modifiche
                    String changes = getSchedaChanges(existingScheda, savedScheda);
                    logger.info("Scheda with ID: {} updated successfully. Changes: {}", id, changes);
                    return savedScheda;
                })
                .orElseThrow(() -> {
                    logger.warn("Scheda with ID: {} not found for update", id);
                    return new RuntimeException("Scheda not found with ID: " + id);
                });
    }

    public SchedaModel insertScheda(SchedaModel scheda){
        logger.info("Inserting scheda with id: {}", scheda.getId());
        SchedaModel newScheda = schedaRepo.save(scheda);
        logger.info("Scheda with ID: {} inserted successfully", newScheda.getId());
        return newScheda;
    }

    public void deleteSchedaById(int id){
        logger.info("Deleting scheda with id: {}", id);
        schedaRepo.deleteById(id);
        logger.info("Scheda with ID: {} deleted successfully", id);
    }

    private String getSchedaChanges(SchedaModel oldScheda, SchedaModel newScheda) {
        StringBuilder changes = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if (!oldScheda.getData_creazione().equals(newScheda.getData_creazione())) {
            changes.append(String.format("Data creazione changed from '%s' to '%s'. ",
                    formatDate(oldScheda.getData_creazione(), dateFormat), formatDate(newScheda.getData_creazione(), dateFormat)));
        }
        if (!oldScheda.getData_fine().equals(newScheda.getData_fine())) {
            changes.append(String.format("Data fine changed from '%s' to '%s'. ",
                    formatDate(oldScheda.getData_fine(), dateFormat), formatDate(newScheda.getData_fine(), dateFormat)));
        }
        if (!oldScheda.getEsercizio().equals(newScheda.getEsercizio())) {
            changes.append(String.format("Esercizio changed from '%s' to '%s'. ", oldScheda.getEsercizio(), newScheda.getEsercizio()));
        }
        if (!oldScheda.getReps().equals(newScheda.getReps())) {
            changes.append(String.format("Reps changed from '%s' to '%s'. ", oldScheda.getReps(), newScheda.getReps()));
        }
        if (!oldScheda.getRecupero().equals(newScheda.getRecupero())) {
            changes.append(String.format("Recupero changed from '%s' to '%s'. ", oldScheda.getRecupero(), newScheda.getRecupero()));
        }

        return changes.toString();
    }

    private String formatDate(Date date, SimpleDateFormat dateFormat) {
        return date != null ? dateFormat.format(date) : "null";
    }
}
