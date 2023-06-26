package com.cibertec.sisgein.controller;

import com.cibertec.sisgein.model.Almacen;
import com.cibertec.sisgein.response.AlmacenResponseRest;
import com.cibertec.sisgein.services.IAlmacenService;
import com.cibertec.sisgein.util.util;
import jdk.jshell.execution.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class AlmacenRestController {


    private IAlmacenService almacenService;

    public AlmacenRestController(IAlmacenService almacenService) {
        super();
        this.almacenService = almacenService;
    }


    @PostMapping("/almacenes")
    public ResponseEntity<AlmacenResponseRest> save(@RequestParam("foto") MultipartFile foto, @RequestParam("nombalm") String nombalm, @RequestParam("direccion") String direccion,
                                                    @RequestParam("ide") Long ide) throws IOException {
        Almacen almacen = new Almacen();
        almacen.setNombalm(nombalm);
        almacen.setDireccion(direccion);
        almacen.setFoto(util.compressZLib(foto.getBytes()));

        ResponseEntity<AlmacenResponseRest> response = almacenService.save(almacen, ide);

        return response;
    }

    // OBTENER ALMACEN POR ID

    @GetMapping("/almacenes/{idal}")
    public ResponseEntity<AlmacenResponseRest> searchAlmacenById(@PathVariable Long idal){
        ResponseEntity<AlmacenResponseRest> response = almacenService.searchById(idal);
        return response;
    }

    @DeleteMapping("/almacenes/{idal}")
    public ResponseEntity<AlmacenResponseRest> deleteAlmacenById(@PathVariable Long idal){
        ResponseEntity<AlmacenResponseRest> response = almacenService.deleteyId(idal);
        return response;
    }

    @GetMapping("/almacenes")
    public ResponseEntity<AlmacenResponseRest> search(){
        ResponseEntity<AlmacenResponseRest> response = almacenService.search();
        return response;
    }

    @PutMapping("/almacenes/{idal}")
    public ResponseEntity<AlmacenResponseRest> update(@RequestParam("foto") MultipartFile foto, @RequestParam("nombalm") String nombalm,
                                                      @RequestParam("direccion") String direccion,
                                                    @RequestParam("ide") Long ide,
                                                      @PathVariable Long idal) throws IOException {
        Almacen almacen = new Almacen();
        almacen.setNombalm(nombalm);
        almacen.setDireccion(direccion);
        almacen.setFoto(util.compressZLib(foto.getBytes()));

        ResponseEntity<AlmacenResponseRest> response = almacenService.update(almacen, ide, idal);

        return response;
    }

}
