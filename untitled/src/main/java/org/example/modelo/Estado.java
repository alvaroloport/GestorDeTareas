package org.example.modelo;

import java.util.List;

public class Estado {
    private int ID;
    private String nombreEstado;
    private String descripcion;
    private List<Tareas> tareas;

    public Estado(int ID, String nombreEstado) {
        this.ID = ID;
        this.nombreEstado = nombreEstado;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
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
}
