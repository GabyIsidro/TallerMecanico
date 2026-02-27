package com.taller.backend.service;

import com.taller.backend.model.Vehiculo; // La clase Vehiculo que representa la entidad de tu base de datos
import com.taller.backend.repository.ClienteRepository; // El repositorio que te permite interactuar con la base de datos para la entidad Cliente
import com.taller.backend.model.Cliente; // La clase Cliente que representa la entidad de tu base de datos
import com.taller.backend.repository.VehiculoRepository; // El repositorio que te permite interactuar con la base de datos para la entidad Vehiculo
import org.springframework.beans.factory.annotation.Autowired; // Anotación que le dice a Spring que inyecte automáticamente una instancia de VehiculoRepository
import org.springframework.stereotype.Service; // Anotación que le dice a Spring que esta clase es un servicio, lo que la hace elegible para ser inyectada en otras partes de tu aplicación
import java.util.List; // Importa la clase List para manejar colecciones de Vehiculo
import java.util.Optional; // Importa la clase Optional para manejar valores que pueden ser nulos, como cuando buscas un Vehiculo por ID y no lo encuentras

@Service // Anotación que combina las dos anteriores
public class VehiculoService {
    
    @Autowired // Anotación que le dice a Spring que inyecte automáticamente una instancia de VehiculoRepository
    private VehiculoRepository vehiculoRepository; // El repositorio que te permite interactuar con la base de datos para la entidad Vehiculo

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los vehiculos
    public List<Vehiculo> getAllVehiculos() { // Método que devuelve una lista de todos los Vehiculo en la base de datos
        return vehiculoRepository.findAll(); // Llama al método findAll() del repositorio para obtener todos los Vehiculo
    }

    // Obtener un vehiculo por ID
    public Optional<Vehiculo> getVehiculoById(Long id) { // Método que devuelve un Optional<Vehiculo> que puede contener un Vehiculo o estar vacío si no se encuentra
        return vehiculoRepository.findById(id); // Llama al método findById() del repositorio para obtener un Vehiculo por su ID
    }

    // Guardar o actualizar un vehiculo
    public Vehiculo saveVehiculo(Vehiculo vehiculo) {// Método que guarda o actualiza un Vehiculo en la base de datos y devuelve el Vehiculo guardado
        // Aquí podrías agregar lógica adicional, como validaciones o transformaciones de datos
        
        if (vehiculo.getCliente() != null && vehiculo.getCliente().getId() != null) {
            Cliente clienteReal = clienteRepository.findById(vehiculo.getCliente().getId()).orElse(null);
            if (clienteReal != null) {
                vehiculo.setCliente(clienteReal);
            }
        }
        return vehiculoRepository.save(vehiculo); // Llama al método save() del repositorio para guardar o actualizar un Vehiculo
    }

    // Eliminar un vehiculo por ID
    public void deleteVehiculo(Long id) { // Método que elimina un Vehiculo de la base de datos por su ID
        vehiculoRepository.deleteById(id); // Llama al método deleteById() del repositorio para eliminar un Vehiculo por su ID
    }
}
