package com.cibertec.sisgein.services;

import com.cibertec.sisgein.dao.IAlmacenDao;
import com.cibertec.sisgein.dao.IEncargadoDao;
import com.cibertec.sisgein.model.Almacen;
import com.cibertec.sisgein.model.Encargado;
import com.cibertec.sisgein.response.AlmacenResponseRest;
import com.cibertec.sisgein.response.ResponseRest;
import com.cibertec.sisgein.util.util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlmacenServiceImpl implements IAlmacenService{

    private IEncargadoDao encargadoDao;
    private IAlmacenDao almacenDao;

    public AlmacenServiceImpl(IEncargadoDao encargadoDao, IAlmacenDao almacenDao) {
        super();
        this.encargadoDao = encargadoDao;
        this.almacenDao = almacenDao;
    }



    @Override
    @Transactional
    public ResponseEntity<AlmacenResponseRest> save(Almacen almacen, Long encargadoId) {

        AlmacenResponseRest response = new AlmacenResponseRest();
        List<Almacen> list = new ArrayList<>();

        try{
            //BUSCAR ENCARGADO ENVIADO AL ALMACEN
            Optional<Encargado> encargado = encargadoDao.findById(encargadoId);

            if(encargado.isPresent()){
                almacen.setEncargado(encargado.get());
            }else{
                response.setMetadata("respuesta nok","-1","Encargado no encontrado asociado al almacen");
                return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            // GUARDAR ALMACEN
            Almacen almacenSaved = almacenDao.save(almacen);

            if(almacenSaved != null){
                list.add(almacenSaved);
                response.getAlmacenResponse().setAlmacens(list);
                response.setMetadata("respuesta ok","00","Almacen guardado");
            }else{
                response.setMetadata("respuesta nok","-1","Almacen no guardado");
                return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","Error al guardar almacen");
            return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<AlmacenResponseRest> searchById(Long idal) {
        AlmacenResponseRest response = new AlmacenResponseRest();
        List<Almacen> list = new ArrayList<>();

        try{
            //BUSCAR ALMACEN POR ID
            Optional<Almacen> almacen = almacenDao.findById(idal);

            if(almacen.isPresent()){

                byte[] imageDescompressed = util.decompressZLib(almacen.get().getFoto());
                almacen.get().setFoto(imageDescompressed);

                list.add(almacen.get());
                response.getAlmacenResponse().setAlmacens(list);
                response.setMetadata("respuesta ok","00","Almacen encontrado");
            }else{
                response.setMetadata("respuesta nok","-1","Almacen no encontrado");
                return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","Error al buscar almacen por ID");
            return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<AlmacenResponseRest> deleteyId(Long idal) {
        AlmacenResponseRest response = new AlmacenResponseRest();

        try{
            //ELIMINAR ALMACEN POR ID
            almacenDao.deleteById(idal);
            response.setMetadata("respuesta ok","00","Almacen eliminado");

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","Error al eliminar almacen");
            return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<AlmacenResponseRest> search() {
        AlmacenResponseRest response = new AlmacenResponseRest();
        List<Almacen> list = new ArrayList<>();
        List<Almacen> listAux = new ArrayList<>();

        try{
            //LISTAR ALMACENES
            listAux = (List<Almacen>) almacenDao.findAll();

            if(listAux.size() > 0){
                listAux.stream().forEach((p) ->{
                    byte[] imageDescompressed = util.decompressZLib(p.getFoto());
                    list.add(p);
                });

                response.getAlmacenResponse().setAlmacens(list);
                response.setMetadata("respuesta ok","00","Almacenes encontrados");
            }else {
                response.setMetadata("respuesta nok","-1","Almacenes no encontrados");
                return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","Error al buscar los almacenes");
            return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<AlmacenResponseRest> update(Almacen almacen, Long encargadoId, Long idal) {
        AlmacenResponseRest response = new AlmacenResponseRest();
        List<Almacen> list = new ArrayList<>();

        try{
            //BUSCAR ENCARGADO ENVIADO AL ALMACEN
            Optional<Encargado> encargado = encargadoDao.findById(encargadoId);

            if(encargado.isPresent()){
                almacen.setEncargado(encargado.get());
            }else{
                response.setMetadata("respuesta nok","-1","Encargado no encontrado asociado al almacen");
                return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            // BUSCAR ALMACEN PARA ACTUALIZAR
            Optional<Almacen> almacenSearch = almacenDao.findById(idal);

            if(almacenSearch.isPresent()){
                //SE ACTUALIZARA EL ALMACEN

                almacenSearch.get().setNombalm(almacen.getNombalm());
                almacenSearch.get().setDireccion(almacen.getDireccion());
                almacenSearch.get().setEncargado(almacen.getEncargado());
                almacenSearch.get().setFoto(almacen.getFoto());


                // ACTUALIZANDO ALMACEN EN LA BD
                Almacen almacenToUpdate = almacenDao.save(almacenSearch.get());

                if(almacenToUpdate != null){
                    list.add(almacenToUpdate);
                    response.getAlmacenResponse().setAlmacens(list);
                    response.setMetadata("respuesta ok","00","Almacen actualizado");
                }else {
                    response.setMetadata("respuesta nok","-1","Almacen no actualizado");
                    return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.BAD_REQUEST);
                }

            }else{
                response.setMetadata("respuesta nok","-1","Almacen no encontrado");
                return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","Error al actualizar almacen");
            return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AlmacenResponseRest>(response, HttpStatus.OK);
    }
}
