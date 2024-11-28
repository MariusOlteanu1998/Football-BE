//package com.myProject.demo.controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import com.myProject.demo.dto.SchedaDTO;
//import com.myProject.demo.model.SchedaModel;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//
//import com.myProject.demo.service.SchedaService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/scheda")
//@CrossOrigin(origins = "https://localhost:4200")
//@Validated
//public class SchedaController {
//
//    private static final Logger logger = LoggerFactory.getLogger(SchedaController.class);
//
//    SchedaService schedaService;
//
//    @Autowired
//    public SchedaController(SchedaService schedaService) {
//        this.schedaService = schedaService;
//    }
//
//    @GetMapping
//    public List<SchedaModel> getAllScheda(){
//        logger.info("Received request to fetch all schedas");
//        return schedaService.getAllScheda();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<SchedaModel> getSchedaById(@PathVariable int id){
//        logger.info("Received request to fetch scheda with id {}", id);
//        return schedaService.getAllSchedaById(id);
//    }
//
//    @PutMapping("/{id}")
//    public Optional<SchedaModel> updateSchedaById(@PathVariable int id, @Valid @RequestBody SchedaModel schedaModel){
//        logger.info("Received request to update scheda with id {}", id);
//        return Optional.ofNullable(schedaService.updateSchedaById(id, schedaModel));
//    }
//
//    @PostMapping
//    public SchedaModel insertScheda(@Valid @RequestBody SchedaModel schedaModel){
//        logger.info("Received request to insert scheda with id {}", schedaModel.getId());
//        return schedaService.insertScheda(schedaModel);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteSchedaById(@PathVariable int id){
//        logger.info("Received request to delete scheda with id {}", id);
//        schedaService.deleteSchedaById(id);
//    }
//}
//
