package com.ayalait.stock.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import com.ayalait.stock.modelo.*;
import com.ayalait.stock.dao.*;
import com.ayalait.utils.ErrorState;
import com.ayalait.utils.OCAprobadas;
import com.ayalait.utils.ResponsePrefactura;

@Service
public class PrefacturaServiceImpl implements PrefacturaService {

	private static final String RESULTADO_OK = "Datos guardado ok.";
	public static final String DELETE_OK="Registro eliminado correctamente.";
	public static final String DELETE_NOK="El registro no existe.";
	public static final String ITEMS_OK="Producto agregado.";
	
	@Autowired
	PrefacturaDao daoPrefactura;

	ErrorState error= new ErrorState();

	
	@Override
	public ResponseEntity<String> crearPrefactura(String pre) {
		try {
			Prefactura request = new Gson().fromJson(pre, Prefactura.class);
			daoPrefactura.crearPrefactura(request);
			return new ResponseEntity<String>(RESULTADO_OK,HttpStatus.OK);
		} catch (Exception e) {
			error.setCode(90020);
			error.setMenssage(e.getCause().getMessage());
			return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> obtenerPrefacturaPorId(int id) {
		try {
			Prefactura orden=daoPrefactura.obtenerPrefacturaPorId(id);
			if(orden!=null) {
				return new ResponseEntity<String>(new Gson().toJson(orden), HttpStatus.OK);
			}else {
				error.setCode(9000);
				error.setMenssage("No existen la prefactura con id: "+id);
				return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			error.setCode(9010);
			error.setMenssage(e.getCause().getMessage());
			return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.NOT_ACCEPTABLE);

		}
	}

	@Override
	public ResponseEntity<String> guardarDetallePrefactura(String detalle) {
		try {
			PrefacturaDetalle request = new Gson().fromJson(detalle, PrefacturaDetalle.class);
			daoPrefactura.guardarDetallePrefactura(request);
			return new ResponseEntity<String>(ITEMS_OK,HttpStatus.OK);
		} catch (Exception e) {
			error.setCode(90020);
			error.setMenssage(e.getCause().getCause().getMessage());
			return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> eliminarDetallePrefactura(int id) {
		try {
			if(daoPrefactura.recuperarDetallePrefacturaID(id)!=null) {
				daoPrefactura.eliminarDetallePrefcatura(id);
				return new ResponseEntity<String>(DELETE_OK,HttpStatus.OK);
			}
			error.setCode(90002);
			error.setMenssage(DELETE_NOK);
			return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			error.setCode(90020);
			error.setMenssage(e.getCause().getCause().getMessage());
			return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> obtenerNumeroPrefactura(String fecha, String idUsuario) {
		try {
			int resultado= daoPrefactura.obtenerNumeroPrefactura(fecha, idUsuario);
			return new ResponseEntity<String>(String.valueOf(resultado),HttpStatus.OK);
			
		} catch (Exception e) {
			error.setCode(90020);
			error.setMenssage(e.getCause().getMessage());
			return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> listadoPrefacturas() {
		try {
			Iterator<Object> lst = daoPrefactura.listadoPrefactura().iterator();
			List<ResponsePrefactura> lstPrefact= new ArrayList<ResponsePrefactura>();
			if(lst != null && lst.hasNext()) {
				while( lst.hasNext()) {
					Object[] objArray = (Object[]) lst.next();
					ResponsePrefactura response= new ResponsePrefactura();
					   response.setId_prefactura(objArray[0].toString());
					   response.setFecha(objArray[1].toString());					  
					   response.setPlazo(objArray[2].toString());
					   response.setId_moneda(objArray[3].toString());
					   response.setItems(objArray[4].toString());
					   response.setEstado(objArray[5].toString());
					   response.setId_estado(objArray[6].toString());
					   response.setNombre(objArray[7].toString());
					   response.setCliente(objArray[8].toString());
					   response.setId_cliente(Integer.parseInt(objArray[9].toString()) );
					   lstPrefact.add(response);
				}
				return new ResponseEntity<String>(new Gson().toJson(lstPrefact),HttpStatus.OK);
			}else {
				error.setCode(90002);
				error.setMenssage("No hay registros de prefacturas.");
				return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.BAD_GATEWAY);
			}
		} catch (Exception e) {
			error.setCode(90020);
			error.setMenssage(e.getCause().getMessage());
			return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> salvarModificaciones(String datos) {
		try {
			PrefacturaModificaciones request = new Gson().fromJson(datos, PrefacturaModificaciones.class);
			daoPrefactura.guardarModificacionPrefactura(request);
			return new ResponseEntity<String>(RESULTADO_OK,HttpStatus.OK);
		} catch (Exception e) {
			error.setCode(90020);
			error.setMenssage(e.getCause().getMessage());
			return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> listadoPrefacturasAprobadas() {
		try {
			Iterator<Object> lst = daoPrefactura.listadoPrefacturasAprobadas().iterator();
			List<OCAprobadas> lstPrefact= new ArrayList<OCAprobadas>();
			if(lst != null && lst.hasNext()) {
				while( lst.hasNext()) {
					Object[] objArray = (Object[]) lst.next();
					 OCAprobadas items = new OCAprobadas();
					   items.setId(Integer.parseInt(objArray[0].toString())) ;
					   items.setNombre(objArray[1].toString());
					   lstPrefact.add(items);
				}
				return new ResponseEntity<String>(new Gson().toJson(lstPrefact),HttpStatus.OK);
			}else {
				error.setCode(90002);
				error.setMenssage("No hay registros de modificación en prefacturas.");
				return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.BAD_GATEWAY);
			}
		} catch (Exception e) {
			error.setCode(90020);
			error.setMenssage(e.getCause().getMessage());
			return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> obtenerModificacionPrefacturaId(int idPrefactura) {
		try {
			PrefacturaModificaciones response= daoPrefactura.obtenerModificacionPrefacturaId(idPrefactura);
			if(response!=null) {
				return new ResponseEntity<String>(new Gson().toJson(response),HttpStatus.OK);

			}else {
				error.setCode(90004);
				error.setMenssage("No se encontraron actualizaciones de la prefactura #: "+ idPrefactura);
				return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.BAD_GATEWAY);

			}
			
		} catch (Exception e) {
			error.setCode(90020);
			error.setMenssage(e.getCause().getMessage());
			return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	

}
