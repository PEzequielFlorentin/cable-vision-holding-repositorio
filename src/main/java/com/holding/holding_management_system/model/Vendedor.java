package com.holding.holding_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código no puede estar vacío")
    @Column(name = "codigo_vendedor", nullable = false)
    private String codigo;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    @NotNull(message = "La fecha de captación es obligatoria")
    private LocalDate fechaCaptacion;

    // Relación con Empresa
    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    // Relación con otros vendedores captados
    @OneToMany(mappedBy = "captador", cascade = CascadeType.ALL)
    private List<Vendedor> vendedoresCaptados;

    // Relación inversa: el captador de este vendedor
    @ManyToOne
    @JoinColumn(name = "captador_id")
    private Vendedor captador;

    // Constructor vacío
    public Vendedor() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaCaptacion() {
        return fechaCaptacion;
    }

    public void setFechaCaptacion(LocalDate fechaCaptacion) {
        this.fechaCaptacion = fechaCaptacion;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Vendedor> getVendedoresCaptados() {
        return vendedoresCaptados;
    }

    public void setVendedoresCaptados(List<Vendedor> vendedoresCaptados) {
        this.vendedoresCaptados = vendedoresCaptados;
    }

    public Vendedor getCaptador() {
        return captador;
    }

    public void setCaptador(Vendedor captador) {
        this.captador = captador;
    }
}