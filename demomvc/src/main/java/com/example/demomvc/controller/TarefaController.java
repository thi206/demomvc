package com.example.demomvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demomvc.entity.Tarefa;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {
	
	List<Tarefa> tarefas = new ArrayList<>();
	
	@GetMapping("/cadastro")
	public String cadastro(Tarefa tarefa) {
		return "/tarefas/cadastro";
	}
	@PostMapping("/salvar")
	public String salvar(Tarefa tarefa) {
		
		Long id = tarefas.size() + 1L;
		Tarefa t = new Tarefa();
		
		t.setId(id);
		t.setNome(tarefa.getNome());
		t.setDataEntrega(tarefa.getDataEntrega());
		t.setResponsavel(tarefa.getResponsavel());
		
		tarefas.add(t);
		return "redirect:/tarefas/lista";
	}
	
	@GetMapping("/lista")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("tarefas/lista");
		mv.addObject("tarefas", tarefas);
		
		return mv;
		
	}
	
	// editar e excluir

			@PostMapping("/editar")
			public String editar(Tarefa tarefa) {

				Tarefa t = new Tarefa();
				for (int i = 0; i < tarefas.size(); i++) {
					if (tarefas.get(i).getId().equals(tarefa.getId())) {
						t = tarefas.get(i);

					}
				}

				tarefas.set(tarefas.indexOf(t), tarefa);

				
				return "redirect:/tarefas/lista";
			}

			@GetMapping("/excluir/{id}")
			public String excluir(@PathVariable("id") Long id ) {

				Tarefa tarefa;
				for (int i = 0; i < tarefas.size(); i++) {
					if (tarefas.get(i).getId().equals(id)) {
						tarefa = tarefas.get(i);
						tarefas.remove(tarefa);
						
					}
				}

				return "redirect:/tarefas/lista";
			}

			@GetMapping("/editar/{id}")
			public ModelAndView preEditar(@PathVariable("id") Long id) {
				ModelAndView mv = new ModelAndView();
				mv.setViewName("tarefa/cadastro");
				
				Tarefa tarefa = null;
				for (int i = 0; i < tarefas.size(); i++) {
					if (tarefas.get(i).getId().equals(id)) {
						tarefa = tarefas.get(i);

					}
				}

				mv.addObject("tarefa", tarefa);
				return mv;
			}

}
