package com.ayalait.stock.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ayalait.stock.modelo.*;

public interface ClienteJpaSpring extends JpaRepository<Cliente, Integer>{
	
	
	@Query(value="select * from clientes where nro_documento=:ci and fecha_baja is null", nativeQuery=true)
	Cliente buscarClientePorCI(@Param("ci") String ci);
}
