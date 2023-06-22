package com.cibertec.sisgein.services;

import com.cibertec.sisgein.model.Categoria;
import com.cibertec.sisgein.response.CategoriaResponseRest;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;


public interface ICategoriaService {
    public ResponseEntity<CategoriaResponseRest> search();

    public ResponseEntity<CategoriaResponseRest> searchById(Long id);

    public ResponseEntity<CategoriaResponseRest> save(Categoria categoria);

    public ResponseEntity<CategoriaResponseRest> update(Categoria categoria, Long id);
}
