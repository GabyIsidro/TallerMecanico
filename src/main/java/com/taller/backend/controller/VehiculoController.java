package com.taller.backend.controller;

import com.taller.backend.model.Vehiculo; // La clase Vehiculo que representa la entidad de tu base de datos
import com.taller.backend.service.VehiculoService; // El servicio que contiene la lógica de negocio para manejar los Vehiculo, como obtenerlos, guardarlos, actualizarlos y eliminarlos
import org.springframework.beans.factory.annotation.Autowired; // Anotación que le dice a Spring que inyecte automáticamente una instancia de VehiculoService
import org.springframework.web.bind.annotation.*; // Importa las anotaciones de Spring MVC para definir el controlador, las rutas y los métodos HTTP, como @RestController, @RequestMapping, @GetMapping, @PostMapping, @PutMapping y @DeleteMapping

import java.util.List; // Importa la clase List para manejar colecciones de Vehiculo
import java.util.Optional; // Importa la clase Optional para manejar valores que pueden ser nulos, como cuando buscas un Vehiculo por ID y no lo encuentras

@RestController // Anotación que combina las dos anteriores
@RequestMapping("/api/vehiculos") //La ruta base para este controlador
@CrossOrigin(origins = "*") //Permite solicitudes desde cualquier origen (útil para desarrollo)

public class VehiculoController { // Clase que maneja las solicitudes HTTP relacionadas con los Vehiculo, como obtenerlos, guardarlos, actualizarlos y eliminarlos
    
    @Autowired // Anotación que le dice a Spring que inyecte automáticamente una instancia de VehiculoService
    private VehiculoService vehiculoService; // El servicio que contiene la lógica de negocio para manejar los Vehiculo, como obtenerlos, guardarlos, actualizarlos y eliminarlos

    // Obtener: http://localhost:8080/api/vehiculos
    @GetMapping
    public List<Vehiculo> getAll() { 
        return vehiculoService.getAllVehiculos(); // Llama al método getAllVehiculos() del servicio para obtener una lista de todos los Vehiculo en la base de datos
    }

    // Obtener: http://localhost:8080/api/vehiculos/1
    @GetMapping("/{id}")
    public Optional<Vehiculo> getById(@PathVariable Long id) {
        return vehiculoService.getVehiculoById(id); // Llama al método getVehiculoById() del servicio para obtener un Vehiculo por su ID
    }

    // Crear: http://localhost:8080/api/vehiculos
    @PostMapping
    public Vehiculo create(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.saveVehiculo(vehiculo); // Llama al método saveVehiculo() del servicio para guardar un Vehiculo en la base de datos
    }

    // Actualizar: http://localhost:8080/api/vehiculos/1
    @PutMapping("/{id}")
    public Vehiculo update(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        vehiculo.setId(id); // Asegura que el ID del vehículo a actualizar sea el mismo que el ID en la URL
        return vehiculoService.saveVehiculo(vehiculo); // Llama al método saveVehiculo() del servicio para actualizar un Vehiculo en la base de datos
    }

    // Eliminar: http://localhost:8080/api/vehiculos/1
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehiculoService.deleteVehiculo(id); // Llama al método deleteVehiculo() del servicio para eliminar un Vehiculo de la base de datos
    }
}
