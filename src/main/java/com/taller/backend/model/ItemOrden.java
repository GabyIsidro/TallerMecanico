package com.taller.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "items_orden")
@Data

public class ItemOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relacion con la orden principal
    @ManyToOne
    @JoinColumn(name = "orden_id")
    @ToString.Exclude
    @JsonIgnoreProperties("items") // Evita la recursión infinita al serializar a JSON
    private OrdenTrabajo orden;

    @ManyToOne
    @JoinColumn(name = "tipo_servicio_id")
    private TipoServicio tipoServicio;

    private Integer cantidad;
    private Double subtotal;

    public void calcularSubtotal(CategoriaVehiculo categoriaVehiculo) {
        double precioUnitario = 0.0;

        //1. Si es mano de obra (ATAIA)
        if (this.tipoServicio != null && categoriaVehiculo != null) {
            switch (categoriaVehiculo) {
                case CATEGORIA_A: precioUnitario = tipoServicio.getPrecioA(); break;
                case CATEGORIA_B: precioUnitario = tipoServicio.getPrecioB(); break;
                case CATEGORIA_C: precioUnitario = tipoServicio.getPrecioC(); break;
            }
        }

        if (this.cantidad == null) this.cantidad = 1;
        this.subtotal = precioUnitario * this.cantidad;
    }
}
