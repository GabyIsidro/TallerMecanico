package com.taller.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Importa la anotación @JsonIgnoreProperties de Jackson para evitar problemas de serialización y deserialización con relaciones bidireccionales
import jakarta.persistence.*; // Importa las anotaciones de JPA para definir la entidad y sus propiedades
import lombok.Data; // Importa la anotación @Data de Lombok para generar automáticamente getters, setters, toString, equals y hashCode
import lombok.ToString;

@Entity // Anotación que le dice a Spring que esta clase es una entidad JPA
@Table(name = "vehiculos") // Anotación que especifica el nombre de la tabla en la base de datos
@Data // Anotación de Lombok que genera automáticamente getters, setters, toString, equals y hashCode

public class Vehiculo { // Clase que representa la entidad Vehiculo en la base de datos

    @Id // Anotación que indica que este campo es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotación que indica que el valor de este campo se generará automáticamente por la base de datos, utilizando una estrategia de identidad (auto-incremental)
    private Long id; // Campo que representa el ID del vehículo, es la clave primaria y se genera automáticamente por la base de datos

    private String marca; // Campo que representa la marca del vehículo
    private String modelo; // Campo que representa el modelo del vehículo
    private String patente; // Campo que representa la patente del vehículo
    private String color; // Campo que representa el color del vehículo
    private int anio; // Campo que representa el año del vehículo

    @Enumerated(EnumType.STRING) // Anotación que indica que el campo categoria se almacenará como una cadena en la base de datos, utilizando los nombres de las constantes del enum
    private CategoriaVehiculo categoria; // Campo que representa la categoría del vehículo, utilizando el enum CategoriaVehiculo
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL) // Anotación que indica que esta entidad tiene una relación de uno a muchos con la entidad OrdenTrabajo
    @ManyToOne // Anotación que indica que esta entidad tiene una relación de muchos a uno con la entidad Cliente
    @JoinColumn(name = "cliente_id") // Anotación que especifica el nombre de la columna en la tabla de vehículos que se usará para la relación con la tabla de clientes
    @ToString.Exclude // Evita que Lombok incluya este campo en el método toString para prevenir recursión infinita
    @JsonIgnoreProperties("vehiculos") // Evita problemas de serialización y deserialización con relaciones bidireccionales, ignorando la propiedad "vehiculos" en la clase Cliente
    private Cliente cliente; // Campo que representa la relación con la entidad Cliente, es decir,
}