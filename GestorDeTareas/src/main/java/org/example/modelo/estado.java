package org.example.modelo;

import java.util.List;

public class estado {
    private Long id;
    private String nombreEstado;
    private String descripcion;
    private List<tareas> tareas;

    public estado(Long id, String nombreEstado) {
        this.id = id;
        this.nombreEstado = nombreEstado;
    }

    public Long getID() {
        return id;
    }
    public void setID(Long ID) {
        this.id = id;
    }
    public String getNombreEstado() {
        return nombreEstado;
    }
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public List<tareas> getTareas() {
        return tareas;
    }
    public void setTareas(List<tareas> tareas) {
        this.tareas = tareas;
    }
    public void mostrarTareas() {
        for (tareas tarea : tareas) {
            System.out.println(tarea.getTitulo());
        }
    }

    @Override
    public String toString() {
        return id + ". " + nombreEstado;
    }
}
