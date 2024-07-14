package com.forohub.forohub.topico.controller;

import com.forohub.forohub.topico.dto.TopicoDTO;
import com.forohub.forohub.topico.dto.UpdateTopico;
import com.forohub.forohub.topico.entities.Topico;
import com.forohub.forohub.topico.services.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/topico")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public List<Topico> listarTopicos() {
        return topicoService.getAllTopicos();
    }

    @GetMapping("/{id}")
    public Optional<Topico> getTopicoById(@PathVariable Integer id) {
        Optional<Topico> topicoFound = topicoService.getTopicoById(id);
        if (!topicoFound.isPresent()) {
            return null
        }
        return topicoService.getTopicoById(id);
    }

    @PostMapping
    public Topico createTopico(@RequestBody @Valid TopicoDTO topicoDTO) {
        return topicoService.crearTopico(topicoDTO);
    }

    @PutMapping
    public Topico updateTopico(@RequestBody UpdateTopico updateTopico) {
        return topicoService.updateTopico(updateTopico);
    }

    @DeleteMapping("/{id}")
    public void deleteTopico(@PathVariable Integer id) {
        topicoService.deleteTopico(id);
    }
}
