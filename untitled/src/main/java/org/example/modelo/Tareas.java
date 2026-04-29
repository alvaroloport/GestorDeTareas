package org.example.modelo;

import java.util.Date;

public class Tareas {
    private int id;
    private String titulo;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaLimite;
    private Usuario usuario;
    private Estado estado;
    private Categoria categoria;
    private String observaciones;

    public Tareas(int id, String titulo, String descripcion, Date fechaCreacion, Date fechaLimite, Usuario usuario, Estado estado, Categoria categoria, String observaciones) {
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
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Date getFechaLimite() {
        return fechaLimite;
    }
    public void setFechaLimite(Date fechaLimite) {
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
        return "Tarea: " + titulo + ", estado: " + estado + ", categoria: " + categoria;
    }

    public void actualizarEstado(Estado estado) {
        this.estado.getTareas().remove(this);
        this.estado = estado;
        this.estado.getTareas().add(this);
    }
    public void asignarCategoria(Categoria categoria) {
        this.categoria.getTareas().remove(this);
        this.categoria = categoria;
        this.categoria.getTareas().add(this);
    }

}
