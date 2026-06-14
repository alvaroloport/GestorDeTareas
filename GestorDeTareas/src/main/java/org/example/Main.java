package org.example;

import org.example.DAO.categoriaDAO;
import org.example.DAO.estadoDAO;
import org.example.DAO.tareasDAO;
import org.example.DAO.usuarioDAO;
import org.example.modelo.usuario;
import org.example.modelo.tareas;
import org.example.modelo.Categoria;
import org.example.modelo.estado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static List<usuario> usuarios = new ArrayList<>();
    private static List<Categoria> categorias = new ArrayList<>();
    private static List<tareas> tareas = new ArrayList<>();
    private static List<estado> estados = new ArrayList<>();

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static estado pendiente = new estado(1L, "Pendiente");
    private static estado enProgreso = new estado(2L, "En Progreso");
    private static estado completada = new estado(3L, "completada");
    private static estado cancelada = new estado(4L, "cancelada");

    private static usuarioDAO usuarioDAO = new usuarioDAO();
    private static tareasDAO tareasDAO = new tareasDAO();
    private static estadoDAO estadoDAO = new estadoDAO();
    private static categoriaDAO categoriaDAO = new categoriaDAO();


    static void main() {
        estados.add(pendiente);
        estadoDAO.add(pendiente);
        List<tareas> tareasPendientes = new ArrayList<>();
        pendiente.setTareas(tareasPendientes);
        estados.add(enProgreso);
        estadoDAO.add(enProgreso);
        List<tareas> tareasEnProgreso = new ArrayList<>();
        enProgreso.setTareas(tareasEnProgreso);
        estados.add(completada);
        estadoDAO.add(completada);
        List<tareas> tareasCompletadas = new ArrayList<>();
        completada.setTareas(tareasCompletadas);
        estados.add(cancelada);
        estadoDAO.add(cancelada);
        List<tareas> tareasCanceladas = new ArrayList<>();
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
            System.out.println("9. Modificar categoria");
            System.out.println("10. Modificar usuario");
            System.out.println("11. Mostrar todos los usuarios");
            System.out.println("12. Mostrar todas las categorias");
            System.out.println("13. Borrar tarea");
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
                case 9 -> modificarCategoria();
                case 10 -> modificarUsuario();
                case 11 -> mostrarUsuarios();
                case 12 -> mostrarCategorias();
                case 13 -> borrarTarea();
                case 0 -> System.out.println("Saliendo");
                default -> System.out.println("Opcion no válida");
            }

        } while (opcion != 0);
    }

    private static void borrarTarea() {
        if (tareasDAO.getAll().size() == 0) {
            System.out.println("Añade una tarea primero");
            return;
        }
        System.out.println("Selecciona una tarea");
        for (tareas t : tareasDAO.getAll()) {
            System.out.println(t.toString() + " Usuario: " + usuarioDAO.findById(t.getUsuario()).getNombre());
        }
        Long tarea = sc.nextLong();

        if (tareasDAO.deleteById(tarea) == 1) {
            System.out.println("Tarea eliminada");
        }
        else {
            System.out.println("Error al eliminar tarea");
        }
    }

    private static void mostrarCategorias() {
        for (Categoria c : categoriaDAO.getAll()) {
            System.out.println(c.toString());
        }
    }

    private static void mostrarUsuarios() {
        for (usuario u : usuarioDAO.getAll()) {
            System.out.println(u.toString());
        }
    }

    private static void modificarUsuario() {
        if (usuarioDAO.getAll().size() == 0) {
            System.out.println("Añade un usuario primero");
            return;
        }

        List<Long> usuarios = new ArrayList<>();
        for (usuario u : usuarioDAO.getAll()) {
            usuarios.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if (!usuarios.contains(usuario)) {
            System.out.println("El usuario no existe");
            return;
        }

        System.out.println("Introduce el nuevo nombre");
        String nombre = sc.next();
        System.out.println("Introduce el nuevo email");
        String email = sc.next();
        System.out.println("Introduce la nueva contraseña");
        String password = sc.next();

        usuario u = new usuario(usuario, nombre, email, password);

        if(usuarioDAO.update(u) == 1) {
            System.out.println("Usuario modificado");
        }
        else {
            System.out.println("Error al modiciar el usuario");
        }
    }

    private static void modificarCategoria() {
        if (categoriaDAO.getAll().size() == 0) {
            System.out.println("Añade una categoria primero");
            return;
        }

        System.out.println("Selecciona una categoria");
        List<Long> categorias = new ArrayList<>();
        for (Categoria c : categoriaDAO.getAll()) {
            categorias.add(c.getId());
            System.out.println(c.toString());
        }
        Long categoria = sc.nextLong();
        if(!categorias.contains(categoria)) {
            System.out.println("La categoria no existe");
            return;
        }

        System.out.println("Introduce el nuevo nombre");
        String nombre = sc.next();
        System.out.println("Introduce la nueva descripcion");
        String descripcion = sc.next();

        Categoria c = new Categoria(categoria, nombre, descripcion);

        if(categoriaDAO.update(c) == 1) {
            System.out.println("Categoria modificada");
        }
        else {
            System.out.println("Error al modiciar la categoria");
        }
    }

    private static void tareasEstado() {
        if (usuarioDAO.getAll().size() == 0) {
            System.out.println("Añade un usuario primero");
            return;
        }

        List<Long> tareasUsuario = new ArrayList<>();
        System.out.println("Selecciona un usuario");
        for (usuario u : usuarioDAO.getAll()) {
            tareasUsuario.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if(!tareasUsuario.contains(usuario)) {
            System.out.println("El usuario no existe");
            return;
        }

        System.out.println("Selecciona un estado");
        for (estado e : estados) {
            System.out.println(e.toString());
        }
        Long estado = sc.nextLong();
        if(estado != 1L && estado != 2L && estado != 3L &&  estado != 4L)  {
            System.out.println("Introduce un estado válido");
            return;
        }

        for (tareas t : tareasDAO.getTareasEstado(estado)) {
            if (tareasUsuario.contains(t.getId())) {
                System.out.println(t.toString());
            }
        }
    }

    private static void tareasCategoria() {
        if (usuarioDAO.getAll().size() == 0) {
            System.out.println("Añade un usuario primero");
            return;
        }
        if (categoriaDAO.getAll().size() == 0) {
            System.out.println("Añade una categoria primero");
            return;
        }
        List<Long> tareasUsuario = new ArrayList<>();
        System.out.println("Selecciona un usuario");
        for (usuario u : usuarioDAO.getAll()) {
            tareasUsuario.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if (!tareasUsuario.contains(usuario)) {
            System.out.println("El usuario no existe");
            return;
        }

        System.out.println("Selecciona una categoria");
        List<Long> categorias = new ArrayList<>();
        for (Categoria c : categoriaDAO.getAll()) {
            categorias.add(c.getId());
            System.out.println(c.toString());
        }
        Long categoria = sc.nextLong();
        if(!categorias.contains(categoria)) {
            System.out.println("La categoria no existe");
            return;
        }

        for (tareas t : tareasDAO.getTareasCategoria(categoria)) {
            if (tareasUsuario.contains(t.getId())) {
                System.out.println(t.toString() + " Estado: " + estadoDAO.findById(t.getEstado()));
            }
        }
    }

    private static void tareasUsuario() {
        if (usuarioDAO.getAll().size() == 0) {
            System.out.println("Añade un usuario primero");
            return;
        }

        List<Long> usuarios = new ArrayList<>();
        for (usuario u : usuarioDAO.getAll()) {
            usuarios.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if (!usuarios.contains(usuario)) {
            System.out.println("El usuario no existe");
            return;
        }

        for (tareas t : tareasDAO.getTareasUsuario(usuario)) {
            System.out.println(t.toString() + " Estado: " + estadoDAO.findById(t.getEstado()));
        }
    }

    private static void actualizarCategoria() {
        if (usuarioDAO.getAll().size() == 0) {
            System.out.println("Añade un usuario primero");
            return;
        }
        if (categoriaDAO.getAll().size() == 0) {
            System.out.println("Añade una categoria primero");
            return;
        }

        System.out.println("Selecciona un usuario");
        List<Long> usuarios = new ArrayList<>();
        for (usuario u : usuarioDAO.getAll()) {
            usuarios.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if (!usuarios.contains(usuario)) {
            System.out.println("El usuario no existe");
            return;
        }

        System.out.println("Selecciona una tarea");
        for (tareas t : tareasDAO.getTareasUsuario(usuario)) {
            System.out.println(t.toString());
        }
        Long tarea = sc.nextLong();

        System.out.println("Selecciona una categoria");
        List<Long> categorias = new ArrayList<>();
        for (Categoria c : categoriaDAO.getAll()) {
            categorias.add(c.getId());
            System.out.println(c.toString());
        }
        Long categoria = sc.nextLong();
        if(!categorias.contains(categoria)) {
            System.out.println("La categoria no existe");
            return;
        }

        tareas t = tareasDAO.findById(tarea);
        t.setCategoria(categoria);

        if (tareasDAO.update(t) == 1) {
            System.out.println("Estado actualizado");
        } else {
            System.out.println("Error actualizando estado");
        }
    }

    private static void actualizarEstado() {
        if (usuarioDAO.getAll().size() == 0) {
            System.out.println("Añade un usuario primero");
            return;
        }

        System.out.println("Selecciona un usuario");
        List<Long> usuarios = new ArrayList<>();
        for (usuario u : usuarioDAO.getAll()) {
            usuarios.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if (!usuarios.contains(usuario)) {
            System.out.println("El usuario no existe");
            return;
        }

        System.out.println("Selecciona una tarea");
        for (tareas t : tareasDAO.getTareasUsuario(usuario)) {
            System.out.println(t.toString());
        }
        Long tarea = sc.nextLong();
        System.out.println("En qué estado se encuentra la tarea?");
        for (estado e : estados) {
            System.out.println(e.toString());
        }
        Long estado = sc.nextLong();
        if(estado != 1L && estado != 2L && estado != 3L &&  estado != 4L)  {
            System.out.println("Introduce un estado válido");
            return;
        }

        tareas t = tareasDAO.findById(tarea);
        t.setEstado(estado);

        if (tareasDAO.update(t) == 1) {
            System.out.println("Estado actualizado");
        } else {
            System.out.println("Error actualizando estado");
        }


    }

    private static void crearTarea() {
        if (usuarioDAO.getAll().size() == 0) {
            System.out.println("Añade un usuario primero");
            return;
        }
        if (categoriaDAO.getAll().size() == 0) {
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

        while (LocalDate.parse(fecha, formatter).compareTo(LocalDate.now()) < 0) {
            System.out.println("Escribe una fecha que no haya pasado aún");
            System.out.println("Añade una fecha límite");
            fecha = sc.next();
        }

        System.out.println("Para qué usuario es la tarea?");
        List<Long> usuarios = new ArrayList<>();
        for (usuario u : usuarioDAO.getAll()) {
            usuarios.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if (!usuarios.contains(usuario)) {
            System.out.println("El usuario no existe");
            return;
        }

        System.out.println("En qué categoria está?");
        List<Long> categorias = new ArrayList<>();
        for (Categoria c : categoriaDAO.getAll()) {
            categorias.add(c.getId());
            System.out.println(c.toString());
        }
        Long categoria = sc.nextLong();
        if(!categorias.contains(categoria)) {
            System.out.println("La categoria no existe");
            return;
        }
        sc.nextLine();

        System.out.println("Añade observaciones");
        String observaciones = sc.nextLine();
        tareas t = new tareas(null, nombre, descripcion, LocalDate.now(), LocalDate.parse(fecha, formatter),
                usuario, 1L, categoria, observaciones);
        tareas.add(t);

        if (tareasDAO.add(t) == 1) {
            System.out.println("Tarea añadida");
        } else {
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
        List<tareas> listaTareas = new ArrayList<>();
        c.setTareas(listaTareas);

        categorias.add(c);
        if (categoriaDAO.add(c) == 1) {
            System.out.println("Categoria añadida");
        } else {
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

        usuario u = new usuario(null, nombre, email, password);
        List<tareas> listaTareas = new ArrayList<>();
        u.setTareas(listaTareas);

        usuarios.add(u);
        if (usuarioDAO.add(u) == 1) {
            System.out.println("Usuario añadido");
        } else {
            System.out.println("Error añadiendo usuario");
        }

    }
}
