package org.example.DAO;

import org.example.modelo.estado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class estadoDAO implements IOperationsCRUD<estado> {

    @Override
    public List<estado> getAll() {
        List<estado> estados = new ArrayList<>();

        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String query = "SELECT * FROM estado";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Long id =  rs.getLong("id");
                String nombre = rs.getString("nombreEstado");

                estado estado = new estado(id, nombre);

                estados.add(estado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estados;
    }

    @Override
    public estado findById(Long id) {
        estado estado = null;
        try{
            Connection conexion = org.example.utils.conexion.getConnection();
            String query = "SELECT * FROM estado WHERE id = " + id;
            Statement ps = conexion.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
                String nombreEstado = rs.getString("nombreEstado");

                estado = new estado(id, nombreEstado);
            }
            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return estado;
    }

    @Override
    public int add(estado object) {
        int resultado = 0;
        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String sql  = "INSERT INTO estado (nombreEstado) VALUES (?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, object.getNombreEstado());

            resultado = ps.executeUpdate();

            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }

    @Override
    public int update(estado object) {
        int  resultado = 0;
        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String sql =  "UPDATE estado SET nombreEstado = ? WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, object.getNombreEstado());
            ps.setLong(2, object.getID());

            resultado = ps.executeUpdate();

            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }

    @Override
    public int deleteById(Long id) {
        int resultado = 0;
        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String sql  = "DELETE FROM estado WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setLong(1, id);
            resultado = ps.executeUpdate();

            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }
}
