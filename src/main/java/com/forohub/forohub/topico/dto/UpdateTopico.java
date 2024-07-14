package com.forohub.forohub.topico.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateTopico(
        @NotNull
        Integer id,
        @NotNull
        String titulo,
        @NotNull
        String fechaCreacion,
        @NotNull
        Integer usuarioId,
        @NotNull
        String curso) {
}
