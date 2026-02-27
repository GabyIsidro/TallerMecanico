package com.taller.backend.controller;

import com.taller.backend.model.OrdenTrabajo;
import com.taller.backend.service.OrdenTrabajoService;
import com.taller.backend.model.EstadoOrden;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/api/ordenes") //La ruta base para este controlador
@CrossOrigin(origins = "*") //Permite solicitudes desde cualquier origen (útil para desarrollo)
public class OrdenTrabajoController {

    //Inyección de dependencia para el servicio de ordenes de trabajo
    @Autowired
    private OrdenTrabajoService ordenService;

    //Endpoint para listar todas las ordenes de trabajo
    @GetMapping
    public List<OrdenTrabajo> listarOrdenes() {
        return ordenService.obtenerTodas();
    }

    //Endpoint para crear una orden de trabajo
    @PostMapping
    public OrdenTrabajo crearOrden(@RequestBody OrdenTrabajo orden) {
        return ordenService.guardarOrden(orden);
    }

    //Endpoint para ver historial de un auto especifico
    @GetMapping("/vehiculo/{id}")
    public List<OrdenTrabajo> listarPorVehiculo(@PathVariable Long id) {
        return ordenService.obtenerPorVehiculo(id);
    }

    //Endpoint para cambiar el estado de una orden de trabajo
    @PatchMapping("/{id}/estado")
    public OrdenTrabajo cambiarEstado(@PathVariable Long id, @RequestParam EstadoOrden estado){
        return ordenService.actualizarEstado(id, estado);
    }

    //Endpoint para cambiar el costo de una orden de trabajo
    @PatchMapping("/{id}/costo")
    public OrdenTrabajo cambiarCosto(@PathVariable Long id, @RequestParam Double costo){
        return ordenService.actualizarCosto(id, costo);
    }
}
