package com.taller.backend.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.taller.backend.model.TipoServicio;
import com.taller.backend.repository.TipoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

@Service
public class DataImportService {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    public String importarPrecios(MultipartFile file) throws IOException, CsvValidationException {
        //Validacion basica
        if (file.isEmpty()){
            throw new RuntimeException("El archivo esta vacio");
        }

        try (Reader reader = new InputStreamReader(file.getInputStream());
                CSVReader csvReader = new CSVReader(reader)) {
    
                //Leer y descartar encabezado
                csvReader.readNext();
                String[] fila;
                int contador = 0;
                int ignorados = 0;

                //Itera fila por fila desde la linea 2
                while ((fila = csvReader.readNext()) != null) {


                    if (fila.length < 5) {
                        System.out.println("Salteando lineas invalidas" + Arrays.toString(fila));
                        ignorados++;
                        continue; 
                    }

                    TipoServicio servicio = new TipoServicio();

                    //Limpieza y asignacion
                    servicio.setGrupo(fila[0] != null ? fila[0].toUpperCase().trim() : "GENERAL");
                    servicio.setDescripcion(fila[1] != null ? fila[1].trim() : "Sin descripción");

                    //Parseo de precios
                    servicio.setPrecioA(parsearPrecio(fila[2]));
                    servicio.setPrecioB(parsearPrecio(fila[3]));
                    servicio.setPrecioC(parsearPrecio(fila[4]));

                    tipoServicioRepository.save(servicio);
                    contador++;
                 }
                return "Exito!! Se cargaron " + contador + " servicios.";
            }
        }

    //Metodo auxiliar para convertir texto a numero, manejando errores
    private Double parsearPrecio(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return 0.0;
        }
        try {
            String limpio = valor.replace("$","").replace(".","").replace(",", ".").trim();
            return Double.parseDouble(limpio);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    
    }
}
