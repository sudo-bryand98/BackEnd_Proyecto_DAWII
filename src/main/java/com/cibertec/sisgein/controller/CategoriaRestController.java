package com.cibertec.sisgein.controller;

import com.cibertec.sisgein.response.CategoriaResponseRest;
import com.cibertec.sisgein.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoriaRestController {

    @Autowired
    private ICategoriaService service;

    @GetMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> searchCategorias(){
        ResponseEntity<CategoriaResponseRest> response = service.search();
        return response;
    }


}
