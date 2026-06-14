package org.example.modelo;





import java.time.LocalDate;

public class tareas {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalDate fechaLimite;
    private Long usuario;
    private Long estado;
    private Long categoria;
    private String observaciones;

    public tareas(Long id, String titulo, String descripcion, LocalDate fechaCreacion, LocalDate fechaLimite, Long usuario,
                  Long estado, Long categoria, String observaciones) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
        this.usuario = usuario;
        this.estado = estado;
        this.categoria = categoria;
        this.observaciones = observaciones;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public Long getUsuario() {
        return usuario;
    }
    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }
    public Long getEstado() {
        return estado;
    }
    public void setEstado(Long estado) {
        this.estado = estado;
    }
    public Long getCategoria() {
        return categoria;
    }
    public void setCategoria(Long categoria) {
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
        return id + ". " + titulo + ". " + descripcion + ". Fecha límite: " + fechaLimite + ".";
    }


}
