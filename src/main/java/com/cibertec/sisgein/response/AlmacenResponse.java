package com.cibertec.sisgein.response;

import com.cibertec.sisgein.model.Almacen;
import lombok.Data;

import java.util.List;

@Data
public class AlmacenResponse {

    List<Almacen> almacens;
}
