package com.example.clinica.controller;

import com.example.clinica.dao.impl.TurnoRepositoy;
import com.example.clinica.model.Turno;
import com.example.clinica.service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService = new TurnoService(new TurnoRepositoy());

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(Turno turno){
        return ResponseEntity.ok(turnoService.guardar(turno));
    }
}
