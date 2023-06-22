package com.cibertec.sisgein.services;

import com.cibertec.sisgein.dao.IEncargadoDao;
import com.cibertec.sisgein.model.Encargado;
import com.cibertec.sisgein.response.EncargadoResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EncargadoServiceImpl implements IEncargadoService {

    @Autowired
    private IEncargadoDao encargadoDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<EncargadoResponseRest> search() {
        EncargadoResponseRest response = new EncargadoResponseRest();

        try {
            List<Encargado> encargado = (List<Encargado>) encargadoDao.findAll();

            response.getEncargadoResponse().setEncargado(encargado);

            response.setMetadata("Respuesta ok","00","Respuesta exitosa");

        } catch (Exception e){
            response.setMetadata("Respuesta nok","-1","Error al consultar");
            e.getStackTrace();
            return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<EncargadoResponseRest> searchById(Long ide) {
        EncargadoResponseRest response = new EncargadoResponseRest();
        List<Encargado> list = new ArrayList<>();

        try {

            Optional<Encargado> encargado = encargadoDao.findById(ide);
            if(encargado.isPresent()){
                list.add(encargado.get());
                response.getEncargadoResponse().setEncargado(list);
                response.setMetadata("Respuesta ok","00","Encargado encontrado");
            }else {
                response.setMetadata("Respuesta nok","-1","Encargado no encontrado");
                return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e){
            response.setMetadata("Respuesta nok","-1","Error al consultar por id");
            e.getStackTrace();
            return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<EncargadoResponseRest> save(Encargado encargado) {
        EncargadoResponseRest response = new EncargadoResponseRest();
        List<Encargado> list = new ArrayList<>();

        try {

            Encargado encargadoSaved = encargadoDao.save(encargado);

            if(encargadoSaved!=null){
                list.add(encargadoSaved);
                response.getEncargadoResponse().setEncargado(list);
                response.setMetadata("Respuesta ok","00","Encargado guardado");
            } else{
                response.setMetadata("Respuesta nok","-1","Encargado no guardado");
                return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e){
            response.setMetadata("Respuesta nok","-1","Error al grabar encargado");
            e.getStackTrace();
            return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<EncargadoResponseRest> update(Encargado encargado, Long ide) {
        EncargadoResponseRest response = new EncargadoResponseRest();
        List<Encargado> list = new ArrayList<>();

        try {

            Optional<Encargado> encargadoSearch = encargadoDao.findById(ide);

            if(encargadoSearch.isPresent()){
                //SE ACTUALIZARÁ EL REGISTRO
                encargadoSearch.get().setNombre(encargado.getNombre());
                encargadoSearch.get().setApellidos(encargado.getApellidos());
                encargadoSearch.get().setCel(encargado.getCel());
                encargadoSearch.get().setCorreo(encargado.getCorreo());

                Encargado encargadoToUpdate = encargadoDao.save(encargadoSearch.get());

                if(encargadoToUpdate != null){
                    list.add(encargadoToUpdate);
                    response.getEncargadoResponse().setEncargado(list);
                    response.setMetadata("Respuesta ok","00","Encargado actualizado");
                }else{
                    response.setMetadata("Respuesta nok","-1","Encargado no actualizado");
                    return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.BAD_REQUEST);
                }

            } else{
                response.setMetadata("Respuesta nok","-1","Encargado no encontrada");
                return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e){
            response.setMetadata("Respuesta nok","-1","Error al grabar encargado");
            e.getStackTrace();
            return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<EncargadoResponseRest> deleteById(Long ide) {
        EncargadoResponseRest response = new EncargadoResponseRest();

        try {

            encargadoDao.deleteById(ide);
            response.setMetadata("Respuesta ok","00","Encargado eliminado");

        } catch (Exception e){
            response.setMetadata("Respuesta nok","-1","Error al eliminar");
            e.getStackTrace();
            return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EncargadoResponseRest>(response, HttpStatus.OK);
    }
}
