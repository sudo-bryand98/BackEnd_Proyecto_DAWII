package com.cibertec.sisgein.controller;

import com.cibertec.sisgein.model.Categoria;
import com.cibertec.sisgein.response.CategoriaResponseRest;
import com.cibertec.sisgein.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoriaRestController {

    @Autowired
    private ICategoriaService service;

    // OBTENER TODAS LAS CATEGORIAS

    @GetMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> searchCategorias(){
        ResponseEntity<CategoriaResponseRest> response = service.search();
        return response;
    }

    // OBTENER CATEGORIA POR ID

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> searchCategoriasById(@PathVariable Long id){
        ResponseEntity<CategoriaResponseRest> response = service.searchById(id);
        return response;
    }

    // GUARDAR CATEGORIA

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> save(@RequestBody Categoria categoria){
        ResponseEntity<CategoriaResponseRest> response = service.save(categoria);
        return response;
    }

    // ACTUALIZAR CATEGORIA

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> update(@RequestBody Categoria categoria, @PathVariable Long id){
        ResponseEntity<CategoriaResponseRest> response = service.update(categoria, id);
        return response;
    }

    // ELIMINAR CATEGORIA
    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> delete(@PathVariable Long id){
        ResponseEntity<CategoriaResponseRest> response = service.deleteById(id);
        return response;
    }

}
