package com.cibertec.sisgein.services;

import com.cibertec.sisgein.model.Categoria;
import com.cibertec.sisgein.response.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
    public ResponseEntity<CategoriaResponseRest> search();

    public ResponseEntity<CategoriaResponseRest> searchById(Long id);

    public ResponseEntity<CategoriaResponseRest> save(Categoria categoria);
}
