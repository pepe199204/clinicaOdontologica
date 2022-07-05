package com.example.clinica.service;

import com.example.clinica.dao.impl.IDao;
import com.example.clinica.model.Paciente;
import com.example.clinica.model.Turno;

import java.util.Date;
import java.util.List;

public class TurnoService {

    private IDao<Turno> turnoRepository;

    public TurnoService(IDao<Turno> turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    //    public Turno registrarTurno(Turno turno){
    //        return  turnoRepository.guardar(turno);
    //    }

    public Turno guardar(Turno turno) {
        turno.setFecha(new Date());
        return turnoRepository.guardar(turno);
    }

    public Turno buscar(Integer id) {
        return turnoRepository.buscar(id);
    }

    public List<Turno> buscarTodos() {
        return turnoRepository.buscarTodos();
    }

    public void eliminar(Integer id) {
        turnoRepository.eliminar(id);
    }

    public Turno actualizar(Turno turno) {
        return turnoRepository.actualizar(turno);
    }


}
