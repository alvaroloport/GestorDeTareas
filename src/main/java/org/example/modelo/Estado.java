package org.example.modelo;

import java.util.List;

public class Estado {
    private int id;
    private String nombreEstado;
    private String descripcion;
    private List<Tareas> tareas;

    public Estado(int id, String nombreEstado) {
        this.id = id;
        this.nombreEstado = nombreEstado;
    }

    public int getID() {
        return id;
    }
    public void setID(int ID) {
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
    public List<Tareas> getTareas() {
        return tareas;
    }
    public void setTareas(List<Tareas> tareas) {
        this.tareas = tareas;
    }
    public void mostrarTareas() {
        for (Tareas tarea : tareas) {
            System.out.println(tarea.getTitulo());
        }
    }

    @Override
    public String toString() {
        return id + ". " + nombreEstado;
    }
}
