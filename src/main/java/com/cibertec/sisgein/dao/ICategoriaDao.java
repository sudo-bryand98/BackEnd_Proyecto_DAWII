package com.cibertec.sisgein.dao;

import com.cibertec.sisgein.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {
}
