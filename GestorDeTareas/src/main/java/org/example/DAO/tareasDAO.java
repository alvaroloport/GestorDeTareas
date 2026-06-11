package src.main.java.org.example.DAO;

import src.main.java.org.example.modelo.Tareas;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class tareasDAO implements IOperationsCRUD<Tareas> {

    private static final usuarioDAO usuarioDAO = new usuarioDAO();
    private static final categoriaDAO categoriaDAO = new categoriaDAO();
    private static final estadoDAO estadoDAO = new estadoDAO();


    @Override
    public List<Tareas> getAll() {
        List<Tareas> tareas = new ArrayList<>();

        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String query = "SELECT * FROM usuario";
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

                Tareas t = new Tareas(id, titulo, descripcion, fechaCreacion, fechaLimite, usuarioDAO.findById(usuario_id),
                        estadoDAO.findById(estado_id), categoriaDAO.findById(categoria_id), observaciones);

                tareas.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tareas;
    }

    public List<Tareas> getTareasUsuario(Long idUsuario) {
        List<Tareas> tareas = new ArrayList<>();

        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String query = "SELECT * FROM usuario WHERE  idUsuario = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(1, idUsuario);
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

                Tareas t = new Tareas(id, titulo, descripcion, fechaCreacion, fechaLimite, usuarioDAO.findById(usuario_id),
                        estadoDAO.findById(estado_id), categoriaDAO.findById(categoria_id), observaciones);

                tareas.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tareas;
    }

    public List<Tareas> getTareasCategoria(Long idCategoria) {
        List<Tareas> tareas = new ArrayList<>();

        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String query = "SELECT * FROM usuario WHERE  idCategoria = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(1, idCategoria);
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

                Tareas t = new Tareas(id, titulo, descripcion, fechaCreacion, fechaLimite, usuarioDAO.findById(usuario_id),
                        estadoDAO.findById(estado_id), categoriaDAO.findById(categoria_id), observaciones);

                tareas.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tareas;
    }

    public List<Tareas> getTareasEstado(Long idEstado) {
        List<Tareas> tareas = new ArrayList<>();

        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String query = "SELECT * FROM usuario WHERE  idEstado = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(1, idEstado);
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

                Tareas t = new Tareas(id, titulo, descripcion, fechaCreacion, fechaLimite, usuarioDAO.findById(usuario_id),
                        estadoDAO.findById(estado_id), categoriaDAO.findById(categoria_id), observaciones);

                tareas.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tareas;
    }

    @Override
    public Tareas findById(Long id) {
        Tareas t = null;
        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();
            String query = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                LocalDate fechaCreacion = rs.getDate("fechaCreacion").toLocalDate();
                LocalDate fechaLimite = rs.getDate("fechaLimite").toLocalDate();
                Long usuario_id = rs.getLong("idUsuario");
                Long estado_id = rs.getLong("idEstado");
                Long categoria_id = rs.getLong("idCategoria");
                String observaciones = rs.getString("observaciones");

                t = new Tareas(id, titulo, descripcion, fechaCreacion, fechaLimite, usuarioDAO.findById(usuario_id),
                        estadoDAO.findById(estado_id), categoriaDAO.findById(categoria_id), observaciones);
            }
            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return t;
    }

    @Override
    public int add(Tareas object) {
        int resultado = 0;
        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String sql  = "INSERT INTO Tareas (titulo, descripcion, fechaCreacion, fechaLimite, usuario, estado, categoria,"
                           + "observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, object.getTitulo());
            ps.setString(2, object.getDescripcion());
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            ps.setDate(4, Date.valueOf(object.getFechaLimite()));
            ps.setLong(5, object.getUsuario().getId());
            ps.setLong(6, object.getEstado().getID());
            ps.setLong(7, object.getCategoria().getId());
            ps.setString(8, object.getObservaciones());

            resultado = ps.executeUpdate();

            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }

    @Override
    public int update(Tareas object) {
        int  resultado = 0;
        try{
            Connection conexion = src.main.java.org.example.utils.conexion.getConnection();

            String sql =  "UPDATE Tareas SET titulo = ?, descripcion = ?, fechaCreacion = ?. fechaLimite = ?,"
                            + "usuario = ?, estado = ?, categoria = ?, observaciones = ? WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, object.getTitulo());
            ps.setString(2, object.getDescripcion());
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            ps.setDate(4, Date.valueOf(object.getFechaLimite()));
            ps.setLong(5, object.getUsuario().getId());
            ps.setLong(6, object.getEstado().getID());
            ps.setLong(7, object.getCategoria().getId());
            ps.setString(8, object.getObservaciones());
            ps.setLong(9, object.getId());

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
