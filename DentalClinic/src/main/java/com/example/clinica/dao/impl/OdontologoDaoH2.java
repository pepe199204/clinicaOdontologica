package com.example.clinica.dao.impl;

import com.example.clinica.dao.configuracion.ConfiguracionJDBC;
import com.example.clinica.model.Odontologo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final Logger logger = LogManager.getLogger(OdontologoDaoH2.class);

    ConfiguracionJDBC database = new ConfiguracionJDBC();
    Connection connection = null;
    PreparedStatement ps = null;

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Guardando el odontologo: " + odontologo);

        connection = database.conectarConBaseDeDatos();
        ps = null;

        try {

            logger.info("Conexión establecida correctamente.");

            String SQL_INSERT= "insert into odontologos (numero_matricula, nombre, apellido) VALUES (?,?,?);";

            ps= connection.prepareStatement(SQL_INSERT);

            ps.setInt(1,odontologo.getNumeroMatricula());
            ps.setString(2,odontologo.getNombre());
            ps.setString(3,odontologo.getApellido());

            ps.execute();
            ps.close();
            logger.info("Registro insertado correctamente");

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error de base de datos: ",e);
        }

        return odontologo;
    }



    @Override
    public Odontologo buscar(Integer id) {
        logger.info("Buscando el odontologo con el ID: " + id);

        connection = database.conectarConBaseDeDatos();
        PreparedStatement ps = null;
        Odontologo odontologo = null;

        try {
            logger.info("Conexión establecida correctamente.");

            String SQL_SELECT= "SELECT * FROM odontologos WHERE id = ?";
            ps= connection.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);

            ResultSet result = ps.executeQuery();

            while(result.next()){
                int idOdontologo= result.getInt("id");
                int numeroMatricula = result.getInt("numero_matricula");
                String nombre= result.getString("nombre");
                String apellido= result.getString("apellido");

                odontologo= new Odontologo(idOdontologo,numeroMatricula,nombre,apellido);
                logger.info("Informacion del odontologo: "+odontologo);
            }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return odontologo;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Eliminando el odontólogo con id: " + id);
        connection = database.conectarConBaseDeDatos();
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = connection.prepareStatement("DELETE FROM odontologos where id = ?");
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("Buscando registros");

        connection = database.conectarConBaseDeDatos();
        Statement st = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            String SQL_SELECT= "SELECT * FROM odontologos";
            st= connection.createStatement();

            ResultSet result = st.executeQuery(SQL_SELECT);

            logger.info("Query ejecutada");


            while(result.next()) {
                int idOdontologo= result.getInt("id");
                int numeroMatricula = result.getInt("numero_matricula");
                String nombre= result.getString("nombre");
                String apellido= result.getString("apellido");

                Odontologo odontologo= new Odontologo(idOdontologo,numeroMatricula,nombre,apellido);
                logger.info("Datos del odontologo: "+odontologo);
                odontologos.add(odontologo);
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return odontologos;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        logger.info("Actualizando el odontólogo: " + odontologo);

        connection = database.conectarConBaseDeDatos();
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement("UPDATE odontologos SET numero_matricula=?, nombre=?, apellido=? WHERE id = ?");

            preparedStatement.setInt(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.setInt(4, odontologo.getId());
            preparedStatement.executeUpdate();


            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return odontologo;
    }
}
