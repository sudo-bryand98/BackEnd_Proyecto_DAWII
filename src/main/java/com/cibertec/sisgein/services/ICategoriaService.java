package com.cibertec.sisgein.services;

import com.cibertec.sisgein.response.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
    public ResponseEntity<CategoriaResponseRest> search();
}
