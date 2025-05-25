package com.jabonesArtesanales.co.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jabonesArtesanales.co.entity.Ordenes;
import com.jabonesArtesanales.co.services.IOrdenesService;

import jakarta.validation.Valid;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping(value="/api")
public class OrdenesRestController {

	@Autowired
	private IOrdenesService pedidosService;
	
	@GetMapping("/pedidos")
	public List<Ordenes> index(){
		return pedidosService.findAll();
	}
	
	@GetMapping("/pedidos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		Ordenes pedidos = null;
		Map<String, Object> response= new HashMap<>();
		
		try {
			pedidos = pedidosService.findById(id);
		}catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta en la Base de Datos");
			response.put("mensaje", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(pedidos == null) {
			response.put("mensaje", "El pedido con el  ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Ordenes>(pedidos,HttpStatus.OK);
	}
	
	
	@SuppressWarnings("null")
	@PostMapping("/pedidos")
	public ResponseEntity<?> create(@Valid @RequestBody Ordenes pedidos, BindingResult result){
		
		Ordenes pedidosNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
	        List<String> errors = result.getFieldErrors()
	                .stream()
	                .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
	                .collect(Collectors.toList());

	        response.put("errors", errors);
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
		
		try {
			pedidosNew.setNumeroPedido(pedidos.getNumeroPedido());
			pedidosNew = pedidosService.save(pedidos);
		}catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar el insert en la Base de Datos");
			response.put("mensaje", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El pedido se ha creado con exito");
		response.put("Pedidos", pedidosNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/pedidos/{id}")
	public ResponseEntity<?>update(@Valid @RequestBody Ordenes pedidos, BindingResult result ,@PathVariable Long id){
		Ordenes pedidosActual = pedidosService.findById(id);
		Ordenes pedidosUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(pedidosActual == null) {
			response.put("mensaje", "Error : no se puede editar,  el pedido con ID: ".concat(id.toString().concat(" No existe en la base de Datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			pedidosActual.setCliente(pedidos.getCliente());
			pedidosActual.setEnvios(pedidos.getEnvios());
			pedidosActual.setEstado(pedidos.getEstado());
			pedidosActual.setFechaPedido(pedidos.getFechaPedido());
			pedidosActual.setMetodoPago(pedidos.getMetodoPago());
			pedidosActual.setProductos(pedidos.getProductos());
			
			pedidosUpdate = pedidosService.save(pedidosActual);
		}catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar al actualizar en la Base de Datos");
			response.put("mensaje", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El pedido se ha actualizado con extio!!");
		response.put("Pedido",pedidosUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/pedidos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			pedidosService.delete(id);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar al borrar en la Base de Datos");
			response.put("mensaje", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El pedido se ha eliminado con exito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
