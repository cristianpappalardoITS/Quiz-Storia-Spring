package com.pappalardo.quiz_storia.dto;

import com.pappalardo.quiz_storia.entities.Evento;

public record EventoDto(String epoca, int anno, String titolo, String luogo, String civilta, String categoria,
                        String descrizione) {

    public static EventoDto fromEntity(Evento evento) {
        return new EventoDto(
                null,
                evento.getAnno(),
                evento.getTitolo(),
                evento.getLuogo(),
                evento.getCivilta(),
                evento.getCategoria(),
                evento.getDescrizione());
    }
}
