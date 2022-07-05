package com.example.clinica.dao.impl;

import com.example.clinica.dao.configuracion.ConfiguracionJDBC;
import com.example.clinica.model.Domicilio;
import com.example.clinica.model.Paciente;
import com.example.clinica.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoH2 implements IDao<Paciente> {

    ConfiguracionJDBC database = new ConfiguracionJDBC();
    Connection connection = null;
    PreparedStatement ps = null;
    private DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();

    @Override
    public Paciente guardar(Paciente paciente) {

        connection = database.conectarConBaseDeDatos();
        PreparedStatement preparedStatement = null;

        try {

            //Como primer paso primero debemos guardar el domicilio del paciente
            //ya que necesitamos el ID del domicilio que se generará en la base de datos para luego
            //insertar ese id en el campo domicilio_id de la tabla pacientes
            Domicilio domicilio = domicilioDaoH2.guardar(paciente.getDomicilio());
            Integer id_domicilio= domicilio.getId();
            //paciente.getDomicilio().setId(domicilio.getId());

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO pacientes(nombre,apellido,dni,fecha_ingreso,domicilio_id) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,paciente.getId());
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getDni());
            //Hay que convertir el Date en sql.Date ya que son dos clases diferentes en Java
            preparedStatement.setDate(4, Util.utilDateToSqlDate(paciente.getFechaIngreso()));
            //Tenemos que pasarle la clave foranea del ID del domicilio es decir el campo domicilio_id
            //preparedStatement.setInt(5, paciente.getDomicilio().getId());
            preparedStatement.setInt(5, id_domicilio);

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                paciente.setId(keys.getInt(1));

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return paciente;
    }

    @Override
    public void eliminar(Integer id) {
        connection = database.conectarConBaseDeDatos();
        PreparedStatement preparedStatement = null;
        try {
            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM pacientes where id = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public Paciente buscar(Integer id) {
        connection = database.conectarConBaseDeDatos();
        PreparedStatement preparedStatement = null;
        Paciente paciente = null;
        try {
            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT id,nombre,apellido,dni,fecha_ingreso,domicilio_id  FROM pacientes where id = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                int idPaciente = result.getInt("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                String dni = result.getString("dni");
                Date fechaIngreso = result.getDate("fecha_ingreso");
                int idDomicilio = result.getInt("domicilio_id");
                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios
                Domicilio domicilio = domicilioDaoH2.buscar(idDomicilio);
                paciente = new Paciente(idPaciente,nombre,apellido,dni,fechaIngreso,domicilio);
            }

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() {
        connection = database.conectarConBaseDeDatos();
        PreparedStatement preparedStatement = null;
        List<Paciente> pacientes = new ArrayList<>();
        try {
            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM pacientes");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            //4 Obtener resultados
            while (result.next()) {
                int idPaciente = result.getInt("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                String dni = result.getString("dni");
                Date fechaIngreso = result.getDate("fecha_ingreso");
                int idDomicilio = result.getInt("domicilio_id");
                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios
                Domicilio domicilio = domicilioDaoH2.buscar(idDomicilio);
                Paciente paciente = new Paciente(idPaciente,nombre,apellido,dni,fechaIngreso,domicilio);
                pacientes.add(paciente);
            }

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return pacientes;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {

        connection = database.conectarConBaseDeDatos();
        PreparedStatement preparedStatement = null;

        try {
            //Como primer paso actualizamos el domicilio del paciente
            Domicilio domicilio = domicilioDaoH2.actualizar(paciente.getDomicilio());

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("UPDATE pacientes SET nombre=?, apellido=?, dni=?, fecha_ingreso=?, domicilio_id=?  WHERE id = ?");
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,paciente.getId());
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getDni());
            //Hay que convertir el Date en sql.Date ya que son dos clases diferentes en Java
            preparedStatement.setDate(4, Util.utilDateToSqlDate(paciente.getFechaIngreso()));
            //Tenemos que pasarle la clave foranea del ID del domicilio es decir el campo domicilio_id
            preparedStatement.setInt(5, paciente.getDomicilio().getId());
            preparedStatement.setInt(6, paciente.getId());

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();


            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return paciente;
    }

}
