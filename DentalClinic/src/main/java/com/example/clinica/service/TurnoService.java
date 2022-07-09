package com.example.clinica.service;

import com.example.clinica.exceptions.BadRequestException;
import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.Turno;
import com.example.clinica.repository.TurnoRepository;
import com.example.clinica.service.OdontologoService;
import com.example.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

//    private IDao<Turno> turnoDao;
private TurnoRepository turnoRepository;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;


    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }


    public Turno guardar(Turno turno) throws BadRequestException, ResourceNotFoundException {
        if (pacienteService.buscar(turno.getPaciente().getId()) == null || odontologoService.buscar(turno.getOdontologo().getId()) == null){
            throw new BadRequestException("el odontólogo o paciente no es válido");

        }else{
            return turnoRepository.save(turno);
        }

    }


    public Turno buscar(Integer id) {
        Turno turno=null;
        Optional<Turno> optionalTurno=turnoRepository.findById(id);
        if (optionalTurno.isPresent()){
            turno=optionalTurno.get();
        }
        return  turno;
    }

    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    public void eliminar(Integer id) throws ResourceNotFoundException {
        if(this.buscar(id)==null)
            throw new ResourceNotFoundException("No se eliminó el turno con id="+id+" ya que no existe en la base de datos");
        turnoRepository.deleteById(id);
    }

    public Turno actualizar(Turno turno) throws BadRequestException, ResourceNotFoundException {
        return this.guardar(turno);
//        return turnoRepository.save(turno);
    }
}
