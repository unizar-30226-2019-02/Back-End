package com.ebrozon.repository;
 
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ebrozon.model.venta;

public interface ventaRepository extends CrudRepository<venta, Long>{
	
	@Modifying
	@Transactional
	@Query(value = "insert into archivosventa(archivo, nventa) values(:arc, :nv)", nativeQuery = true)
	void archivoApareceEnVenta(int arc, int nv);
	
	@Modifying
	@Transactional
	@Query(value = "delete from archivosventa where nventa = :nv", nativeQuery = true)
	void borrarArchivosVenta(int nv);
	
	List<venta> findByciudadAndActivaOrderByFechainicioDesc(String ciudad,int Activa);
	
	List<venta> findByprovinciaAndActivaOrderByFechainicioDesc(String provincia,int Activa);
	
	List<venta> findByusuarioAndActivaOrderByFechainicioDesc(String usuario, int Activa);
	
	List<venta> findByactivaOrderByFechainicioDesc(int activa);
	
	Optional<venta> findByidentificador(int identificador);
	
	List<venta> findByusuarioOrderByFechainicioDesc(String usuario);
	
	boolean existsByidentificador(int id);
	
	@Query("Select max(identificador) from venta")
	Optional<Integer> lastId();
	
	@Query(value = "Select archivo from archivosventa where nventa = :prod", nativeQuery = true)
	List<Integer> listaArchivos(int prod);
	
	@Modifying
	@Transactional
	@Query(value = "update venta set ciudad = :ci where usuario = :un", nativeQuery = true)
	void updateCityVentasUsuario(String un, String ci);
	
	@Modifying
	@Transactional
	@Query(value = "update venta set provincia = :pr where usuario = :un", nativeQuery = true)
	void updateProvinciaVentasUsuario(String un, String pr);

	void deleteByidentificador(int id);

	List<venta> findFirst25ByactivaAndIdentificadorGreaterThanOrderByFechainicioDesc(int i, int id);

	List<venta> findFirst25ByciudadAndActivaAndIdentificadorOrderByFechainicioDesc(String ci, int i, int id);

	List<venta> findFirst25ByprovinciaAndActivaAndIdentificadorOrderByFechainicioDesc(String pr, int i, int id);

	List<venta> findFirst25ByusuarioAndIdentificadorOrderByFechainicioDesc(String un, int id);

	List<venta> findFirst25ByusuarioAndIdentificadorGreaterThanOrderByFechainicioDesc(String un, int id);

	List<venta> findFirst25ByprovinciaAndActivaAndIdentificadorGreaterThanOrderByFechainicioDesc(String pr, int i,
			int id);

	List<venta> findFirst25ByciudadAndActivaAndIdentificadorGreaterThanOrderByFechainicioDesc(String ci, int i, int id);
}