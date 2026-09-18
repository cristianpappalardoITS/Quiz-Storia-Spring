package com.pappalardo.quiz_storia.dto;

import com.pappalardo.quiz_storia.entities.Evento;

public record EventoDto(Long id,String epoca, int anno, String titolo, String luogo, String civilta, String categoria,
                        String descrizione) {

    public static EventoDto fromEntity(Evento evento) {
        return new EventoDto(
                null,
                null,
                evento.getAnno(),
                evento.getTitolo(),
                evento.getLuogo(),
                evento.getCivilta(),
                evento.getCategoria(),
                evento.getDescrizione());
    }

    public static EventoDto fromEntityWithId(Evento evento) {
        return new EventoDto(
                evento.getId(),
                null,
                evento.getAnno(),
                evento.getTitolo(),
                evento.getLuogo(),
                evento.getCivilta(),
                evento.getCategoria(),
                evento.getDescrizione());
    }
    public void updateEntity(Evento evento) {
        evento.setAnno(anno);
        evento.setTitolo(titolo);
        evento.setLuogo(luogo);
        evento.setCivilta(civilta);
        evento.setCategoria(categoria);
        evento.setDescrizione(descrizione);
    }
}
