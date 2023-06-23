package com.cibertec.sisgein.dao;

import com.cibertec.sisgein.model.Almacen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAlmacenDao extends CrudRepository<Almacen, Long> {

//    @Query(" select a from tb_almacen a where a.nombalm like %?1% ")
//    List<Almacen> findByNameLike(String nombalm);
//
//    List<Almacen> findByNameContainingIngnoreCase(String nombalm);

}
