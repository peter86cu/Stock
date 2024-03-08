package com.ayalait.stock.modelo;

import javax.persistence.*;

import java.io.Serializable;



@Entity
@Table(name="prefacturas")
public class Prefactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id_prefactura;	
	private String fecha_hora;
	private int estado;
	private String id_usuario;
	private int id_plazo;
	private int id_forma_pago;
	private int id_cliente;
	private String fecha_baja;
	private int id_cotizacion_producto;
	private String fecha_hora_creado;
	public int getId_prefactura() {
		return id_prefactura;
	}
	public void setId_prefactura(int id_prefactura) {
		this.id_prefactura = id_prefactura;
	}
	public String getFecha_hora() {
		return fecha_hora;
	}
	public void setFecha_hora(String fecha_hora) {
		this.fecha_hora = fecha_hora;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getId_plazo() {
		return id_plazo;
	}
	public void setId_plazo(int id_plazo) {
		this.id_plazo = id_plazo;
	}
	public int getId_forma_pago() {
		return id_forma_pago;
	}
	public void setId_forma_pago(int id_forma_pago) {
		this.id_forma_pago = id_forma_pago;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getFecha_baja() {
		return fecha_baja;
	}
	public void setFecha_baja(String fecha_baja) {
		this.fecha_baja = fecha_baja;
	}
	public int getId_cotizacion_producto() {
		return id_cotizacion_producto;
	}
	public void setId_cotizacion_producto(int id_cotizacion_producto) {
		this.id_cotizacion_producto = id_cotizacion_producto;
	}
	public String getFecha_hora_creado() {
		return fecha_hora_creado;
	}
	public void setFecha_hora_creado(String fecha_hora_creado) {
		this.fecha_hora_creado = fecha_hora_creado;
	}
	public Prefactura() {
		super();
		// TODO Auto-generated constructor stub
	}

}
