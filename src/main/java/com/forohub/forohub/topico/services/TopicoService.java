package com.forohub.forohub.topico.services;

import com.forohub.forohub.error.ResourceNotFoundException;
import com.forohub.forohub.topico.dto.TopicoDTO;
import com.forohub.forohub.topico.dto.UpdateTopico;
import com.forohub.forohub.topico.entities.Estado;
import com.forohub.forohub.topico.entities.Topico;
import com.forohub.forohub.topico.repositories.TopicoRepository;
import com.forohub.forohub.user.entities.Usuario;
import com.forohub.forohub.user.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Topico> getAllTopicos() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> getTopicoById(Integer id) {
        return topicoRepository.findById(id);
    }

    public Topico crearTopico(TopicoDTO topicoDTO) {
        Optional<Usuario> userFound = usuarioRepository.findById(topicoDTO.usuarioId());
        if (!userFound.isPresent()) {
            System.out.println("ERROR");
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime fechaCreacion = LocalDateTime.parse(topicoDTO.fechaCreacion(), formatter);

        Topico topicoSaved = Topico.builder()
                .id(null)
                .titulo(topicoDTO.curso())
                .autor(userFound.get())
                .curso(topicoDTO.curso())
                .fechaCreacion(fechaCreacion)
                .estado(Estado.ACTIVO)
                .build();

        return topicoRepository.save(topicoSaved);
    }

    public Topico updateTopico(UpdateTopico updateTopico) {

        return topicoRepository.findById(updateTopico.id()).map(topico -> {
            topico.setTitulo(updateTopico.titulo());
            topico.setFechaCreacion(LocalDateTime.parse(updateTopico.fechaCreacion(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            topico.setCurso(updateTopico.curso());
            return topicoRepository.save(topico);
        }).orElseThrow(() -> new ResourceNotFoundException("Topico not found with id " + updateTopico.id()));
    }

    public ResponseEntity deleteTopico(Integer id) {
       Optional<Topico> topicoFound = topicoRepository.findById(id);
       if (!topicoFound.isPresent()) {
           throw new ResourceNotFoundException("Topico not found with id " + id);
       }
       topicoRepository.deleteById(id);
       return ResponseEntity.ok().build();
    }
}
