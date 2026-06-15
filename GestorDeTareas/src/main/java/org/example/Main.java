package org.example;

import org.example.DAO.categoriaDAO;
import org.example.DAO.estadoDAO;
import org.example.DAO.tareasDAO;
import org.example.DAO.usuarioDAO;
import org.example.modelo.usuario;
import org.example.modelo.tareas;
import org.example.modelo.categoria;
import org.example.modelo.estado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static List<usuario> usuarios = new ArrayList<>();
    private static List<categoria> categorias = new ArrayList<>();
    private static List<tareas> tareas = new ArrayList<>();
    private static List<estado> estados = new ArrayList<>();

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    private static usuarioDAO usuarioDAO = new usuarioDAO();
    private static tareasDAO tareasDAO = new tareasDAO();
    private static estadoDAO estadoDAO = new estadoDAO();
    private static categoriaDAO categoriaDAO = new categoriaDAO();


    static void main() throws Exception {
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

    /**
     * Elimina una tarea de la base de datos
     * @throws ArrayStoreException - se lanza cuando no se encuentra ninguna tarea en la base de datos
     * @throws Exception - se lanza al no poder eliminar una tarea
     */
    private static void borrarTarea() throws Exception {
        if (tareasDAO.getAll().size() == 0) {
            throw new ArrayStoreException("Añade una tarea primero");
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
            throw new Exception("Error al eliminar tarea");
        }
    }

    private static void mostrarCategorias() {
        for (categoria c : categoriaDAO.getAll()) {
            System.out.println(c.toString());
        }
    }

    private static void mostrarUsuarios() {
        for (usuario u : usuarioDAO.getAll()) {
            System.out.println(u.toString());
        }
    }

    /**
     * Modifica los datos de un usuario
     * @throws ArrayStoreException - se lanza cuando no se encuentra ningún usuarioen la base de datos
     * @throws ArrayIndexOutOfBoundsException - se lanza cuando se introduce un usuario que no existe
     * @throws Exception - se lanza al no poder modificar un usuario
     */
    private static void modificarUsuario() throws Exception {
        if (usuarioDAO.getAll().size() == 0) {
            throw new ArrayStoreException("Añade un usuario primero");
        }

        List<Long> usuarios = new ArrayList<>();
        for (usuario u : usuarioDAO.getAll()) {
            usuarios.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if (!usuarios.contains(usuario)) {
            throw new ArrayIndexOutOfBoundsException("El usuario no existe");
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
            throw new Exception("Error al modificar el usuario");
        }
    }

    /**
     * Modifica los datos de una categoría
     * @throws ArrayStoreException - se lanza cuando no se encuentra ninguna categoría en la base de datos
     * @throws ArrayIndexOutOfBoundsException - se lanza cuando se introduce una categoría que no existe
     * @throws Exception - se lanza al no poder modificar una categoría
     */
    private static void modificarCategoria() throws Exception {
        if (categoriaDAO.getAll().size() == 0) {
            throw new ArrayStoreException("Añade una categoria primero");
        }

        System.out.println("Selecciona una categoria");
        List<Long> categorias = new ArrayList<>();
        for (categoria c : categoriaDAO.getAll()) {
            categorias.add(c.getId());
            System.out.println(c.toString());
        }
        Long categoria = sc.nextLong();
        if(!categorias.contains(categoria)) {
            throw new ArrayIndexOutOfBoundsException("La categoria no existe");
        }

        System.out.println("Introduce el nuevo nombre");
        String nombre = sc.next();
        System.out.println("Introduce la nueva descripcion");
        String descripcion = sc.next();

        categoria c = new categoria(categoria, nombre, descripcion);

        if(categoriaDAO.update(c) == 1) {
            System.out.println("Categoria modificada");
        }
        else {
            throw new Exception("Error al modiciar la categoria");
        }
    }

    /**
     * Muestra todas las tareas de un usuario que se encuentran en un mismo estado
     * @throws ArrayStoreException - se lanza cuando no se encuentra ningún usuario en la base de datos
     * @throws ArrayIndexOutOfBoundsException - se lanza cuando se introduce un usuario o un estado que no existe
     */
    private static void tareasEstado() {
        if (usuarioDAO.getAll().size() == 0) {
            throw new ArrayStoreException("Añade un usuario primero");
        }

        List<Long> tareasUsuario = new ArrayList<>();
        System.out.println("Selecciona un usuario");
        for (usuario u : usuarioDAO.getAll()) {
            tareasUsuario.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if(!tareasUsuario.contains(usuario)) {
            throw new ArrayIndexOutOfBoundsException("El usuario no existe");
        }

        System.out.println("Selecciona un estado");
        for (estado e : estados) {
            System.out.println(e.toString());
        }
        Long estado = sc.nextLong();
        if(estado != 1L && estado != 2L && estado != 3L &&  estado != 4L)  {
            throw new ArrayIndexOutOfBoundsException("Introduce un estado válido");
        }

        for (tareas t : tareasDAO.getTareasEstado(estado)) {
            if (tareasUsuario.contains(t.getId())) {
                System.out.println(t.toString());
            }
        }
    }

    /**
     * Muestra todas las tareas de un usuario de una misma categoría
     * @throws ArrayStoreException - se lanza cuando no se encuentra ningún usuario o ninguna categoría en la base de datos
     * @throws ArrayIndexOutOfBoundsException - se lanza cuando se introduce un usuario o una categoría que no existe
     */
    private static void tareasCategoria() {
        if (usuarioDAO.getAll().size() == 0) {
            throw new ArrayStoreException("Añade un usuario primero");
        }
        if (categoriaDAO.getAll().size() == 0) {
            throw new ArrayStoreException("Añade una categoria primero");
        }
        List<Long> tareasUsuario = new ArrayList<>();
        System.out.println("Selecciona un usuario");
        for (usuario u : usuarioDAO.getAll()) {
            tareasUsuario.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if (!tareasUsuario.contains(usuario)) {
            throw new ArrayIndexOutOfBoundsException("El usuario no existe");
        }

        System.out.println("Selecciona una categoria");
        List<Long> categorias = new ArrayList<>();
        for (categoria c : categoriaDAO.getAll()) {
            categorias.add(c.getId());
            System.out.println(c.toString());
        }
        Long categoria = sc.nextLong();
        if(!categorias.contains(categoria)) {
            throw new ArrayIndexOutOfBoundsException("La categoria no existe");
        }

        for (tareas t : tareasDAO.getTareasCategoria(categoria)) {
            if (tareasUsuario.contains(t.getId())) {
                System.out.println(t.toString() + " Estado: " + estadoDAO.findById(t.getEstado()));
            }
        }
    }

    /**
     * Muestra todas las tareas de un usuario
     * @throws ArrayStoreException - se lanza cuando no se encuentra ningún usuario en la base de datos
     * @throws ArrayIndexOutOfBoundsException - se lanza cuando se introduce un usuario que no existe
     */
    private static void tareasUsuario() {
        if (usuarioDAO.getAll().size() == 0) {
            throw new ArrayStoreException("Añade un usuario primero");
        }

        List<Long> usuarios = new ArrayList<>();
        for (usuario u : usuarioDAO.getAll()) {
            usuarios.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if (!usuarios.contains(usuario)) {
            throw new ArrayIndexOutOfBoundsException("El usuario no existe");
        }

        for (tareas t : tareasDAO.getTareasUsuario(usuario)) {
            System.out.println(t.toString() + " Estado: " + estadoDAO.findById(t.getEstado()));
        }
    }

    /**
     * Cambia la categoría de una tarea
     * @throws ArrayStoreException - se lanza cuando no se encuentra ningún usuario o ninguna categoría en la base de datos
     * @throws ArrayIndexOutOfBoundsException - se lanza cuando se introduce un usuario, una categoría o una tarea que no existe
     * @throws Exception - se lanza al no poder actualizar una categoría
     */
    private static void actualizarCategoria() throws Exception {
        if (usuarioDAO.getAll().size() == 0) {
            throw new ArrayStoreException("Añade un usuario primero");
        }
        if (categoriaDAO.getAll().size() == 0) {
            throw new ArrayStoreException("Añade una categoria primero");
        }

        System.out.println("Selecciona un usuario");
        List<Long> usuarios = new ArrayList<>();
        for (usuario u : usuarioDAO.getAll()) {
            usuarios.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if (!usuarios.contains(usuario)) {
            throw new ArrayIndexOutOfBoundsException("El usuario no existe");
        }

        System.out.println("Selecciona una tarea");
        List<Long> tareas = new ArrayList<>();
        for (tareas t : tareasDAO.getTareasUsuario(usuario)) {
            tareas.add(t.getId());
            System.out.println(t.toString());
        }
        Long tarea = sc.nextLong();
        if(!tareas.contains(tarea)) {
            throw new ArrayIndexOutOfBoundsException("La tarea no existe");
        }

        System.out.println("Selecciona una categoria");
        List<Long> categorias = new ArrayList<>();
        for (categoria c : categoriaDAO.getAll()) {
            categorias.add(c.getId());
            System.out.println(c.toString());
        }
        Long categoria = sc.nextLong();
        if(!categorias.contains(categoria)) {
            throw new ArrayIndexOutOfBoundsException("La categoria no existe");
        }

        tareas t = tareasDAO.findById(tarea);
        t.setCategoria(categoria);

        if (tareasDAO.update(t) == 1) {
            System.out.println("Estado actualizado");
        } else {
            throw new Exception("Error actualizando estado");
        }
    }

    /**
     * Cambia el estado en el que se encuentra una tarea
     * @throws ArrayStoreException - se lanza cuando no se encuentra ningún usuario en la base de datos
     * @throws ArrayIndexOutOfBoundsException - se lanza cuando se introduce un usuario, un estado o una tarea que no existe
     * @throws Exception - se lanza al no poder actualizar un estado
     */
    private static void actualizarEstado() throws Exception {
        if (usuarioDAO.getAll().size() == 0) {
            throw new ArrayStoreException("Añade un usuario primero");
        }

        System.out.println("Selecciona un usuario");
        List<Long> usuarios = new ArrayList<>();
        for (usuario u : usuarioDAO.getAll()) {
            usuarios.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if (!usuarios.contains(usuario)) {
            throw new ArrayIndexOutOfBoundsException("El usuario no existe");
        }

        System.out.println("Selecciona una tarea");
        List<Long> tareas = new ArrayList<>();
        for (tareas t : tareasDAO.getTareasUsuario(usuario)) {
            tareas.add(t.getId());
            System.out.println(t.toString());
        }
        Long tarea = sc.nextLong();
        if(!tareas.contains(tarea)) {
            throw new ArrayIndexOutOfBoundsException("La tarea no existe");
        }

        System.out.println("En qué estado se encuentra la tarea?");
        for (estado e : estados) {
            System.out.println(e.toString());
        }
        Long estado = sc.nextLong();
        if(estado != 1L && estado != 2L && estado != 3L &&  estado != 4L)  {
            throw new ArrayIndexOutOfBoundsException("Introduce un estado válido");
        }

        tareas t = tareasDAO.findById(tarea);
        t.setEstado(estado);

        if (tareasDAO.update(t) == 1) {
            System.out.println("Estado actualizado");
        } else {
            throw new Exception("Error actualizando estado");
        }
    }

    /**
     * Crea una nueva tarea u la añade a la base de datos
     * @throws ArrayStoreException - se lanza cuando no se encuentra ningún usuario o ninguna categoría en la base de datos
     * @throws IllegalArgumentException - se lanza cuando se introduce una fecha anterior a la actual
     * @throws ArrayIndexOutOfBoundsException - se lanza cuando se introduce un usuario o una categoría que no existe
     * @throws Exception - se lanza al no poder añadir una tarea a la base de datos
     */
    private static void crearTarea() throws Exception {
        if (usuarioDAO.getAll().size() == 0) {
            throw new ArrayStoreException("Añade un usuario primero");
        }
        if (categoriaDAO.getAll().size() == 0) {
            throw new ArrayStoreException("Añade una categoria primero");
        }

        System.out.println("Escribe el nombre de la tarea");
        String nombre = sc.next();
        sc.nextLine();

        System.out.println("Añade una descripcion de la tarea");
        String descripcion = sc.nextLine();

        System.out.println("Añade una fecha límite");
        String fecha = sc.next();

        if (LocalDate.parse(fecha, formatter).compareTo(LocalDate.now()) < 0) {
            throw new IllegalArgumentException("La fecha ya ha pasado");
        }

        System.out.println("Para qué usuario es la tarea?");
        List<Long> usuarios = new ArrayList<>();
        for (usuario u : usuarioDAO.getAll()) {
            usuarios.add(u.getId());
            System.out.println(u.toString());
        }
        Long usuario = sc.nextLong();
        if (!usuarios.contains(usuario)) {
            throw new ArrayIndexOutOfBoundsException("El usuario no existe");
        }

        System.out.println("En qué categoria está?");
        List<Long> categorias = new ArrayList<>();
        for (categoria c : categoriaDAO.getAll()) {
            categorias.add(c.getId());
            System.out.println(c.toString());
        }
        Long categoria = sc.nextLong();
        if(!categorias.contains(categoria)) {
            throw new ArrayIndexOutOfBoundsException("La categoria no existe");
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
            throw new Exception("Error añadiendo tarea");
        }
    }

    /**
     * Crea una nueva categoría u la añade a la base de datos
     * @throws Exception - Se lanza al no poder añadir la categoría a la base de datos
     */
    private static void crearCategoria() throws Exception {
        System.out.println("Escribe el nombre de la categoria");
        String nombreCategoria = sc.next();
        sc.nextLine();

        System.out.println("Añade una pequeña descripción");
        String descripcionCategoria = sc.nextLine();

        categoria c = new categoria(null, nombreCategoria, descripcionCategoria);
        List<tareas> listaTareas = new ArrayList<>();
        c.setTareas(listaTareas);

        categorias.add(c);
        if (categoriaDAO.add(c) == 1) {
            System.out.println("Categoria añadida");
        } else {
            throw new Exception("Error añadiendo la categoria");
        }
    }

    /**
     * Crea un nuevo usuario y lo añade a la base de datos
     * @throws Exception - Se lanza al no poder añadir el usuario a la base de datos
     */
    private static void crearUsuario() throws Exception {
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
            throw new Exception("Error al añadir el usuario");
        }

    }
}
