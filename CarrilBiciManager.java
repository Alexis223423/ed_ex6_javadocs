package javadoc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Clase que gestiona los tramos de carriles bici de una ciudad o región.
 * 
 * Permite registrar nuevos tramos, modificar su estado, consultar su longitud
 * y generar informes con la información actual de todos los tramos.
 */
public class CarrilBiciManager {

    /** Mapa de tramos: nombre del tramo → longitud en kilómetros. */
    private final Map<String, Double> tramos;

    /** Mapa de estados: nombre del tramo → estado actual del tramo. */
    private final Map<String, String> estadoTramos;

    /**
     * Constructor. Inicializa la estructura de datos para los tramos y sus estados.
     */
    public CarrilBiciManager() {
        this.tramos = new HashMap<>();
        this.estadoTramos = new HashMap<>();
    }

    /**
     * Añade un nuevo tramo al sistema con su correspondiente longitud.
     * El estado inicial del tramo será "En servicio".
     *
     * @param nombre   Nombre del tramo (no puede ser nulo ni vacío).
     * @param longitud Longitud del tramo en kilómetros (debe ser mayor que cero).
     * @throws IllegalArgumentException si el nombre es inválido o la longitud no es válida.
     */
    public void añadirTramo(String nombre, double longitud) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del tramo no puede estar vacío");
        }
        if (longitud <= 0) {
            throw new IllegalArgumentException("La longitud debe ser mayor que cero");
        }
        tramos.put(nombre, longitud);
        estadoTramos.put(nombre, "En servicio");
    }

    /**
     * Actualiza el estado de un tramo existente.
     *
     * @param nombre       Nombre del tramo a actualizar.
     * @param nuevoEstado  Nuevo estado que se desea asignar al tramo.
     * @throws NoSuchElementException si el tramo no existe.
     */
    public void actualizarEstado(String nombre, String nuevoEstado) {
        if (!tramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe: " + nombre);
        }
        estadoTramos.put(nombre, nuevoEstado);
    }
    
    /**
     * Alias del método {@link #actualizarEstado(String, String)}.
     *
     * @param nombre Nombre del tramo.
     * @param estado Nuevo estado del tramo.
     */
    public void cambiarEstado(String nombre, String estado) {
        actualizarEstado(nombre, estado);
    }

    /**
     * Consulta el estado actual de un tramo específico.
     *
     * @param nombre Nombre del tramo.
     * @return Estado actual del tramo.
     * @throws NoSuchElementException si el tramo no existe.
     */
    public String consultarEstado(String nombre) {
        if (!estadoTramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe");
        }
        return estadoTramos.get(nombre);
    }

    /**
     * Calcula la longitud total de todos los tramos registrados.
     *
     * @return Suma total de la longitud de los tramos, en kilómetros.
     */
    public double longitudTotal() {
        return tramos.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Devuelve un mapa inmodificable con todos los tramos y sus longitudes.
     *
     * @return Mapa de tramos (nombre → longitud en kilómetros).
     */
    public Map<String, Double> obtenerTramos() {
        return Collections.unmodifiableMap(tramos);
    }

    /**
     * Genera un informe con el listado de todos los tramos, su longitud y su estado,
     * incluyendo la longitud total acumulada.
     *
     * @return Cadena de texto con el informe detallado.
     */
    public String generarInforme() {
        StringBuilder sb = new StringBuilder("INFORME DE CARRILES BICI - Bahía de Cádiz\n");
        sb.append("===========================================\n");
        for (String nombre : tramos.keySet()) {
            sb.append("- ").append(nombre).append(" (")
              .append(tramos.get(nombre)).append(" km): ")
              .append(estadoTramos.get(nombre)).append("\n");
        }
        sb.append("Longitud total: ").append(longitudTotal()).append(" km\n");
        return sb.toString();
    }
}

