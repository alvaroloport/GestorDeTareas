package org.example.DAO;

import org.example.modelo.usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class usuarioDAO implements IOperationsCRUD<usuario> {
    @Override
    public List<usuario> getAll() {
        List<usuario> usuarios = new ArrayList<>();

        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String query = "SELECT * FROM usuario";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Long id =  rs.getLong("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String password = rs.getString("password");

                usuario u = new usuario(id, nombre, email, password);

                usuarios.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    @Override
    public usuario findById(Long id) {
        usuario u = null;
        try{
            Connection conexion = org.example.utils.conexion.getConnection();
            String query = "SELECT * FROM usuario WHERE id = " + id;
            Statement ps = conexion.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String password = rs.getString("password");

                u = new usuario(id, nombre, email, password);
            }
            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return u;
    }

    @Override
    public int add(usuario object) {
        int resultado = 0;
        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String sql  = "INSERT INTO usuario (nombre, email, password) VALUES (?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, object.getNombre());
            ps.setString(2, object.getEmail());
            ps.setString(3, object.getPassword());
            resultado = ps.executeUpdate();

            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }

    @Override
    public int update(usuario object) {
        int  resultado = 0;
        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String sql =  "UPDATE usuario SET nombre = ?, email = ?, password = ? WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, object.getNombre());
            ps.setString(2, object.getEmail());
            ps.setString(3, object.getPassword());
            ps.setLong(4, object.getId());
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

            String sql  = "DELETE FROM usuario WHERE id = ?";
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
