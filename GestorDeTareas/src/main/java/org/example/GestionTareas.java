package src.main.java.org.example;


import src.main.java.org.example.DAO.categoriaDAO;
import src.main.java.org.example.DAO.estadoDAO;
import src.main.java.org.example.DAO.tareasDAO;
import src.main.java.org.example.DAO.usuarioDAO;
import src.main.java.org.example.modelo.*;
import src.main.java.org.example.modelo.Usuario;
import src.main.java.org.example.modelo.Tareas;
import src.main.java.org.example.modelo.Categoria;
import src.main.java.org.example.modelo.Estado;
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

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static Estado pendiente = new Estado(0L, "Pendiente");
    private static Estado enProgreso = new  Estado(1L, "En Progreso");
    private static Estado completada = new  Estado(2L, "completada");
    private static Estado cancelada = new  Estado(3L, "cancelada");

    private static usuarioDAO  usuarioDAO = new usuarioDAO();
    private static tareasDAO tareasDAO = new tareasDAO();
    private static estadoDAO estadoDAO = new estadoDAO();
    private static categoriaDAO categoriaDAO = new categoriaDAO();


    static void main() {
        estados.add(pendiente);
        estadoDAO.add(pendiente);
        List<Tareas> tareasPendientes = new ArrayList<>();
        pendiente.setTareas(tareasPendientes);
        estados.add(enProgreso);
        estadoDAO.add(enProgreso);
        List<Tareas> tareasEnProgreso = new ArrayList<>();
        enProgreso.setTareas(tareasEnProgreso);
        estados.add(completada);
        estadoDAO.add(completada);
        List<Tareas> tareasCompletadas = new ArrayList<>();
        completada.setTareas(tareasCompletadas);
        estados.add(cancelada);
        estadoDAO.add(cancelada);
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
        System.out.println("Selecciona un usuario");
        for(Usuario u : usuarioDAO.getAll()){
            u.toString();
        }
        Long usuario = sc.nextLong();
        List<Tareas> tareasUsuario = tareasDAO.getTareasUsuario(usuario);
        System.out.println("Selecciona un estado");
        for(Estado e : estadoDAO.getAll()){
            e.toString();
        }
        Long estado = sc.nextLong();
        for(Tareas t : tareasDAO.getTareasEstado(estado)){
            if (tareasUsuario.contains(t)){
                t.toString();
            }
        }
    }

    private static void tareasCategoria() {
        System.out.println("Selecciona un usuario");
        for(Usuario u : usuarioDAO.getAll()){
            u.toString();
        }
        Long usuario = sc.nextLong();
        List<Tareas> tareasUsuario = tareasDAO.getTareasUsuario(usuario);
        System.out.println("Selecciona una categoria");
        for(Categoria c : categoriaDAO.getAll()){
            c.toString();
        }
        Long categoria = sc.nextLong();
        for(Tareas t : tareasDAO.getTareasCategoria(categoria)){
            if (tareasUsuario.contains(t)){
                t.toString();
            }
        }
    }

    private static void tareasUsuario() {
        System.out.println("Selecciona un usuario");
        for(Usuario u : usuarioDAO.getAll()){
            u.toString();
        }
        Long usuario = sc.nextLong();

        for(Tareas t : tareasDAO.getTareasUsuario(usuario)){
            t.toString();
        }
    }

    private static void actualizarCategoria() {
        System.out.println("Selecciona un usuario");
        for(Usuario u : usuarioDAO.getAll()){
            u.toString();
        }
        Long usuario = sc.nextLong();
        System.out.println("Selecciona una tarea");
        for(Tareas t : tareasDAO.getTareasUsuario(usuario)) {
            t.toString();
        }
        Long tarea = sc.nextLong();
        System.out.println("Selecciona una categoria");
        for(Categoria c : categoriaDAO.getAll()){
            c.toString();
        }
        Long categoria = sc.nextLong();

        Tareas t = tareasDAO.findById(tarea);
        t.setCategoria(categoriaDAO.findById(categoria));

        if(tareasDAO.update(t) == 1){
            System.out.println("Estado actualizado");
        }
        else{
            System.out.println("Error actualizando estado");
        }
    }

    private static void actualizarEstado() {
        System.out.println("Selecciona un usuario");
        for(Usuario u : usuarioDAO.getAll()){
            u.toString();
        }
        Long usuario = sc.nextLong();

        System.out.println("Selecciona una tarea");
        for(Tareas t : tareasDAO.getTareasUsuario(usuario)) {
            t.toString();
        }
        Long tarea = sc.nextLong();
        System.out.println("En qué estado se encuentra la tarea?");
        for(Estado e : estadoDAO.getAll()){
            e.toString();
        }
        Long estado = sc.nextLong();

        Tareas t = tareasDAO.findById(tarea);
        t.setEstado(estadoDAO.findById(estado));

        if(tareasDAO.update(t) == 1){
            System.out.println("Estado actualizado");
        }
        else{
            System.out.println("Error actualizando estado");
        }


    }

    private static void crearTarea() {
        if(usuarioDAO.getAll().size() == 0) {
            System.out.println("Añade un usuario primero");
            return;
        }
        if(categoriaDAO.getAll().size() == 0) {
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
        for(Usuario u : usuarioDAO.getAll()){
            u.toString();
        }
        Long usuario = sc.nextLong();
        System.out.println("En qué categoria está?");
        for(Categoria c : categoriaDAO.getAll() ) {
            c.toString();
        }
        Long categoria = sc.nextLong();
        sc.nextLine();
        System.out.println("Añade observaciones");
        String observaciones = sc.nextLine();
        Tareas t = new Tareas(null,nombre, descripcion, LocalDate.now(), LocalDate.parse(fecha, formatter),
                usuarioDAO.findById(usuario), pendiente , categoriaDAO.findById(categoria), observaciones);
        tareas.add(t);

        if(tareasDAO.add(t) == 1){
            System.out.println("Tarea añadida");
        }
        else{
            System.out.println("Error añadiendo tarea");
        }
    }

    private static void crearCategoria() {
        System.out.println("Escribe el nombre de la categoria");
        String nombreCategoria = sc.next();
        sc.nextLine();
        System.out.println("Añade una pequeña descripción");
        String descripcionCategoria = sc.nextLine();
        Categoria c = new Categoria(null, nombreCategoria, descripcionCategoria);
        List<Tareas> listaTareas = new ArrayList<>();
        c.setTareas(listaTareas);
        categorias.add(c);
        if(categoriaDAO.add(c) == 1){
            System.out.println("Categoria añadida");
        }
        else{
            System.out.println("Error añadiendo la categoria");
        }


    }

    private static void crearUsuario() {
        System.out.println("Introduce el nombre del usuario");
        String nombre = sc.next();
        System.out.println("Introduce el email del usuario");
        String email = sc.next();
        System.out.println("Introduce la contraseña del usuario");
        String password = sc.next();
        Usuario u = new Usuario(null, nombre, email, password);
        List<Tareas> listaTareas = new ArrayList<>();
        u.setTareas(listaTareas);
        usuarios.add(u);
        if (usuarioDAO.add(u) == 1){
            System.out.println("Usuario añadido");
        }
        else{
            System.out.println("Error añadiendo usuario");
        }

    }
}
