package com.myProject.demo.service;

import java.util.List;
import java.util.Optional;

import com.myProject.demo.model.SchedaModel;
import com.myProject.demo.repository.SchedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedaService {

    @Autowired
    private SchedaRepository schedaRepo;

    public SchedaService(SchedaRepository schedaRepo) {
        this.schedaRepo = schedaRepo;
    }

    public List<SchedaModel> getAllScheda(){
        return schedaRepo.findAll();
    }

    public Optional<SchedaModel> getAllSchedaById(int id){
        return schedaRepo.findById(id);
    }

    public Optional<SchedaModel> updateSchedaById(int id, SchedaModel updateScheda){
        /*return schedaRepo.findById(id).map(scheda ->{
            scheda.setData_creazione(updateScheda.getData_creazione());
            scheda.setData_fine(updateScheda.getData_fine());
            scheda.setEsercizio(updateScheda.getEsercizio());
            scheda.setReps(updateScheda.getReps());
            scheda.setRecupero(updateScheda.getRecupero());
            return schedaRepo.save(scheda);
        });*/

        Optional<SchedaModel> optionalScheda = schedaRepo.findById(id);

        if (optionalScheda.isPresent()) {
            SchedaModel scheda = optionalScheda.get();
            scheda.setData_creazione(updateScheda.getData_creazione());
            scheda.setData_fine(updateScheda.getData_fine());
            scheda.setEsercizio(updateScheda.getEsercizio());
            scheda.setReps(updateScheda.getReps());
            scheda.setRecupero(updateScheda.getRecupero());
            return Optional.of(schedaRepo.save(scheda));
        } else {
            return Optional.empty();
        }
    }

    public SchedaModel insertScheda(SchedaModel scheda){
        return schedaRepo.save(scheda);
    }

    public void deleteSchedaById(int id){
        schedaRepo.deleteById(id);
    }

}
