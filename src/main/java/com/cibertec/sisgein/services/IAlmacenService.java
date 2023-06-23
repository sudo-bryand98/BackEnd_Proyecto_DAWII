package com.cibertec.sisgein.services;

import com.cibertec.sisgein.model.Almacen;
import com.cibertec.sisgein.response.AlmacenResponseRest;
import org.springframework.http.ResponseEntity;

public interface IAlmacenService {
    public ResponseEntity<AlmacenResponseRest> save(Almacen almacen, Long encargadoId);
    public ResponseEntity<AlmacenResponseRest> searchById(Long idal);
    public ResponseEntity<AlmacenResponseRest> deleteyId(Long idal);
    public ResponseEntity<AlmacenResponseRest> search();
}
