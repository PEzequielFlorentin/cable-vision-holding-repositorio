package com.holding.holding_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código del vendedor no puede estar vacío")
    private String codigoVendedor;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    @NotNull(message = "El vendedor debe estar asociado a una empresa")
    private Empresa empresa;

    @OneToMany(mappedBy = "captador", cascade = CascadeType.ALL)
    private List<Vendedor> vendedoresCaptados;

    @ManyToOne
    @JoinColumn(name = "captador_id")
    private Vendedor captador;

    @PastOrPresent(message = "La fecha de captación debe ser en el pasado o el presente")
    private LocalDate fechaCaptacion;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
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

    public LocalDate getFechaCaptacion() {
        return fechaCaptacion;
    }

    public void setFechaCaptacion(LocalDate fechaCaptacion) {
        this.fechaCaptacion = fechaCaptacion;
    }
}
