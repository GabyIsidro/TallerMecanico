package com.taller.backend.service;

import com.taller.backend.model.EstadoOrden;
import com.taller.backend.model.OrdenTrabajo;
import com.taller.backend.model.Vehiculo;
import com.taller.backend.repository.OrdenTrabajoRepository;
import com.taller.backend.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenTrabajoService {

    @Autowired
    private OrdenTrabajoRepository ordenRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    //Metodo para obtener todas las ordenes de trabajo
    public List<OrdenTrabajo> obtenerTodas() {
        return ordenRepository.findAll();
    }

    //Metodo para guardar una orden de trabajo
    public OrdenTrabajo guardarOrden(OrdenTrabajo orden) {
        //Valida que venga con un vehiculo 
        if (orden.getVehiculo() != null && orden.getVehiculo().getId() != null) {
            //Busca el vehiculo en la base de datos
            Vehiculo vehiculoReal = vehiculoRepository.findById(orden.getVehiculo().getId()).orElse(null);

            //Si existe el vehiculo, lo asigna a la orden de trabajo
            if (vehiculoReal != null) {
                orden.setVehiculo(vehiculoReal);
            }

        }
        return ordenRepository.save(orden);
    }

    //Metodo para obtener las ordenes de trabajo por el id del vehiculo
    public List<OrdenTrabajo> obtenerPorVehiculo(Long vehiculoId) {
        return ordenRepository.findByVehiculoId(vehiculoId);
    }

    //Metodo para cambiar el estado de una orden
    public OrdenTrabajo actualizarEstado(Long id, EstadoOrden nuevoEstado) {
        OrdenTrabajo orden = ordenRepository.findById(id).orElseThrow(()->new RuntimeException("Orden no encontrada"));
        orden.setEstado(nuevoEstado);
        return ordenRepository.save(orden);
    }

    //Metodo para actualizar el presupuesto de una orden de trabajo
    public OrdenTrabajo actualizarCosto(Long id, Double nuevoCosto){
        OrdenTrabajo orden = ordenRepository.findById(id).orElseThrow(()->new RuntimeException("Orden no encontrada"));
        orden.setCostoTotal(nuevoCosto);
        return ordenRepository.save(orden);
    }
}
