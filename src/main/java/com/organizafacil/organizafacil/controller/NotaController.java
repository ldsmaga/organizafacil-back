package com.organizafacil.organizafacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.organizafacil.organizafacil.entity.Nota;
import com.organizafacil.organizafacil.service.NotaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="/notas")
public class NotaController {
	
	@Autowired
	private NotaService service;
	
	
	@PostMapping("/adicionar")
	public Nota addNota(@RequestBody Nota nota) {
		return service.saveNota(nota);
	}
	
	//traz as notas apenas do user logado
	//	@RequestMapping(method=RequestMethod.GET)
	@GetMapping
	public ResponseEntity<Page<Nota>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="idAnotacao") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<Nota> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public Nota findNotaById(@PathVariable Integer id) {
		return service.getNotaById(id);
	}
	
	@PutMapping("/editar")
	public Nota updateNota(@RequestBody Nota nota) {
		return service.updateNota(nota);
	}
	

	@PutMapping("/inativar")
	public Nota inativarTarefa(@RequestBody Nota nota) {
		return service.inativarNota(nota);
	}
	

	@PutMapping("/arquivar")
	public Nota arquivarTarefa(@RequestBody Nota nota) {
		return service.arquivarNota(nota);
	}
	

	@PutMapping("/desarquivar")
	public Nota desarquivarTarefa(@RequestBody Nota nota) {
		return service.desarquivarNota(nota);
	}
	
	
//	@DeleteMapping("/apagar/{id}")
//	public String deleteNota(@PathVariable int id) {
//		return service.deleteNota(id);
//	}
	
//	@GetMapping("/notas")
//	public List<Nota> findNotas(){
//		return service.getNotas();
//	}
	
	
}
