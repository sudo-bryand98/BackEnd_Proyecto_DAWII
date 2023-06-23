package com.cibertec.sisgein.controller;

import com.cibertec.sisgein.model.Almacen;
import com.cibertec.sisgein.model.Categoria;
import com.cibertec.sisgein.response.AlmacenResponseRest;
import com.cibertec.sisgein.response.CategoriaResponseRest;
import com.cibertec.sisgein.services.IAlmacenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     *
     * @param nombalm
     * @param direccion
     * @param encargadoID
     * @return
     * @throws IOException
     */

    @PostMapping("/almacenes")
    public ResponseEntity<AlmacenResponseRest> save(@RequestParam("nombalm") String nombalm, @RequestParam("direccion") String direccion,
                                                    @RequestParam("encargadoId") Long encargadoID) throws IOException {
        Almacen almacen = new Almacen();
        almacen.setNombalm(nombalm);
        almacen.setDireccion(direccion);

        ResponseEntity<AlmacenResponseRest> response = almacenService.save(almacen, encargadoID);

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



}
