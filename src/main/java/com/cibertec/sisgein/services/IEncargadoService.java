package com.cibertec.sisgein.services;

import com.cibertec.sisgein.model.Encargado;
import com.cibertec.sisgein.response.EncargadoResponseRest;
import org.springframework.http.ResponseEntity;

public interface IEncargadoService {

    public ResponseEntity<EncargadoResponseRest> search();

    public ResponseEntity<EncargadoResponseRest> searchById(Long ide);

    public ResponseEntity<EncargadoResponseRest> save(Encargado encargado);

    public ResponseEntity<EncargadoResponseRest> update(Encargado encargado, Long ide);

    public ResponseEntity<EncargadoResponseRest> deleteById(Long ide);

}
