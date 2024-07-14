package com.forohub.forohub.topico.services;

import com.forohub.forohub.topico.dto.TopicoDTO;
import com.forohub.forohub.topico.dto.UpdateTopico;
import com.forohub.forohub.topico.entities.Estado;
import com.forohub.forohub.topico.entities.Topico;
import com.forohub.forohub.topico.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    public List<Topico> getAllTopicos() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> getTopicoById(Integer id) {
        return topicoRepository.findById(id);
    }

    public Topico crearTopico(TopicoDTO topicoDTO) {
        Optional<Topico> userFound = topicoRepository.findById(topicoDTO.usuarioId());
        if (!userFound.isPresent()) {
            System.out.println("ERROR");
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime fechaCreacion = LocalDateTime.parse(topicoDTO.fechaCreacion(), formatter);

        Topico topicoSaved = Topico.builder()
                .id(null)
                .autor(userFound.get().getAutor())
                .curso(topicoDTO.curso())
                .fechaCreacion(fechaCreacion)
                .estado(Estado.ACTIVO)
                .build();

        return topicoRepository.save(topicoSaved);
    }

    public Topico updateTopico(UpdateTopico updateTopico) {
        Optional<Topico> topicoFound = topicoRepository.findById(updateTopico.id());
        if (!topicoFound.isPresent()) {
            System.out.println("ERROR");
            return null;
        }

        Topico topico = topicoFound.get();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime fechaCreacion = LocalDateTime.parse(updateTopico.fechaCreacion(), formatter);

        topico.setTitulo(updateTopico.titulo());
        topico.setFechaCreacion(fechaCreacion);
        topico.setCurso(updateTopico.curso());

        return topicoRepository.save(topico);
    }

    public void deleteTopico(Integer id) {
       Optional<Topico> topicoFound = topicoRepository.findById(id);
       if (!topicoFound.isPresent()) {
           System.out.println("ERROR");

       }
       topicoRepository.deleteById(id);
    }


}
