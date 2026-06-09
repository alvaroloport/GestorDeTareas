package src.main.java.org.example.modelo;

import java.util.List;
import src.main.java.org.example.modelo.Tareas;

public class Categoria {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<Tareas> tareas;
    public Categoria(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public String toString() {
        return id + ". " + nombre;
    }
}
