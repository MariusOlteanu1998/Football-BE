package com.myProject.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myProject.demo.model.SchedaModel;
import com.myProject.demo.model.UserModel;
import com.myProject.demo.repository.SchedaRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class SchedaService {

    private static final Logger logger = LoggerFactory.getLogger(SchedaService.class);

    @Autowired
    private SchedaRepository schedaRepo;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public SchedaService(SchedaRepository schedaRepo) {
        this.schedaRepo = schedaRepo;
    }

    public List<SchedaModel> getAllScheda() {
        logger.info("Fetching all schedas");
        return schedaRepo.findAll();
    }

    public Optional<SchedaModel> getAllSchedaById(int id) {
        logger.info("Fetching schedas with id: {}", id);
        return schedaRepo.findById(id);
    }

    public SchedaModel updateSchedaById(int id, @Valid SchedaModel updateSchedaDTO) {
        logger.info("Updating scheda with id: {}", id);
        return schedaRepo.findById(id)
                .map(existingScheda -> {
                    try {
                        // Convertire DTO in Model
                        SchedaModel updateScheda = objectMapper.convertValue(updateSchedaDTO, SchedaModel.class);

                        // Aggiornare solo i campi presenti nel DTO
                        if (updateScheda.getDataCreazione() != null) {
                            existingScheda.setDataCreazione(updateScheda.getDataCreazione());
                        }
                        if (updateScheda.getDataFine() != null) {
                            existingScheda.setDataFine(updateScheda.getDataFine());
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
                        //String changes = getSchedaChanges(existingScheda, existingScheda);

                        logger.info("Scheda with ID: {} updated successfully.", id);
                        return savedScheda;
                    } catch (IllegalArgumentException e) {
                        logger.error("Error converting SchedaDTO to SchedaModel: {}", e.getMessage());
                        throw new RuntimeException("Conversion error");
                    }
                })
                .orElseThrow(() -> {
                    logger.warn("Scheda with ID: {} not found for update", id);
                    return new RuntimeException("Scheda not found with ID: " + id);
                });
    }

    public SchedaModel insertScheda(@Valid SchedaModel schedaDTO) {
        logger.info("Inserting scheda");
        SchedaModel schedaModel = objectMapper.convertValue(schedaDTO, SchedaModel.class);
        SchedaModel newScheda = schedaRepo.save(schedaModel);
        logger.info("Scheda with ID: {} inserted successfully", newScheda.getId());
        return newScheda;
    }

    public void deleteSchedaById(int id) {
        logger.info("Deleting scheda with id: {}", id);
        schedaRepo.deleteById(id);
        logger.info("Scheda with ID: {} deleted successfully", id);
    }

//    private String getSchedaChanges(SchedaModel oldScheda, SchedaModel newScheda) {
//        StringBuilder changes = new StringBuilder();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//
//        if (!oldScheda.getDataCreazione().equals(newScheda.getDataCreazione())) {
//            changes.append(String.format("Data creazione changed from '%s' to '%s'. ",
//                    formatter.format(oldScheda.getDataCreazione()), formatter.format(newScheda.getDataCreazione())));
//        }
//        if (!oldScheda.getDataFine().equals(newScheda.getDataFine())) {
//            changes.append(String.format("Data fine changed from '%s' to '%s'. ",
//                    formatter.format(oldScheda.getDataFine()), formatter.format(newScheda.getDataFine())));
//        }
//        if (!oldScheda.getEsercizio().equals(newScheda.getEsercizio())) {
//            changes.append(String.format("Esercizio changed from '%s' to '%s'. ", oldScheda.getEsercizio(), newScheda.getEsercizio()));
//        }
//        if (!oldScheda.getReps().equals(newScheda.getReps())) {
//            changes.append(String.format("Reps changed from '%s' to '%s'. ", oldScheda.getReps(), newScheda.getReps()));
//        }
//        if (!oldScheda.getRecupero().equals(newScheda.getRecupero())) {
//            changes.append(String.format("Recupero changed from '%s' to '%s'. ", oldScheda.getRecupero(), newScheda.getRecupero()));
//        }
//
//        return changes.toString();
//    }

}
