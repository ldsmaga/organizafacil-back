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

import com.organizafacil.organizafacil.entity.Tarefa;
import com.organizafacil.organizafacil.service.TarefaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tarefas")
public class TarefaController {
	
	@Autowired
	private TarefaService service;
	
	@PostMapping("/adicionar")
	public Tarefa addNota(@RequestBody Tarefa tarefa) {
		return service.saveTarefa(tarefa);
	}
	
	//traz as notas apenas do user logado
	//	@RequestMapping(method=RequestMethod.GET)
		@GetMapping
		public ResponseEntity<Page<Tarefa>> findPage(
				@RequestParam(value="page", defaultValue="0") Integer page, 
				@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
				@RequestParam(value="orderBy", defaultValue="idTarefa") String orderBy, 
				@RequestParam(value="direction", defaultValue="DESC") String direction) {
			Page<Tarefa> list = service.findPage(page, linesPerPage, orderBy, direction);
			return ResponseEntity.ok().body(list);
		}
		
		@GetMapping("/{id}")
		public Tarefa findTarefaById(@PathVariable Integer id) {
			return service.getTarefaById(id);
		}
		
		@PutMapping("/editar")
		public Tarefa updateTarefa(@RequestBody Tarefa tarefa) {
			return service.updateTarefa(tarefa);
		}
		
		@PutMapping("/inativar")
		public Tarefa inativarTarefa(@RequestBody Tarefa tarefa) {
			return service.inativarTarefa(tarefa);
		}
		
		
//		@DeleteMapping("/apagar/{id}")
//		public String deleteTarefa(@PathVariable int id) {
//			return service.deleteTarefa(id);
//		}
	

}
