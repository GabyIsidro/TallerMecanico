package com.taller.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_servicio")
@Data 
public class TipoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grupo;

    @Column(length = 500)
    private String descripcion;

    private Double precioA;
    private Double precioB;
    private Double precioC;

}
