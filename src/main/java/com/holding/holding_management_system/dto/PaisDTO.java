package com.holding.holding_management_system.dto;

import java.util.List;

public class PaisDTO {
    private Long id;
    private String nombre;
    private String capital;
    private Long numeroHabitantes;
    private Double pib;
    private List<String> empresas; // Lista de nombres de empresas asociadas

    // Constructor vacío
    public PaisDTO() {
    }

    // Constructor completo
    public PaisDTO(Long id, String nombre, String capital, Long numeroHabitantes, Double pib, List<String> empresas) {
        this.id = id;
        this.nombre = nombre;
        this.capital = capital;
        this.numeroHabitantes = numeroHabitantes;
        this.pib = pib;
        this.empresas = empresas;
    }

    // Getters y Setters
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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Long getNumeroHabitantes() {
        return numeroHabitantes;
    }

    public void setNumeroHabitantes(Long numeroHabitantes) {
        this.numeroHabitantes = numeroHabitantes;
    }

    public Double getPib() {
        return pib;
    }

    public void setPib(Double pib) {
        this.pib = pib;
    }

    public List<String> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<String> empresas) {
        this.empresas = empresas;
    }
}