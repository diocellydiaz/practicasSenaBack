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
import com.jabonesArtesanales.co.services.IEnviosService;

import jakarta.validation.Valid;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping(value="/api")
public class EnviosRestController {
	
	@Autowired
	private IEnviosService enviosService;
	
	@GetMapping("/envios")
	public List<Envios> index(){
		return enviosService.findAll();
	}
	
	@GetMapping("/envios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		Envios envios = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			envios = enviosService.findById(id);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(envios == null) {
			response.put("mensaje", "El envío con el ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Envios>(envios, HttpStatus.OK);
	}
	
	@PostMapping("/envios")
	public ResponseEntity<?> create(@Valid @RequestBody Envios envios, BindingResult result){
		Envios enviosNew = null;
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
			enviosNew = enviosService.save(envios);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar el insert en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El envío ha sido creado con éxito");
		response.put("envio", enviosNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/envios/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Envios envios, BindingResult result, @PathVariable Long id){
		Envios enviosActual = enviosService.findById(id);
		Envios enviosUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(enviosActual == null) {
			response.put("mensaje", "Error: no se puede editar, el envío con el ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {

			enviosActual.setOrdenID(envios.getOrdenID());
			enviosActual.setFechaEnvio(envios.getFechaEnvio());
			enviosActual.setDireccionCalle(envios.getDireccionCiudad());
			enviosActual.setEstadoEnvio(envios.getEstadoEnvio());
			
			enviosUpdate = enviosService.save(enviosActual);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al actualizar en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El envío se ha actualizado con éxito");
		response.put("envio", enviosUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/envios/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		
		try {
			enviosService.delete(id);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al eliminar en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El envío ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}

