package src.main.java.org.example.modelo;

import src.main.java.org.example.modelo.Usuario;
import src.main.java.org.example.modelo.Estado;
import src.main.java.org.example.modelo.Categoria;



import java.time.LocalDate;
import java.util.Date;

public class Tareas {
    private int id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalDate fechaLimite;
    private Usuario usuario;
    private Estado estado;
    private Categoria categoria;
    private String observaciones;

    public Tareas(int id, String titulo, String descripcion, LocalDate fechaCreacion, LocalDate fechaLimite, Usuario usuario,
                  Estado estado, Categoria categoria, String observaciones) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
        this.usuario = usuario;
        this.estado = estado;
        this.categoria = categoria;
        this.observaciones = observaciones;
        usuario.getTareas().add(this);
        estado.getTareas().add(this);
        categoria.getTareas().add(this);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public LocalDate getFechaLimite() {
        return fechaLimite;
    }
    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return id + ". " + titulo + ", estado: " + estado.getNombreEstado() + ", categoria: " + categoria.getNombre();
    }

    public void actualizarEstado(Estado estado) {
        this.estado.getTareas().remove(this);
        this.estado = estado;
        this.estado.getTareas().add(this);
        this.setEstado(estado);
    }
    public void asignarCategoria(Categoria categoria) {
        this.categoria.getTareas().remove(this);
        this.categoria = categoria;
        this.categoria.getTareas().add(this);
        this.setCategoria(categoria);
    }
    public void añadirObservacion(String observacion) {
        this.observaciones += "\n" + observacion;
    }

}
