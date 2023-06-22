package com.cibertec.sisgein.response;

import com.cibertec.sisgein.model.Encargado;
import lombok.Data;

import java.util.List;

@Data
public class EncargadoResponse {

    private List<Encargado> encargado;
}
