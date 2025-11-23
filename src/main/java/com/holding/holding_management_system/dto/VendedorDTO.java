package com.holding.holding_management_system.dto;

import java.time.LocalDate;
import java.util.List;

public class VendedorDTO {

    private Long id;
    private String codigo;
    private String nombre;
    private String direccion;
    private LocalDate fechaCaptacion;
    private Long empresaId; // ID de la empresa asociada
    private Long captadorId; // ID del vendedor captador
    private List<VendedorDTO> captados; // Subordinados

    // Constructor vacío
    public VendedorDTO() {
    }

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

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Long getCaptadorId() {
        return captadorId;
    }

    public void setCaptadorId(Long captadorId) {
        this.captadorId = captadorId;
    }

    public List<VendedorDTO> getCaptados() {
        return captados;
    }

    public void setCaptados(List<VendedorDTO> captados) {
        this.captados = captados;
    }
}