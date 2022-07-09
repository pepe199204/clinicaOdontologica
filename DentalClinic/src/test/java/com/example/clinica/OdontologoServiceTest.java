package com.example.clinica;


import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.Odontologo;
import com.example.clinica.service.OdontologoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

		Odontologo odontologo1 = new Odontologo(1555,"Nick","Rivera");
		Odontologo odontologo2 = new Odontologo(2155,"Julius","Hibbert");
		Odontologo odontologo3 = new Odontologo(1624,"Eleanor","Abernathy");
		Odontologo odontologo4 = new Odontologo(4523,"Stacey","Swanson");


    @Test
    public void registarOdontologos(){
        Assert.assertNotNull(odontologoService.guardar(odontologo1));
        Assert.assertNotNull(odontologoService.guardar(odontologo2));
        Assert.assertNotNull(odontologoService.guardar(odontologo3));
        Assert.assertNotNull(odontologoService.guardar(odontologo4));
    }

    @Test
    public void listarTodosLosOdontologos(){
        Assert.assertEquals(odontologoService.buscarTodos().size(), odontologoService.buscarTodos().size());
    }

    @Test
    public void eliminarOdontologo() throws ResourceNotFoundException {
        odontologoService.eliminar(10);
        assertTrue(odontologoService.buscar(10) == null);

    }

    @Test
    public void buscarOdontologo() {
        assertNotNull(odontologoService.buscar(15));
    }



    @Test
    public void actualizarOdontologo() {

        Odontologo odontologo3Actualizado=new Odontologo(15,1555,"Nick","Rivera Lopez");
        Odontologo odontologoActualizado=odontologoService.actualizar(odontologo3Actualizado);

        assertEquals(odontologoService.buscar(15).getNombre(),"Nick");
        assertEquals(odontologoService.buscar(15).getApellido(),"Rivera Lopez");

    }



}