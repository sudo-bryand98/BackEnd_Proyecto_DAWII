package com.cibertec.sisgein.services;

import com.cibertec.sisgein.model.Producto;
import com.cibertec.sisgein.response.ProductoResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductoService {
    public ResponseEntity<ProductoResponseRest> save(Producto producto, Long almcenId, Long categoriaId);
    public ResponseEntity<ProductoResponseRest> searchById(Long idp);
    public ResponseEntity<ProductoResponseRest> deleteyId(Long idp);
    public ResponseEntity<ProductoResponseRest> search();
    public ResponseEntity<ProductoResponseRest> update(Producto producto, Long almcenId, Long categoriaId, Long idp);
}
