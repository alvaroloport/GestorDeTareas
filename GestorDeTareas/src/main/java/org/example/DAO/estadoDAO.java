package src.main.java.org.example.DAO;

import src.main.java.org.example.modelo.Estado;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class estadoDAO implements IOperationsCRUD<Estado> {

    @Override
    public List<Estado> getAll() {
        List<Estado> estados = new ArrayList<>();

        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String query = "SELECT * FROM usuario";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Long id =  rs.getLong("id");
                String nombre = rs.getString("nombreEstado");

                Estado t = new Estado(id, nombre);

                estados.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estados;
    }

    @Override
    public Estado findById(Long id) {
        Estado estado = null;
        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();
            String query = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String nombreEstado = rs.getString("nombreEstado");

                estado = new Estado(id, nombreEstado);
            }
            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return estado;
    }

    @Override
    public int add(Estado object) {
        int resultado = 0;
        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String sql  = "INSERT INTO Tareas (nombreEstado) VALUES (?)";
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
    public int update(Estado object) {
        int  resultado = 0;
        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String sql =  "UPDATE Tareas SET nombreEstado = ? WHERE id = ?";
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
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String sql  = "DELETE FROM Estado WHERE id = ?";
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
