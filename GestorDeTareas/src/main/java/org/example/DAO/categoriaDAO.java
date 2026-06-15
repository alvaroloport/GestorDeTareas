package org.example.DAO;

import org.example.modelo.categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class categoriaDAO implements IOperationsCRUD<categoria>{
    @Override
    public List<categoria> getAll() {
        List<categoria> categorias = new ArrayList<>();

        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String query = "SELECT * FROM categoria";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Long id =  rs.getLong("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");

                categoria c = new categoria(id, nombre, descripcion);

                categorias.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categorias;
    }

    @Override
    public categoria findById(Long id) {
        categoria c = null;
        try{
            Connection conexion = org.example.utils.conexion.getConnection();
            String query = "SELECT * FROM categoria WHERE id = " + id;
            Statement ps = conexion.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");

                c = new categoria(id, nombre, descripcion);
            }
            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public int add(categoria object) {
        int resultado = 0;
        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String sql  = "INSERT INTO categoria (nombre, descripcion) VALUES (?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, object.getNombre());
            ps.setString(2, object.getDescripcion());

            resultado = ps.executeUpdate();

            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }

    @Override
    public int update(categoria object) {
        int  resultado = 0;
        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String sql =  "UPDATE categoria SET nombre = ?, descripcion = ? WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, object.getNombre());
            ps.setString(2, object.getDescripcion());
            ps.setLong(3, object.getId());

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

            String sql  = "DELETE FROM categoria WHERE id = ?";
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
