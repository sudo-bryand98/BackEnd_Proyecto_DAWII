package com.cibertec.sisgein.response;

import com.cibertec.sisgein.model.Categoria;
import lombok.Data;

import java.util.List;

@Data
public class CategoriaResponse {

    private List<Categoria> categoria;
}
