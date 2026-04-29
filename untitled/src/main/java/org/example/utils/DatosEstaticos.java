package org.example.utils;

import org.example.modelo.Estado;

public class DatosEstaticos {
    Estado pendiente = new Estado(1, "Pendiente");
    Estado enProgreso = new  Estado(2, "En Progreso");
    Estado completada = new  Estado(3, "completada");
    Estado cancelada = new  Estado(4, "cancelada");

}
