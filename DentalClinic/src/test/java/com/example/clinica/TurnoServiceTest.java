package com.example.clinica;

import com.example.clinica.exceptions.BadRequestException;
import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.Domicilio;
import com.example.clinica.model.Odontologo;
import com.example.clinica.model.Paciente;
import com.example.clinica.model.Turno;
import com.example.clinica.service.OdontologoService;
import com.example.clinica.service.PacienteService;
import com.example.clinica.service.TurnoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTest {

    @Autowired
    TurnoService turnoService;
    @Autowired
    OdontologoService odontologoService;
    @Autowired
    PacienteService pacienteService;


    @Test
    public void agregarTurnoTest() throws BadRequestException, ResourceNotFoundException {

        Domicilio domicilioTurno = new Domicilio("Avenida Siempreviva", "742", "Springfield", "Massachusetts");
        Paciente pacienteTurno = pacienteService.guardar(new Paciente("Marge", "Simpson", "40433", new Date(), domicilioTurno));
        Odontologo odontologoTurno = odontologoService.guardar(new Odontologo(2155,"Julius2","Hibbert"));

        Turno turnoTest = turnoService.guardar(new Turno(1,pacienteTurno,odontologoTurno, new Date()));
        Assert.assertNotNull(turnoService.buscar(turnoTest.getId()));

    }

}
