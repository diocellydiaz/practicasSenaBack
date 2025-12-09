package com.jabonesArtesanales.co.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;



@Entity
@Table(name = "envio")
public class Envios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_envio;

    private Date fechaEnvio;

    // Campos para la dirección de envío
    private String direccionCalle;
    private String direccionCiudad;
    private String direccionEstado;
    private String direccionCodigoPostal;
    private String direccionPais;

    @Enumerated(EnumType.STRING)
    private EstadoEnvio estadoEnvio;

    private String telefonoContacto;

    private Long numeroPedido;

    @ManyToOne
    @JoinColumn(name = "id_pedidos")
    private Ordenes pedido;

	public Long getId_envio() {
		return id_envio;
	}

	public void setId_envio(Long id_envio) {
		this.id_envio = id_envio;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getDireccionCalle() {
		return direccionCalle;
	}

	public void setDireccionCalle(String direccionCalle) {
		this.direccionCalle = direccionCalle;
	}

	public String getDireccionCiudad() {
		return direccionCiudad;
	}

	public void setDireccionCiudad(String direccionCiudad) {
		this.direccionCiudad = direccionCiudad;
	}

	public String getDireccionEstado() {
		return direccionEstado;
	}

	public void setDireccionEstado(String direccionEstado) {
		this.direccionEstado = direccionEstado;
	}

	public String getDireccionCodigoPostal() {
		return direccionCodigoPostal;
	}

	public void setDireccionCodigoPostal(String direccionCodigoPostal) {
		this.direccionCodigoPostal = direccionCodigoPostal;
	}

	public String getDireccionPais() {
		return direccionPais;
	}

	public void setDireccionPais(String direccionPais) {
		this.direccionPais = direccionPais;
	}

	public EstadoEnvio getEstadoEnvio() {
		return estadoEnvio;
	}

	public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public Long getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Ordenes getPedido() {
		return pedido;
	}

	public void setPedido(Ordenes pedido) {
		this.pedido = pedido;
	}

	public Object getOrdenID() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOrdenID(Object ordenID) {
		// TODO Auto-generated method stub
		
	}


	public Object getOrdenID1() {
		// TODO Auto-generated method stub
		return null;
	}
    
    

}
