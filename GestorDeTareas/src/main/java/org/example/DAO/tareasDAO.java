package org.example.DAO;

import org.example.modelo.tareas;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class tareasDAO implements IOperationsCRUD<tareas> {

    private static final usuarioDAO usuarioDAO = new usuarioDAO();
    private static final categoriaDAO categoriaDAO = new categoriaDAO();
    private static final estadoDAO estadoDAO = new estadoDAO();


    @Override
    public List<tareas> getAll() {
        List<tareas> tareas = new ArrayList<>();

        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String query = "SELECT * FROM Tareas";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Long id =  rs.getLong("id");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                LocalDate fechaCreacion = rs.getDate("fechaCreacion").toLocalDate();
                LocalDate fechaLimite = rs.getDate("fechaLimite").toLocalDate();
                Long usuario_id = rs.getLong("idUsuario");
                Long estado_id = rs.getLong("idEstado");
                Long categoria_id = rs.getLong("idCategoria");
                String observaciones = rs.getString("observaciones");

                tareas t = new tareas(id, titulo, descripcion, fechaCreacion, fechaLimite, usuario_id,
                        estado_id, categoria_id, observaciones);

                tareas.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tareas;
    }

    public List<tareas> getTareasUsuario(Long idUsuario) {
        List<tareas> tareas = new ArrayList<>();

        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String query = "SELECT * FROM Tareas WHERE idUsuario =" +  idUsuario;
            Statement ps = conexion.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
                Long id =  rs.getLong("id");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                LocalDate fechaCreacion = rs.getDate("fechaCreacion").toLocalDate();
                LocalDate fechaLimite = rs.getDate("fechaLimite").toLocalDate();
                Long usuario_id = rs.getLong("idUsuario");
                Long estado_id = rs.getLong("idEstado");
                Long categoria_id = rs.getLong("idCategoria");
                String observaciones = rs.getString("observaciones");

                tareas t = new tareas(id, titulo, descripcion, fechaCreacion, fechaLimite, usuario_id,
                        estado_id, categoria_id, observaciones);

                tareas.add(t);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tareas;
    }

    public List<tareas> getTareasCategoria(Long idCategoria) {
        List<tareas> tareas = new ArrayList<>();

        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String query = "SELECT * FROM Tareas WHERE idCategoria = " + idCategoria;
            Statement ps = conexion.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
                Long id =  rs.getLong("id");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                LocalDate fechaCreacion = rs.getDate("fechaCreacion").toLocalDate();
                LocalDate fechaLimite = rs.getDate("fechaLimite").toLocalDate();
                Long usuario_id = rs.getLong("idUsuario");
                Long estado_id = rs.getLong("idEstado");
                Long categoria_id = rs.getLong("idCategoria");
                String observaciones = rs.getString("observaciones");

                tareas t = new tareas(id, titulo, descripcion, fechaCreacion, fechaLimite, usuario_id,
                        estado_id, categoria_id, observaciones);

                tareas.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tareas;
    }

    public List<tareas> getTareasEstado(Long idEstado) {
        List<tareas> tareas = new ArrayList<>();

        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String query = "SELECT * FROM Tareas WHERE idEstado = " +  idEstado;
            Statement ps = conexion.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
                Long id =  rs.getLong("id");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                LocalDate fechaCreacion = rs.getDate("fechaCreacion").toLocalDate();
                LocalDate fechaLimite = rs.getDate("fechaLimite").toLocalDate();
                Long usuario_id = rs.getLong("idUsuario");
                Long estado_id = rs.getLong("idEstado");
                Long categoria_id = rs.getLong("idCategoria");
                String observaciones = rs.getString("observaciones");

                tareas t = new tareas(id, titulo, descripcion, fechaCreacion, fechaLimite, usuario_id,
                        estado_id, categoria_id, observaciones);

                tareas.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tareas;
    }

    @Override
    public tareas findById(Long id) {
        tareas t = null;
        try{
            Connection conexion = org.example.utils.conexion.getConnection();
            String query = "SELECT * FROM Tareas WHERE id = " + id;
            Statement ps = conexion.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                LocalDate fechaCreacion = rs.getDate("fechaCreacion").toLocalDate();
                LocalDate fechaLimite = rs.getDate("fechaLimite").toLocalDate();
                Long usuario_id = rs.getLong("idUsuario");
                Long estado_id = rs.getLong("idEstado");
                Long categoria_id = rs.getLong("idCategoria");
                String observaciones = rs.getString("observaciones");

                t = new tareas(id, titulo, descripcion, fechaCreacion, fechaLimite, usuario_id,
                        estado_id, categoria_id, observaciones);
            }
            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return t;
    }

    @Override
    public int add(tareas object) {
        int resultado = 0;
        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String sql  = "INSERT INTO Tareas (titulo, descripcion, fechaCreacion, fechaLimite, idUsuario, idEstado, idCategoria,"
                    + "observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, object.getTitulo());
            ps.setString(2, object.getDescripcion());
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            ps.setDate(4, Date.valueOf(object.getFechaLimite()));
            ps.setLong(5, object.getUsuario());
            ps.setLong(6, object.getEstado());
            ps.setLong(7, object.getCategoria());
            ps.setString(8, object.getObservaciones());

            resultado = ps.executeUpdate();

            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }

    @Override
    public int update(tareas object) {
        int  resultado = 0;
        try{
            Connection conexion = org.example.utils.conexion.getConnection();

            String sql =  "UPDATE Tareas SET titulo ='"+ object.getTitulo() + "', descripcion = '"+ object.getDescripcion()
                    + "', fechaCreacion = '"+ new java.sql.Date(System.currentTimeMillis()) + "', fechaLimite = '"+ object.getFechaLimite()
                    + "', idUsuario = '"+ object.getUsuario() + "', idEstado = '"+ object.getEstado() + "', idCategoria = '"+ object.getCategoria()
                    + "', observaciones = '"+ object.getObservaciones() + "' WHERE id = '"+ object.getId() +"'";
            Statement ps = conexion.createStatement();
            resultado = ps.executeUpdate(sql);

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

            String sql  = "DELETE FROM Tareas WHERE id = ?";
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
