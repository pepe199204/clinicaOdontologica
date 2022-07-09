package com.example.clinica.service;

import com.example.clinica.repository.DomicilioRepository;
import com.example.clinica.model.Domicilio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService {
    private DomicilioRepository domicilioRepository;

    public DomicilioService(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    public Domicilio guardar(Domicilio d){
        return domicilioRepository.save(d);
    }

    public Domicilio buscar(Integer id){
        return domicilioRepository.findById(id).get();
    }

    public List<Domicilio> buscarTodos(){
        return domicilioRepository.findAll();
    }

    public void eliminar(Integer id){
        domicilioRepository.deleteById(id);
    }
    public Domicilio actualizar(Domicilio d) {
        return domicilioRepository.save(d);
    }

}
