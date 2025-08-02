package com.example.demomvc.controller;

import com.example.demomvc.entity.Tarefa;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaApiController {

    private List<Tarefa> tarefas = new ArrayList<>();
    private Long nextId = 1L;

    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa) {
        tarefa.setId(nextId++);
        tarefas.add(tarefa);
        return tarefa;
    }

    @GetMapping
    public List<Tarefa> listar() {
        return tarefas;
    }

    @GetMapping("/{id}")
    public Tarefa buscar(@PathVariable Long id) {
        return tarefas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @RequestBody Tarefa novaTarefa) {
        for (int i = 0; i < tarefas.size(); i++) {
            if (tarefas.get(i).getId().equals(id)) {
                novaTarefa.setId(id);
                tarefas.set(i, novaTarefa);
                return novaTarefa;
            }
        }
        throw new RuntimeException("Tarefa não encontrada");
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        tarefas.removeIf(t -> t.getId().equals(id));
    }
}
