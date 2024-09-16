package com.myProject.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.myProject.demo.model.SchedaModel;
import com.myProject.demo.service.SchedaService;


@RestController
@RequestMapping("/scheda")
@CrossOrigin(origins = "https://localhost:4200")
public class SchedaController {

    @Autowired
    SchedaService schedaService;

    public SchedaController(SchedaService schedaService) {
        this.schedaService = schedaService;
    }

    @GetMapping
    public List<SchedaModel> getAllScheda(){
        return schedaService.getAllScheda();
    }

    @GetMapping("/{id}")
    public Optional<SchedaModel> getSchedaById(@PathVariable int id){
        return schedaService.getAllSchedaById(id);
    }

    @PutMapping("/{id}")
    public Optional<SchedaModel> updateSchedaById(@PathVariable int id, @RequestBody SchedaModel schedaModel){
        return schedaService.updateSchedaById(id, schedaModel);
    }

    @PostMapping
    public SchedaModel insertScheda(@RequestBody SchedaModel schedaModel){
        return schedaService.insertScheda(schedaModel);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedaById(@PathVariable int id){
        schedaService.deleteSchedaById(id);
    }

}
