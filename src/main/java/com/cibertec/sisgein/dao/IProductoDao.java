package com.cibertec.sisgein.dao;

import com.cibertec.sisgein.model.Producto;
import org.springframework.data.repository.CrudRepository;

public interface IProductoDao extends CrudRepository<Producto, Long>{
}
