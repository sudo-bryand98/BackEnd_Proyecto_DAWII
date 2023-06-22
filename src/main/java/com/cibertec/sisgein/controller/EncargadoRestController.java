package com.cibertec.sisgein.controller;

import com.cibertec.sisgein.model.Encargado;
import com.cibertec.sisgein.response.EncargadoResponseRest;
import com.cibertec.sisgein.services.IEncargadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class EncargadoRestController {

    @Autowired
    private IEncargadoService service;

    // OBTENER TODOS LAS ENCARGADOS
    @GetMapping("/encargados")
    public ResponseEntity<EncargadoResponseRest> searchEncargados(){
        ResponseEntity<EncargadoResponseRest> response = service.search();
        return response;
    }

    // OBTENER ENCARGADO POR ID

    @GetMapping("/encargados/{ide}")
    public ResponseEntity<EncargadoResponseRest> searchCategoriasById(@PathVariable Long ide){
        ResponseEntity<EncargadoResponseRest> response = service.searchById(ide);
        return response;
    }

    // GUARDAR ENCARGADO
    @PostMapping("/encargados")
    public ResponseEntity<EncargadoResponseRest> save(@RequestBody Encargado encargado){
        ResponseEntity<EncargadoResponseRest> response = service.save(encargado);
        return response;
    }

    // ACTUALIZAR ENCARGADO

    @PutMapping("/encargados/{ide}")
    public ResponseEntity<EncargadoResponseRest> update(@RequestBody Encargado encargado, @PathVariable Long ide){
        ResponseEntity<EncargadoResponseRest> response = service.update(encargado, ide);
        return response;
    }

    // ELIMINAR CATEGORIA
    @DeleteMapping("/encargados/{ide}")
    public ResponseEntity<EncargadoResponseRest> delete(@PathVariable Long ide){
        ResponseEntity<EncargadoResponseRest> response = service.deleteById(ide);
        return response;
    }


}
