package com.jabonesArtesanales.co.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name="envio")
public class Envios implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_envio;
	
	private Date fechaEnvio;
	 @ManyToOne
	  @JoinColumn(name = "id_direccionEnvio")
	private DireccionEnvio  direccionEnvio;
	 @Enumerated(EnumType.STRING)
	private EstadoEnvio estadoEnvio;
	private String telefonoContacto;
	private long numeroPedido;
	
	
	
	public Date getFechaEnvio() {
		return fechaEnvio;
	}



	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}



	public DireccionEnvio getDireccionEnvio() {
		return direccionEnvio;
	}



	public void setDireccionEnvio(DireccionEnvio direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
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



	public long getNumeroPedido() {
		return numeroPedido;
	}



	public void setNumeroPedido(long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @ManyToOne
	    @JoinColumn(name = "id_pedidos")
	    private Ordenes pedido;

		public Ordenes getPedido() {
			return pedido;
		}

		public void setPedido(Ordenes pedido) {
			this.pedido = pedido;
		}


}
