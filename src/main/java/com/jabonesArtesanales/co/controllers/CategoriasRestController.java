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

import com.jabonesArtesanales.co.entity.Categorias;
import com.jabonesArtesanales.co.services.ICategoriasService;

import jakarta.validation.Valid;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping(value="/api")
public class CategoriasRestController {

	@Autowired
	private ICategoriasService categoriasService;
	
	@GetMapping("/categorias")
	public List<Categorias> index(){
		return categoriasService.findAll();
	}
	
	@GetMapping("/categorias/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		Categorias categorias = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			categorias = categoriasService.findById(id);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(categorias == null) {
			response.put("mensaje", "La categoria ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Categorias>(categorias, HttpStatus.OK);
	}
	
	@PostMapping("/categorias")
	public ResponseEntity<?> create(@Valid @RequestBody Categorias categorias, BindingResult result){
		
		Categorias categoriasNew = null;
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
			categoriasNew = categoriasService.save(categorias);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar el insert en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La categoria ha sido creada con exito");
		response.put("categorias", categoriasNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/categorias/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Categorias categorias, BindingResult result, @PathVariable Long id){
		Categorias categoriaActual = categoriasService.findById(id);
		Categorias categoriasUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(categoriaActual == null) {
			response.put("mensaje", "Error: no se puede editar, la categoria ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			categoriaActual.setNombre(categorias.getNombre());
			categoriaActual.setDescripcion(categorias.getDescripcion());
			categoriaActual.setProducto(categorias.getProducto());
			
			categoriasUpdate = categoriasService.save(categoriaActual);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al actualizar en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La categoria se ha actualizado con exito");
		response.put("categorias", categoriasUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			categoriasService.delete(id);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al eliminar en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La categoria se ha eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}