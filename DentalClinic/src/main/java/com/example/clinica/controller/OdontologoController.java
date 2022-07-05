package com.example.clinica.controller;


import com.example.clinica.dao.impl.OdontologoDaoH2;
import com.example.clinica.model.Odontologo;
import com.example.clinica.service.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @PostMapping
    public ResponseEntity<Odontologo> registrar(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        ResponseEntity<String> response;
        odontologoService.eliminar(id);

        response = ResponseEntity.status(HttpStatus.OK).body("Eliminado");
        return response;
    }


    @GetMapping("/{id}")
    public Odontologo buscarPorId(@PathVariable Integer id){
        return odontologoService.buscar(id);
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo){
        System.out.println(odontologo);
        ResponseEntity<Odontologo> response;

        if (odontologo.getId() != null && odontologoService.buscar(odontologo.getId()) != null) {
            response=ResponseEntity.ok(odontologoService.actualizar(odontologo));

        } else {

            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }


}
