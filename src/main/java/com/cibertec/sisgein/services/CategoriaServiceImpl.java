package com.cibertec.sisgein.services;

import com.cibertec.sisgein.dao.ICategoriaDao;
import com.cibertec.sisgein.model.Categoria;
import com.cibertec.sisgein.response.CategoriaResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService{

    @Autowired
    private ICategoriaDao categoriaDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> search() {
        CategoriaResponseRest response = new CategoriaResponseRest();

        try {
            List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();

            response.getCategoriaResponse().setCategoria(categoria);

            response.setMetadata("Respuesta ok","00","Respuesta exitosa");

        } catch (Exception e){
            response.setMetadata("Respuesta nok","-1","Error al consultar");
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }
}
