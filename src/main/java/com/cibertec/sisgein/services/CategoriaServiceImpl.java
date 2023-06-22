package com.cibertec.sisgein.services;

import com.cibertec.sisgein.dao.ICategoriaDao;
import com.cibertec.sisgein.model.Categoria;
import com.cibertec.sisgein.response.CategoriaResponseRest;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> searchById(Long id) {
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();

        try {

            Optional<Categoria> categoria = categoriaDao.findById(id);
            if(categoria.isPresent()){
                list.add(categoria.get());
                response.getCategoriaResponse().setCategoria(list);
                response.setMetadata("Respuesta ok","00","Categoria encontrada");
            }else {
                response.setMetadata("Respuesta nok","-1","Categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e){
            response.setMetadata("Respuesta nok","-1","Error al consultar por id");
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> save(Categoria categoria) {

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();

        try {

            Categoria categoriaSaved = categoriaDao.save(categoria);

            if(categoriaSaved!=null){
                list.add(categoriaSaved);
                response.getCategoriaResponse().setCategoria(list);
                response.setMetadata("Respuesta ok","00","Categoria guardada");
            } else{
                response.setMetadata("Respuesta nok","-1","Categoria no guardada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e){
            response.setMetadata("Respuesta nok","-1","Error al grabar categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoriaResponseRest> update(Categoria categoria, Long id) {
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();

        try {

           Optional<Categoria> categoriaSearch = categoriaDao.findById(id);

           if(categoriaSearch.isPresent()){
               //SE ACTUALIZARÁ EL REGISTRO
                categoriaSearch.get().setNombreCat(categoria.getNombreCat());

                Categoria categoriaToUpdate = categoriaDao.save(categoriaSearch.get());

                if(categoriaToUpdate != null){
                    list.add(categoriaToUpdate);
                    response.getCategoriaResponse().setCategoria(list);
                    response.setMetadata("Respuesta ok","00","Categoria actualizada");
                }else{
                    response.setMetadata("Respuesta nok","-1","Categoria no actualizada");
                    return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
                }

           } else{
               response.setMetadata("Respuesta nok","-1","Categoria no encontrada");
               return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
           }

        } catch (Exception e){
            response.setMetadata("Respuesta nok","-1","Error al grabar categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }


}
