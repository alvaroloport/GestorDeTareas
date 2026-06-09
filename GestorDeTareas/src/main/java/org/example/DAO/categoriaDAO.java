package src.main.java.org.example.DAO;

import src.main.java.org.example.modelo.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class categoriaDAO implements IOperationsCRUD<Categoria>{
    @Override
    public List<Categoria> getAll() {
        List<Categoria> categorias = new ArrayList<>();

        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String query = "SELECT * FROM usuario";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Long id =  rs.getLong("id");
                String nombre = rs.getString("nombreEstado");
                String descripcion = rs.getString("descripcion");

                Categoria c = new Categoria(id, nombre, descripcion);

                categorias.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categorias;
    }

    @Override
    public Categoria findById(Long id) {
        Categoria c = null;
        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();
            String query = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");

                c = new Categoria(id, nombre, descripcion);
            }
            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public int add(Categoria object) {
        int resultado = 0;
        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String sql  = "INSERT INTO Tareas (nombre, descripcion) VALUES (?, ?)";
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
    public int update(Categoria object) {
        int  resultado = 0;
        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String sql =  "UPDATE Tareas SET nombre = ?, descripcion = ? WHERE id = ?";
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
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String sql  = "DELETE FROM Categoria WHERE id = ?";
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
