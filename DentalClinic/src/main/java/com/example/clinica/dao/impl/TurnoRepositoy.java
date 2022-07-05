package com.example.clinica.dao.impl;

import com.example.clinica.model.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoRepositoy implements IDao<Turno>{

    private List<Turno> turnos = new ArrayList<>();

    @Override
    public Turno guardar(Turno turno) {
        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno buscar(Integer id) {
        Turno turnoEncontrado = null;
        for (Turno turno : turnos){
            if(turno.getId().equals(id)){
                turnoEncontrado = turno;
            }
        }
        return turnoEncontrado;
    }

    @Override
    public void eliminar(Integer id) {
        Turno turno = buscar(id);
        turnos.remove(turno);
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnos;
    }

    @Override
    public Turno actualizar(Turno turno) {
        int index = -1;
        for (int i = 0; i < turnos.size(); i++) {
            if (turnos.get(i).getId().equals(turno.getId())) {
                index = i;
            }
        }
        turnos.set(index, turno);
        return turno;
    }
}
