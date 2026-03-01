package com.taller.backend.controller;

import com.taller.backend.model.TipoServicio;
import com.taller.backend.repository.TipoServicioRepository;
import com.taller.backend.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/servicio")
@CrossOrigin(origins = "*")
public class TipoServicioController {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    @Autowired
    private DataImportService dataImportService;

    @PostMapping("/importar")
    public ResponseEntity<String> importarPrecios(@RequestParam("file") MultipartFile file) {
        try {
            String resultado = dataImportService.importarPrecios(file);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al importar: " + e.getMessage());
        }
    }
    
    @GetMapping
    public List<TipoServicio> listarServicios() {
        return tipoServicioRepository.findAll();
    }

}
