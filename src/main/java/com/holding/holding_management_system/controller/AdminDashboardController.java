package com.holding.holding_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @GetMapping("/dashboard")
    public String getAdminDashboard(Model model) {
        // Datos simulados (reemplazar con datos reales del servicio o repositorio)
        model.addAttribute("empresasActivas", 15);
        model.addAttribute("vendedoresActivos", 25);
        model.addAttribute("asesoresActivos", 10);
        model.addAttribute("facturacionTotal", 500000);

        model.addAttribute("empresasRecientes", new String[]{"Empresa A", "Empresa B", "Empresa C"});
        model.addAttribute("actividadReciente", new String[]{"Actividad 1", "Actividad 2", "Actividad 3"});

        return "frontend/dashboard-admin"; // Renderizar la vista desde templates/frontend/dashboard-admin.html
    }
}