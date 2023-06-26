package com.cibertec.sisgein.services;

import com.cibertec.sisgein.dao.IAlmacenDao;
import com.cibertec.sisgein.dao.ICategoriaDao;
import com.cibertec.sisgein.dao.IProductoDao;
import com.cibertec.sisgein.model.Almacen;
import com.cibertec.sisgein.model.Categoria;
import com.cibertec.sisgein.model.Producto;
import com.cibertec.sisgein.response.ProductoResponseRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService{

    private ICategoriaDao categoriaDao;
    private IAlmacenDao almacenDao;
    private IProductoDao productoDao;

    public ProductoServiceImpl(ICategoriaDao categoriaDao, IAlmacenDao almacenDao, IProductoDao productoDao) {
        super();
        this.categoriaDao = categoriaDao;
        this.almacenDao = almacenDao;
        this.productoDao = productoDao;
    }

    @Override
    @Transactional
    public ResponseEntity<ProductoResponseRest> save(Producto producto, Long almcenId, Long categoriaId) {
        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();

        try{
            //BUSCAR CATEGORIA ENVIADA AL PRODUCTO
            Optional<Categoria> categoria = categoriaDao.findById(categoriaId);
            Optional<Almacen> almecen = almacenDao.findById(almcenId);

            if(categoria.isPresent() && almecen.isPresent()){
                producto.setCategoria(categoria.get());
                producto.setAlmacen(almecen.get());
            }else{
                response.setMetadata("respuesta nok","-1","Categoria o almcén no encontrados asociado al producto");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            // GUARDAR ALMACEN
            Producto productoSaved = productoDao.save(producto);

            if(productoSaved != null){
                list.add(productoSaved);
                response.getProductoResponse().setProductos(list);
                response.setMetadata("respuesta ok","00","Producto guardado");
            }else{
                response.setMetadata("respuesta nok","-1","Producto no guardado");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","Error al guardar producto");
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductoResponseRest> searchById(Long idp) {

        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();

        try{
            //BUSCAR PRODUCTO POR ID
            Optional<Producto> producto = productoDao.findById(idp);

            if(producto.isPresent()){
                list.add(producto.get());
                response.getProductoResponse().setProductos(list);
                response.setMetadata("respuesta ok","00","Producto encontrado");
            }else{
                response.setMetadata("respuesta nok","-1","Producto no encontrado");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","Error al buscar producto por ID");
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductoResponseRest> deleteyId(Long idp) {
        ProductoResponseRest response = new ProductoResponseRest();

        try{
            //ELIMINAR PRODUCTO POR ID
            productoDao.deleteById(idp);
            response.setMetadata("respuesta ok","00","Producto eliminado");

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","Error al eliminar producto");
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductoResponseRest> search() {

        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();
        List<Producto> listAux = new ArrayList<>();

        try{
            //LISTAR ALMACENES
            listAux = (List<Producto>) productoDao.findAll();

            if(listAux.size() > 0){
                listAux.stream().forEach((p) ->{
                    list.add(p);
                });

                response.getProductoResponse().setProductos(list);
                response.setMetadata("respuesta ok","00","Productos encontrados");
            }else {
                response.setMetadata("respuesta nok","-1","Productos no encontrados");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","Error al buscar los productos");
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductoResponseRest> update(Producto producto, Long almcenId, Long categoriaId, Long idp) {
        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();

        try{
            //BUSCAR CATEGORIA ENVIADA AL PRODUCTO
            Optional<Categoria> categoria = categoriaDao.findById(categoriaId);

            //BUSCAR ALMACEN ENVIADO AL PRODUCTO
            Optional<Almacen> almacen = almacenDao.findById(almcenId);

            if(categoria.isPresent() && almacen.isPresent()){
                producto.setCategoria(categoria.get());
                producto.setAlmacen(almacen.get());
            }else{
                response.setMetadata("respuesta nok","-1","Categoria o almacen no encontrados asociados al producto");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            // BUSCAR PRODUCTO PARA ACTUALIZAR
            Optional<Producto> productoSearch = productoDao.findById(idp);

            if(productoSearch.isPresent()){
                //SE ACTUALIZARA EL ALMACEN

                productoSearch.get().setNombrep(producto.getNombrep());
                productoSearch.get().setStock(producto.getStock());
                productoSearch.get().setAlmacen(producto.getAlmacen());
                productoSearch.get().setCategoria(producto.getCategoria());


                // ACTUALIZANDO ALMACEN EN LA BD
                Producto productoToUpdate = productoDao.save(productoSearch.get());

                if(productoToUpdate != null){
                    list.add(productoToUpdate);
                    response.getProductoResponse().setProductos(list);
                    response.setMetadata("respuesta ok","00","Producto actualizado");
                }else {
                    response.setMetadata("respuesta nok","-1","Producto no actualizado");
                    return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
                }

            }else{
                response.setMetadata("respuesta nok","-1","Producto no encontrado");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","Error al actualizar producto");
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }
}
