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

import com.jabonesArtesanales.co.entity.DireccionEnvio;
import com.jabonesArtesanales.co.services.IDireccionEnvioService;

import jakarta.validation.Valid;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping(value="/api")
public class DireccionEnviosRestController {
	
	@Autowired
	private IDireccionEnvioService direccionEnvioService;
	
	@GetMapping("/direccionEnvio")
	public List<DireccionEnvio> index(){
		return direccionEnvioService.findAll();
	}
	
	@GetMapping("/direccionEnvio/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		DireccionEnvio direccioenvio = null; 
		Map<String, Object> response= new HashMap<>();
		
		
		try {
			direccioenvio = direccionEnvioService.findById(id);
		}catch(DataAccessException ex){
			response.put("mensaje", "Error al realizar la consulta en la Base de Datos");
			response.put("mensaje", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		if(direccioenvio == null) {
			response.put("mensaje", "La direccion de envio con el ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<DireccionEnvio>(direccioenvio,HttpStatus.OK);
		
	}
	
	@PostMapping("/direccionEnvio")
	public ResponseEntity<?> create(@Valid @RequestBody DireccionEnvio direccioenvio, BindingResult result){
		
		DireccionEnvio direccioenvioNew = null;
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
			direccioenvioNew = direccionEnvioService.save(direccioenvio);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar el insert en la Base de Datos");
			response.put("mensaje", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El direccion envio ha sido creado con exito");
		response.put("direccion envio",direccioenvioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/direccionEnvio/{id}")
	public ResponseEntity<?>update(@Valid @RequestBody DireccionEnvio direccioenvio, BindingResult result,@PathVariable Long id ){
		DireccionEnvio dirreccionEnvioActual = direccionEnvioService.findById(id);
		DireccionEnvio dirreccionEnvioUptade = null;
		
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(dirreccionEnvioActual == null) {
			response.put("mensaje", "Error: No se puede editar, la Direccion de envio con el ID: ".concat(id.toString().concat(" No existe en la base de Datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			dirreccionEnvioActual.setPais(direccioenvio.getPais());
			dirreccionEnvioActual.setCalle(direccioenvio.getCalle());
			dirreccionEnvioActual.setCodigoPostal(direccioenvio.getCiudad());
			dirreccionEnvioActual.setNumero(direccioenvio.getEstado());
			dirreccionEnvioActual.setEstado(direccioenvio.getEstado());
			
			dirreccionEnvioUptade = direccionEnvioService.save(dirreccionEnvioActual);
		}catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar al actualizar en la Base de Datos");
			response.put("mensaje", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La direccion de envio se ha actualizado con exito");
		response.put("Direccion de envio", dirreccionEnvioUptade);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/direccionEnvio/{id}")
	public ResponseEntity<?>delete(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		
		try {
			direccionEnvioService.delete(id);
		}catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar al actualizar en la Base de Datos");
			response.put("mensaje", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La direccion del cliente se ha eliminado con exito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		
	}

}
