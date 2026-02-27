package com.taller.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes_trabajo")
@Data
public class OrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único de la orden de trabajo

    //Para que la fecha se guarde automaticamente al crear una nueva orden de trabajo
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaIngreso; // Fecha de ingreso de la orden de trabajo

    private String descripcion; // Descripción de la orden de trabajo
    private Double costoTotal; // Costo total de la orden de trabajo, calculado a partir de los repuestos y servicios asociados
    @Enumerated(EnumType.STRING) // Guarda el nombre del estado en la BD
    private EstadoOrden estado; // Estado de la orden de trabajo, usando el enum EstadoOrden

    //RELACION: Una orden pertece a un Vehiculo
    @ManyToOne
    @JoinColumn(name = "vehiculo_id") // Nombre de la columna que se usará como clave foránea en la tabla ordenes_trabajo
    @ToString.Exclude // Para evitar la referencia circular al imprimir el objeto
    @JsonIgnoreProperties("ordenes") // Para evitar la referencia circular al serializar a JSON
    private Vehiculo vehiculo;

    //Antes de guardar en la BD, poner la fecha actual si esta vacia
    @PrePersist
    public void prePersist() {
        if (fechaIngreso == null) {
            fechaIngreso = LocalDateTime.now();
        }
    }
}
