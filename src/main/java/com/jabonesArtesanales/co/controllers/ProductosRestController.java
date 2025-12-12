package com.jabonesArtesanales.co.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jabonesArtesanales.co.entity.Producto;
import com.jabonesArtesanales.co.services.IProductosService;

import jakarta.validation.Valid;

@CrossOrigin(origins= {"https://jabonesartesanales.up.railway.app", "http://localhost:4200"})
@RestController
@RequestMapping(value="/api")
public class ProductosRestController {

	@Autowired
	private IProductosService productosService;
	
	private final Logger log = LoggerFactory.getLogger(ProductosRestController.class);
	
	@GetMapping("/productos")
	public List<Producto> index(){
		return productosService.findAll();
	}
	
	@GetMapping("/productos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		Producto producto = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			producto = productosService.findById(id);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(producto == null) {
			response.put("mensaje", "El producto con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	@PostMapping("/productos")
	public ResponseEntity<?> create(@Valid @RequestBody Producto producto, BindingResult result) {
		Producto productoNew = null;
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
			productoNew = productosService.save(producto);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar el insert en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El producto ha sido creado con éxito");
		response.put("producto", productoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/productos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long id) {
		Producto productoActual = productosService.findById(id);
		Producto productoUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(productoActual == null) {
			response.put("mensaje", "Error: no se puede editar, el producto con el ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
	
			productoActual.setNombrefoto(producto.getNombrefoto());
			productoActual.setDescripcion(producto.getDescripcion());
			productoActual.setPrecio(producto.getPrecio());
			productoActual.setCategoria(producto.getCategoria());
			productoActual.setProveedor(producto.getProveedor());
			
			if(producto.getNombrefoto() != null) {
				productoActual.setNombrefoto(producto.getNombrefoto());
			}
			
			productoUpdate = productosService.save(productoActual);
		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al actualizar en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El producto se ha actualizado con éxito");
		response.put("producto", productoUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			Producto producto = productosService.findById(id);
			
			
			if(producto == null) {
				response.put("mensaje", "El producto con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			

			if(producto.getNombrefoto() != null) {
				String nombreFotoAnterior = producto.getNombrefoto();
				if (nombreFotoAnterior != null && !nombreFotoAnterior.isEmpty()) {
					Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
					File archivoFotoAnterior = rutaFotoAnterior.toFile();
					if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
						archivoFotoAnterior.delete();
					}
				}
			}

			productosService.delete(id);

		} catch(DataAccessException ex) {
			response.put("mensaje", "Error al eliminar en la Base de Datos");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El producto ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/productos/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id ){
		Map<String, Object> response = new HashMap<>();
		
		Producto producto = productosService.findById(id);
		
		if(producto == null) {
			response.put("mensaje", "El producto con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if(!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			log.info(rutaArchivo.toString());
				
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del producto " + nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause() != null ? e.getCause().getMessage() : ""));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		
			String nombreFotoAnterior = producto.getNombrefoto(); 
			if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}

			// CORRECCIÓN CRÍTICA: Establecer el nuevo nombre de archivo, no el anterior
			producto.setNombrefoto(nombreArchivo);
			productosService.save(producto);
			
			response.put("producto", producto);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		} else {
			response.put("mensaje", "No se ha seleccionado ningún archivo");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		log.info(rutaArchivo.toString());
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
		
		if(!recurso.exists() || !recurso.isReadable()) {
			throw new RuntimeException("Error: no se pudo cargar la imagen: " + nombreFoto); 
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@PutMapping("/productos/{id}/reducir-stock")
	public ResponseEntity<String> reducirStock(@PathVariable Long id, @RequestParam int cantidad) {
		try {
			Producto producto = productosService.findById(id);
			if(producto == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado.");
			}
			
			boolean actualizado = productosService.reducirStock(id, cantidad, producto);
			
			if(actualizado) {
				return ResponseEntity.ok("Stock reducido correctamente.");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo reducir el stock.");
			}
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al reducir el stock: " + ex.getMessage());
		}
	}
	
	
	@GetMapping("/productos/categoria/{categoriaId}")
	public List<Producto> productosPorCategoria(@PathVariable Long categoriaId) {
	    return productosService.findByCategoriaId(categoriaId);
	}
	
	
	
}