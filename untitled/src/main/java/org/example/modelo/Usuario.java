package org.example.modelo;

import java.util.List;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String password;
    private List<Tareas> tareas;

    public Usuario(int id, String nombre, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Tareas> getTareas() {
        return tareas;
    }
    public void setTareas(List<Tareas> tareas) {
        this.tareas = tareas;
    }

    public void mostrarTareas(){
        for (Tareas tarea : tareas) {
            System.out.println(tarea.toString());
        }
    }


}
