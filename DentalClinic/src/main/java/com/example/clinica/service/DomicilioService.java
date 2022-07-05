package com.example.clinica.service;

import com.example.clinica.dao.impl.IDao;
import com.example.clinica.model.Domicilio;

import java.util.List;

public class DomicilioService {
    private IDao<Domicilio> domicilioDao;

    public DomicilioService(IDao<Domicilio> domicilioDao) {
        this.domicilioDao = domicilioDao;
    }

    public Domicilio guardar(Domicilio d){ domicilioDao.guardar(d); return d; }
    public Domicilio buscar(Integer id){
        return domicilioDao.buscar(id);
    }
    public List<Domicilio> buscarTodos(){
        return domicilioDao.buscarTodos();
    }
    public void eliminar(Integer id){
        domicilioDao.eliminar(id);
    }
    public Domicilio actualizar(Domicilio d) { return domicilioDao.actualizar(d); }

}
