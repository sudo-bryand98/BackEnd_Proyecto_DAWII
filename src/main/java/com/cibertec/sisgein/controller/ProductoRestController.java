package com.cibertec.sisgein.controller;

import com.cibertec.sisgein.model.Almacen;
import com.cibertec.sisgein.model.Producto;
import com.cibertec.sisgein.response.AlmacenResponseRest;
import com.cibertec.sisgein.response.ProductoResponseRest;
import com.cibertec.sisgein.services.IProductoService;
import com.cibertec.sisgein.util.util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductoRestController {

    private IProductoService productoService;

    public ProductoRestController(IProductoService productoService) {
        super();
        this.productoService = productoService;
    }

    @PostMapping("/productos")
    public ResponseEntity<ProductoResponseRest> save(@RequestParam("nombrep") String nombrep,
                                                     @RequestParam("stock") Long stock,
                                                     @RequestParam("idal") Long idal,
                                                     @RequestParam("id") Long id) throws IOException{
        Producto producto = new Producto();
        producto.setNombrep(nombrep);
        producto.setStock(stock);
        ResponseEntity<ProductoResponseRest> response = productoService.save(producto, idal, id);

        return response;
    }
}
