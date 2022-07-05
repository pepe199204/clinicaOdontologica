package com.example.clinica;

import com.example.clinica.dao.impl.OdontologoDaoH2;
import com.example.clinica.dao.impl.PacienteDaoH2;
import com.example.clinica.model.Domicilio;
import com.example.clinica.model.Odontologo;
import com.example.clinica.model.Paciente;
import com.example.clinica.service.OdontologoService;
import com.example.clinica.service.PacienteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ClinicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaApplication.class, args);
		PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

		Domicilio domicilio = new Domicilio("Avenida Siempreviva", "742", "Springfield", "Massachusetts");
		Paciente paciente= new Paciente("Homero J.", "Simpson", "4043243", new Date(), domicilio);
		Paciente p = pacienteService.guardar(paciente);

		OdontologoService odontologoService= new OdontologoService(new OdontologoDaoH2());

		Odontologo odontologo1 = new Odontologo(1,1555,"Nick","Rivera");
		odontologoService.guardar(odontologo1);

		Odontologo odontologo2 = new Odontologo(2,2155,"Julius","Hibbert");
		odontologoService.guardar(odontologo2);

		Odontologo odontologo3 = new Odontologo(3,1624,"Eleanor","Abernathy");
		odontologoService.guardar(odontologo3);

		Odontologo odontologo4 = new Odontologo(4,4523,"Stacey","Swanson");
		odontologoService.guardar(odontologo4);

//        Odontologo obtenerOdontologo1 = odontologoService.buscar(1);
//        Odontologo obtenerOdontologo2 = odontologoService.buscar(2);
//        Odontologo obtenerOdontologo3 = odontologoService.buscar(3);
//        Odontologo obtenerOdontologo4 = odontologoService.buscar(4);

		List<Odontologo> odontologos = odontologoService.buscarTodos();
	}

}
