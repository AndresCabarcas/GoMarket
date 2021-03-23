package com.testing.gomarket;

public class Tienda {
    private Double latitud;
    private Double Longitud;
    private String nombre;
    private String NIT;
    private String descripcion;
    private String direccion;
    private String horario;
    private String imagen;

    public Tienda() {
        super();
    }

    public Tienda(Double latitud, Double longitud, String nombre, String NIT, String descripcion, String direccion, String horario, String imagen) {
        this.latitud = latitud;
        Longitud = longitud;
        this.nombre = nombre;
        this.NIT = NIT;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.horario = horario;
        this.imagen = imagen;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return Longitud;
    }

    public void setLongitud(Double longitud) {
        Longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
