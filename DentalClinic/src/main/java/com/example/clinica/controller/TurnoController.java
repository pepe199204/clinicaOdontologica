package com.example.clinica.controller;
import com.example.clinica.exceptions.BadRequestException;
import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.Turno;
import com.example.clinica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());

    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(turnoService.guardar(turno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable int id) {
        if (turnoService.buscar(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } else {
            return ResponseEntity.ok(turnoService.buscar(id));
        }
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<Turno> respuesta;

    return ResponseEntity.ok(turnoService.actualizar(turno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable int id) throws ResourceNotFoundException, SQLException {
        turnoService.eliminar(id);
        return ResponseEntity.ok("Se eliminó el turno con id="+id);

    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> tratarErrorBadRequest(BadRequestException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }


}

