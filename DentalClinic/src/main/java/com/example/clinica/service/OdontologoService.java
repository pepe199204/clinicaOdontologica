package com.example.clinica.service;

import com.example.clinica.dao.impl.IDao;
import com.example.clinica.model.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoDao;

    public OdontologoService(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public Odontologo guardar (Odontologo odontologo){
        return odontologoDao.guardar(odontologo);
    }

    public Odontologo buscar(Integer id) { return odontologoDao.buscar(id); }

    public void eliminar(Integer id) { odontologoDao.eliminar(id); }

    public List<Odontologo> buscarTodos (){ return odontologoDao.buscarTodos(); }

    public Odontologo actualizar(Odontologo odontologo) { return odontologoDao.actualizar(odontologo); }


}
