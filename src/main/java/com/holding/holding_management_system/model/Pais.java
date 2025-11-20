package com.holding.holding_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;


@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del país no puede estar vacío")
    private String nombre;

    @NotNull(message = "El PIB es obligatorio")
    @Positive(message = "El PIB debe ser un número positivo")
    private Double pib;

    @NotNull(message = "El número de habitantes es obligatorio")
    @Positive(message = "El número de habitantes debe ser un número positivo")
    private Long numeroHabitantes;
    
    @NotBlank(message = "La capital no puede estar vacía")
    private String capital;

    //Empresa y País
    @OneToMany(mappedBy = "sede", cascade = CascadeType.ALL)
    private List<Empresa> empresas;

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

    public Double getPib() {
        return pib;
    }

    public void setPib(Double pib) {
        this.pib = pib;
    }

    public Long getNumeroHabitantes() {
        return numeroHabitantes;
    }

    public void setNumeroHabitantes(Long numeroHabitantes) {
        this.numeroHabitantes = numeroHabitantes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
}