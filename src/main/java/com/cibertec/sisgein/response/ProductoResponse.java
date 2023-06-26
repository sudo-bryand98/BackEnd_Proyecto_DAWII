package com.cibertec.sisgein.response;

import com.cibertec.sisgein.model.Producto;
import lombok.Data;

import java.util.List;

@Data
public class ProductoResponse {
    List<Producto> productos;

}
