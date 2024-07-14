package com.forohub.forohub.topico.controller;

import com.forohub.forohub.topico.dto.TopicoDTO;
import com.forohub.forohub.topico.dto.UpdateTopico;
import com.forohub.forohub.topico.entities.Topico;
import com.forohub.forohub.topico.services.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/topico")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public List<Topico> listarTopicos() {
        return topicoService.getAllTopicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> getTopicoById(@PathVariable Integer id) {
        Optional<Topico> topicoFound = topicoService.getTopicoById(id);
        if (!topicoFound.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(topicoFound.get());
    }

    @PostMapping
    public ResponseEntity<Topico> createTopico(@RequestBody @Valid TopicoDTO topicoDTO) {
        Topico createdTopico = topicoService.crearTopico(topicoDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTopico.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdTopico);
    }

    @PutMapping
    public ResponseEntity<Topico> updateTopico(@RequestBody UpdateTopico updateTopico) {
        Topico updatedTopico = topicoService.updateTopico(updateTopico);
        return ResponseEntity.ok(updatedTopico);
    }

    @DeleteMapping("/{id}")
    public void deleteTopico(@PathVariable Integer id) {
        topicoService.deleteTopico(id);
    }
}
