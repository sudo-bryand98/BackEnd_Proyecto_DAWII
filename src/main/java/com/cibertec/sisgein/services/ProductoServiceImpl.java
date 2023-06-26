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
    public ResponseEntity<ProductoResponseRest> searchById(Long idp) {
        return null;
    }

    @Override
    public ResponseEntity<ProductoResponseRest> deleteyId(Long idp) {
        return null;
    }

    @Override
    public ResponseEntity<ProductoResponseRest> search() {
        return null;
    }

    @Override
    public ResponseEntity<ProductoResponseRest> update(Producto producto, Long almcenId, Long categoriaId) {
        return null;
    }
}
