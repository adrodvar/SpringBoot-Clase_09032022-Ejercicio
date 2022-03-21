package com.example.controllers;

import com.example.entities.Manufacturer;
import com.example.repositories.ManufacturerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ManufacturerController {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerController(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    // GET http://localhost:8080/manufacturers
    @GetMapping("/manufacturers")
    public List<Manufacturer> findAll(){
        return this.manufacturerRepository.findAll();
    }

//    @GetMapping("/manufacturers/{id}")
//    public Manufacturer findById(@PathVariable Long id){
//        return this.manufacturerRepository.findById(id).orElse(null);
//    }

    @GetMapping("/manufacturers/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id){

        // Opción 1: tradicional
//        Optional<Manufacturer> manufacturerOpt = this.manufacturerRepository.findById(id);
//        if(manufacturerOpt.isPresent())
//            return ResponseEntity.ok(manufacturerOpt.get());
//
//        return ResponseEntity.notFound().build();
////        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        // Opción 2: enfoque funcional
        return this.manufacturerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST http://localhost:8080/manufacturers crear nuevos manufacturers
    @PostMapping("/manufacturers")
    public ResponseEntity<Manufacturer> save(@RequestBody Manufacturer manufacturer){
        if(manufacturer.getId() != null) // Si el id es distinto de nulo quiere decir que ya existe el manufacturer y por tanto no hay que crear, hay que actualizar
            return ResponseEntity.badRequest().build();

        Manufacturer manufacturerDB = this.manufacturerRepository.save(manufacturer);
        return ResponseEntity.ok(manufacturerDB);
    }

    @PutMapping("/manufacturers")
    public ResponseEntity<Manufacturer> update(@RequestBody Manufacturer manufacturer){
        if(manufacturer.getId() == null) // si el id es nulo no se puede actualizar algo que no existe, habría que crearlo primero con PostMapping
            return ResponseEntity.badRequest().build();

        Manufacturer manufacturerDB = this.manufacturerRepository.save(manufacturer);
        return ResponseEntity.ok(manufacturerDB);
    }


    @DeleteMapping("/manufacturers/{id}")
    public ResponseEntity<Manufacturer> deleteById(@PathVariable Long id) {
        this.manufacturerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path="/manufacturers/year/{year}")
    public List<Manufacturer>findByYear(@PathVariable("year") String year){

        List<Manufacturer> manufacturerDB = this.manufacturerRepository.findByYear(Math.toIntExact(Long.parseLong(year)));
        return manufacturerDB;
    }

}









