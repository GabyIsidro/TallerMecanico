package com.taller.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

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
    @JsonIgnoreProperties("ordenes") // Para evitar la referencia circular al serializar a JSON
    private Vehiculo vehiculo;

    @ToString.Exclude // Para evitar la referencia circular al imprimir el objeto
    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true) // Relación uno a muchos con ItemOrden
    @JsonIgnoreProperties("orden") // Para evitar la referencia circular al serializar a JSON
    private List<ItemOrden> items = new ArrayList<>();

    //Antes de guardar en la BD, poner la fecha actual si esta vacia
    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (fechaIngreso == null) {
            fechaIngreso = LocalDateTime.now();
        }
        this.costoTotal = getTotal(); // Actualiza el costo total antes de guardar o actualizar la orden
    }

    //Metodo helper para agregar item facilmente y calcular el total
    public void agregarItem(ItemOrden item) {
        items.add(item);
        item.setOrden(this); // Establece la relación bidireccional
    }

    //Metodo para calcular el TOTAL FINAL de toda la orden
    public Double getTotal() {
        double total = 0.0;
        for (ItemOrden item : items){
            if (item.getSubtotal() != null) {
                total += item.getSubtotal();
            }
        }
        return total;
    }

}
