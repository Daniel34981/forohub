package com.daniel.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(

        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        String autor,
        String curso

) {

    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }

}