package org.example.modelo;

import java.util.List;

public class usuario {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private List<tareas> tareas;

    public usuario(Long id, String nombre, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
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
    public List<tareas> getTareas() {
        return tareas;
    }
    public void setTareas(List<tareas> tareas) {
        this.tareas = tareas;
    }

    public void mostrarTareas(){
        for (tareas tarea : tareas) {
            System.out.println(tarea.toString());
        }
    }

    @Override
    public String toString() {
        return getId() + ". " + getNombre() + ". Email: " + getEmail() + ". Password: " + getPassword();
    }
}
