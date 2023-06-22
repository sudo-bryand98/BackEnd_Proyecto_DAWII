package com.cibertec.sisgein.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_encargado")
public class Encargado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ide;
    private String nombre;

    private String apellidos;

    private String cel;

    private String correo;
}
