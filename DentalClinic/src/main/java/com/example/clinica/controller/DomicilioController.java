package com.example.clinica.controller;

import com.example.clinica.model.Domicilio;
import com.example.clinica.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    @Autowired
    private DomicilioService domicilioService;

    @PostMapping
    public ResponseEntity<Domicilio> registrar(@RequestBody Domicilio domicilio){
        return ResponseEntity.ok(domicilioService.guardar(domicilio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        ResponseEntity<String> response;
        domicilioService.eliminar(id);

        response = ResponseEntity.status(HttpStatus.OK).body("Eliminado");
        return response;
    }


    @GetMapping("/{id}")
    public Domicilio buscarPorId(@PathVariable Integer id){
        return domicilioService.buscar(id);
    }

    @GetMapping
    public ResponseEntity<List<Domicilio>> buscarTodos(){
        return ResponseEntity.ok(domicilioService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<Domicilio> actualizar(@RequestBody Domicilio domicilio){
        ResponseEntity<Domicilio> response;

        if (domicilio.getId() != null && domicilioService.buscar(domicilio.getId()) != null) {
            response=ResponseEntity.ok(domicilioService.actualizar(domicilio));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

}
