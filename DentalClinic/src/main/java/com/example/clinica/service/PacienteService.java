package com.example.clinica.service;

import com.example.clinica.repository.PacienteRepository;
import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardar(Paciente p) {
        p.setFechaIngreso(new Date());
        return pacienteRepository.save(p);
    }

    public Paciente buscar(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        return paciente.orElse(null);
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    public void eliminar(Integer id) throws ResourceNotFoundException{
        if(buscar(id) == null)
            throw new ResourceNotFoundException("No existe un paciente con el id: " + id);
        pacienteRepository.deleteById(id);
    }

    public Paciente actualizar(Paciente p) {
        return pacienteRepository.save(p);
    }
}
