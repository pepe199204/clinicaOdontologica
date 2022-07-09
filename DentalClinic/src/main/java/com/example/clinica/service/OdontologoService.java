package com.example.clinica.service;

import com.example.clinica.repository.OdontologoRepository;
import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private OdontologoRepository odontologoRepository;

    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardar (Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public Odontologo buscar(Integer id) {
        Odontologo odontologo=null;
        Optional<Odontologo> optionalPaciente=odontologoRepository.findById(id);
        if (optionalPaciente.isPresent()){
            odontologo=optionalPaciente.get();
        }
        return  odontologo;
    }

    public void eliminar(Integer id) throws ResourceNotFoundException {
        if (this.buscar(id) == null){
            throw new ResourceNotFoundException("No existe el odontologo con id: " + id);
        } else {
            odontologoRepository.deleteById(id);
        }

    }

    public List<Odontologo> buscarTodos (){
        return odontologoRepository.findAll();
    }


    public Odontologo actualizar(Odontologo odontologo) {
        return  odontologoRepository.save(odontologo);
    }
}
