package com.taller.backend.repository;

import com.taller.backend.model.OrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajo, Long> {
    List<OrdenTrabajo> findByVehiculoId(Long vehiculoId); // Método para encontrar órdenes de trabajo por el ID del vehículo
}
