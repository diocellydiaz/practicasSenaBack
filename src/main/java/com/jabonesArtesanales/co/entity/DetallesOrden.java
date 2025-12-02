package com.jabonesArtesanales.co.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detalles_orden")
public class DetallesOrden implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @Enumerated(EnumType.STRING)
    private EstadoDetalleOrden estado;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonBackReference
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Ordenes orden;

}
