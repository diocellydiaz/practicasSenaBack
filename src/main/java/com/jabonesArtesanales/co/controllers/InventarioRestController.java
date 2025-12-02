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

import com.jabonesArtesanales.co.entity.Envios;
import com.jabonesArtesanales.co.entity.Inventario;
import com.jabonesArtesanales.co.services.IInventarioService;

import jakarta.validation.Valid;

@CrossOrigin(origins= {"https://jabonesartesanales_up.railway.app","http://localhost:4200"})
@RestController
@RequestMapping(value="/api")
public class InventarioRestController {
	
	@Autowired
	private IInventarioService inventarioService;
	
	@GetMapping("/inventario")
	public List<Inventario>  findAll(){
		return inventarioService.findAll();
	}
	
	@GetMapping("/inventario/{id}")
	public  ResponseEntity<?> show(@PathVariable Long id){
		Inventario inventario = null;
		
			Map<String, Object> response = new HashMap<>();
		
		try {
			inventario = inventarioService.findById(id);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(inventario == null) {
			response.put("mensaje", "El envío con el ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Inventario>(inventario, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/inventario")
	public ResponseEntity<?> create(@Valid @RequestBody Inventario inventario, BindingResult result){
		Inventario inventarioNew = null;
		
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
			inventarioNew = inventarioService.save(inventario);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar el insert en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El inventario  ha sido creado con éxito");
		response.put("envio", inventarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	
		
	}
	
	@PutMapping("/inventario/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Inventario inventario, BindingResult result, @PathVariable Long id) {

	    Map<String, Object> response = new HashMap<>();
	    Inventario inventarioActual = inventarioService.findById(id);
	    Inventario inventarioUpdate = null;

	    if (result.hasErrors()) {
	        List<String> errors = result.getFieldErrors()
	                .stream()
	                .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
	                .collect(Collectors.toList());

	        response.put("errors", errors);
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	    if (inventarioActual == null) {
	        response.put("mensaje", "Error: no se puede editar, el inventario con el ID: " + id + " no existe en la base de datos!");
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    try {
	        // ✅ Actualizar solo los campos deseados
	        inventarioActual.setStock(inventario.getStock());
	

	        inventarioUpdate = inventarioService.save(inventarioActual);

	    } catch (DataAccessException ex) {
	        response.put("mensaje", "Error al actualizar en la Base de Datos");
	        response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    response.put("mensaje", "El inventario se ha actualizado con éxito");
	    response.put("inventario", inventarioUpdate);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/inventario/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		
		try {
			inventarioService.deleteById(id);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al eliminar en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El envío ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
