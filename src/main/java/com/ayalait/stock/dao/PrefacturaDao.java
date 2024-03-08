package com.ayalait.stock.dao;




import java.util.List;

import org.springframework.stereotype.Component;

import com.ayalait.stock.modelo.*;





@Component
public interface PrefacturaDao {
	
	void crearPrefactura(Prefactura orden);
	int obtenerNumeroPrefactura(String fecha, String idUsuario);
	Prefactura obtenerPrefacturaPorId(int id);
	void guardarDetallePrefactura(PrefacturaDetalle detalle);
	PrefacturaDetalle recuperarDetallePrefacturaID(int id);
	void eliminarDetallePrefcatura(int id);
	List<Object> listadoPrefactura();
	void guardarModificacionPrefactura(PrefacturaModificaciones prefactura);
	List<Object> listadoPrefacturasAprobadas();
	
	PrefacturaModificaciones obtenerModificacionPrefacturaId(int idPrefactura);
	

}
