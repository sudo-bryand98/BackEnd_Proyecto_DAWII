package com.cibertec.sisgein.controller;

import com.cibertec.sisgein.response.CategoriaResponseRest;
import com.cibertec.sisgein.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
