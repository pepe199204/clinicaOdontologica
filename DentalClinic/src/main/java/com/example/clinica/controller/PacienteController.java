package com.example.clinica.controller;

import com.example.clinica.dao.impl.PacienteDaoH2;
import com.example.clinica.model.Paciente;
import com.example.clinica.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")

public class PacienteController {

    private PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

    //GUARDAR
    @PostMapping
    public ResponseEntity<Paciente> registrar(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        ResponseEntity<String> response;
        pacienteService.eliminar(id);

        response = ResponseEntity.status(HttpStatus.OK).body("Eliminado");
        return response;
    }

    //BUSCAR POR ID
    @GetMapping("/{id}") //envio parametro con un GET
    public Paciente buscarPorId(@PathVariable Integer id){
        return pacienteService.buscar(id);
    }

    //BUSCAR TODOS
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    //ACTUALIZAR CON RESPONSE ENTITY
    @PutMapping
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente){
        ResponseEntity<Paciente> response;
        //verificar si el Id es distinto de null y si el paciente existe
        if (paciente.getId() != null && pacienteService.buscar(paciente.getId()) != null) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            response=ResponseEntity.ok(pacienteService.actualizar(paciente));
        }
        return response;
    }

/*    @GetMapping(path = "/")
    public ResponseEntity<Paciente> buscarPacientePorId(@RequestParam Integer id){
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @DeleteMapping(path = "/")
    public ResponseEntity<String> eliminarPacientePorId(@RequestParam Integer id){
        ResponseEntity<String> response;
        pacienteService.eliminar(id);

        response= ResponseEntity.status(HttpStatus.OK).body("Eliminado");
        return response;
    }*/
}
