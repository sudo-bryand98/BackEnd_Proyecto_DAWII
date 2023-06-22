package com.cibertec.sisgein.dao;

import com.cibertec.sisgein.model.Almacen;
import org.springframework.data.repository.CrudRepository;

public interface IAlmacenDao extends CrudRepository<Almacen, Long> {
}
