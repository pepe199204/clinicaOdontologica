package com.example.clinica;

import com.example.clinica.exceptions.ResourceNotFoundException;
import com.example.clinica.model.Domicilio;
import com.example.clinica.model.Paciente;
import com.example.clinica.service.DomicilioService;
import com.example.clinica.service.PacienteService;
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
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private DomicilioService domicilioService;

    @Test
    public void agregarYBuscarPacienteTest(){
        Domicilio domicilio = new Domicilio("Avenida Siempreviva", "742", "Springfield", "Massachusetts");
        Paciente paciente= pacienteService.guardar(new Paciente("Homero J.", "Simpson", "4043243", new Date(), domicilio));
        Assert.assertNotNull(pacienteService.buscar(paciente.getId()));
    }


    @Test
    public void eliminarPacienteTest() throws ResourceNotFoundException {
        pacienteService.eliminar(1);
        Assert.assertNull(pacienteService.buscar(1));
    }

    @Test
    public void traerTodos() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
    }
}
