package org.example;


import org.example.modelo.*;

import javax.xml.transform.Source;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;

public class GestionTareas {

    private static final Scanner sc = new Scanner(System.in);
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Categoria> categorias = new ArrayList<>();
    private static List<Tareas> tareas = new ArrayList<>();
    private static List<Estado> estados = new ArrayList<>();
    private static int idUsuario = 0;
    private static int idCategoria = 0;
    private static int idTarea = 0;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static Estado pendiente = new Estado(0, "Pendiente");
    private static Estado enProgreso = new  Estado(1, "En Progreso");
    private static Estado completada = new  Estado(2, "completada");
    private static Estado cancelada = new  Estado(3, "cancelada");


    static void main() {
        estados.add(pendiente);
        List<Tareas> tareasPendientes = new ArrayList<>();
        pendiente.setTareas(tareasPendientes);
        estados.add(enProgreso);
        List<Tareas> tareasEnProgreso = new ArrayList<>();
        enProgreso.setTareas(tareasEnProgreso);
        estados.add(completada);
        List<Tareas> tareasCompletadas = new ArrayList<>();
        completada.setTareas(tareasCompletadas);
        estados.add(cancelada);
        List<Tareas> tareasCanceladas = new ArrayList<>();
        cancelada.setTareas(tareasCanceladas);
        int opcion;
        do {
            System.out.println("=================================");
            System.out.println("GESTOR DE TAREAS");
            System.out.println("=================================");
            System.out.println("¿Qué desea hacer?");
            System.out.println("1. Crear usuario");
            System.out.println("2. Crear Categoria");
            System.out.println("3. Crear Tarea");
            System.out.println("4. Actualizar estado de una tarea");
            System.out.println("5. Cambiar categoria de una tarea");
            System.out.println("6. Mostrar todas las tareas por usuario");
            System.out.println("7. Mostrar todas las tareas por categoria");
            System.out.println("8. Mostrar todas las tareas por estado");
            System.out.println("0. Salir");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> crearUsuario();
                case 2 -> crearCategoria();
                case 3 -> crearTarea();
                case 4 -> actualizarEstado();
                case 5 -> actualizarCategoria();
                case 6 -> tareasUsuario();
                case 7 -> tareasCategoria();
                case 8 -> tareasEstado();
                case 0 -> System.out.println("Saliendo");
                default -> System.out.println("Opcion no válida");
            }


        }while (opcion!=0);

    }

    private static void tareasEstado() {
        System.out.println("Selecciona un estado");
        for(Estado e : estados) {
            System.out.println(e.getID() + ". " + e.getNombreEstado());
        }
        int estado = sc.nextInt();
        estados.get(estado).mostrarTareas();
    }

    private static void tareasCategoria() {
        System.out.println("Selecciona una categoria");
        for(Categoria c : categorias) {
            System.out.println(c.getId() + ". " +  c.getNombre());
        }
        int categoria = sc.nextInt();
        categorias.get(categoria).mostrarTareas();
    }

    private static void tareasUsuario() {
        System.out.println("Selecciona un usuario");
        for(Usuario u : usuarios) {
            System.out.println(u.getId() + ". " + u.getNombre());
        }
        int usuario = sc.nextInt();
        usuarios.get(usuario).mostrarTareas();
    }

    private static void actualizarCategoria() {
        System.out.println("Selecciona un usuario");
        for(Usuario u : usuarios) {
            System.out.println(u.getId() + ". " + u.getNombre());
        }
        int usuario = sc.nextInt();
        System.out.println("Selecciona una tarea");
        usuarios.get(usuario).mostrarTareas();
        int tarea = sc.nextInt();
        System.out.println("Selecciona una categoria");
        for(Categoria c : categorias) {
            System.out.println(c.getId() + ". " +  c.getNombre());
        }
        int categoria = sc.nextInt();
        tareas.get(tarea).asignarCategoria(categorias.get(categoria));

        System.out.println("Categoria actualizada");
    }

    private static void actualizarEstado() {
        System.out.println("Selecciona un usuario");
        for(Usuario u : usuarios) {
            System.out.println(u.getId() + ". " + u.getNombre());
        }
        int usuario = sc.nextInt();
        System.out.println("Selecciona una tarea");
        usuarios.get(usuario).mostrarTareas();
        int tarea = sc.nextInt();
        System.out.println("En qué estado se encuentra la tarea?");
        for(Estado estado : estados) {
            System.out.println(estado.getID() + ". " + estado.getNombreEstado());
        }
        int estado = sc.nextInt();
        tareas.get(tarea).actualizarEstado(estados.get(estado));

        System.out.println("Estado actualizado");
    }

    private static void crearTarea() {
        if(usuarios.size() == 0) {
            System.out.println("Añade un usuario primero");
            return;
        }
        if(categorias.size() == 0) {
            System.out.println("Añade una categoria primero");
            return;
        }
        System.out.println("Escribe el nombre de la tarea");
        String nombre = sc.next();
        sc.nextLine();
        System.out.println("Añade una descripcion de la tarea");
        String descripcion = sc.nextLine();
        System.out.println("Añade una fecha límite");
        String fecha = sc.next();
        while(LocalDate.parse(fecha, formatter).compareTo(LocalDate.now()) < 0){
            System.out.println("Escribe una fecha que no haya pasado aún");
            System.out.println("Añade una fecha límite");
            fecha = sc.next();
        }
        System.out.println("Para qué usuario es la tarea?");
        for(Usuario u : usuarios ) {
            System.out.println(u.getId() + ". " + u.getNombre());
        }
        int usuario = sc.nextInt();
        System.out.println("En qué categoria está?");
        for(Categoria c : categorias ) {
            System.out.println(c.getId() + ". " +  c.getNombre());
        }
        int categoria = sc.nextInt();
        sc.nextLine();
        System.out.println("Añade observaciones");
        String observaciones = sc.nextLine();
        Tareas t = new Tareas(idTarea,nombre, descripcion, LocalDate.now(), LocalDate.parse(fecha, formatter),
                usuarios.get(usuario), pendiente , categorias.get(categoria), observaciones);
        tareas.add(t);
        idTarea++;

        System.out.println("Tarea añadida");
    }

    private static void crearCategoria() {
        System.out.println("Escribe el nombre de la categoria");
        String nombreCategoria = sc.next();
        sc.nextLine();
        System.out.println("Añade una pequeña descripción");
        String descripcionCategoria = sc.nextLine();
        Categoria c = new Categoria(idCategoria, nombreCategoria, descripcionCategoria);
        List<Tareas> listaTareas = new ArrayList<>();
        c.setTareas(listaTareas);
        categorias.add(c);
        idCategoria++;

        System.out.println("Categoria añadida");
    }

    private static void crearUsuario() {
        System.out.println("Introduce el nombre del usuario");
        String nombre = sc.next();
        System.out.println("Introduce el email del usuario");
        String email = sc.next();
        System.out.println("Introduce la contraseña del usuario");
        String password = sc.next();
        Usuario u = new Usuario(idUsuario, nombre, email, password);
        List<Tareas> listaTareas = new ArrayList<>();
        u.setTareas(listaTareas);
        usuarios.add(u);
        idUsuario++;

        System.out.println("Usuario añadido");
    }
}
