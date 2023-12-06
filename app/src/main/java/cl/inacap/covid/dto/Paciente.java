package cl.inacap.covid.dto;

import java.io.Serializable;

public class Paciente implements Serializable {
    private int id;
    private String rut;
    private String nombre;
    private String apellido;
    private String fechaExamen;
    private String areaTrabajo;
    private boolean presentaSintomasCovid;
    private double temperatura;
    private boolean presentaTos;
    private int presionArterial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(String fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(String areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    public boolean isPresentaSintomasCovid() {
        return presentaSintomasCovid;
    }

    public void setPresentaSintomasCovid(boolean presentaSintomasCovid) {
        this.presentaSintomasCovid = presentaSintomasCovid;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public boolean isPresentaTos() {
        return presentaTos;
    }

    public void setPresentaTos(boolean presentaTos) {
        this.presentaTos = presentaTos;
    }

    public int getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(int presionArterial) {
        this.presionArterial = presionArterial;
    }

    @Override
    public String toString() {
        return "Rut: " + rut + "\nNombre: " + nombre + "\nApellido: " + apellido + "\nFecha: " + fechaExamen;
    }
}
