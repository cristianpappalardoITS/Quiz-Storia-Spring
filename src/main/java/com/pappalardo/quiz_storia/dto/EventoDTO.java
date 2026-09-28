package com.pappalardo.quiz_storia.dto;

import com.pappalardo.quiz_storia.entities.Evento;

public record EventoDTO(Long id, String epoca, int anno, String titolo, String luogo, String civilta, String categoria,
                        String descrizione) {

    public static EventoDTO fromEntity(Evento evento) {
        return new EventoDTO(
                null,
                null,
                evento.getAnno(),
                evento.getTitolo(),
                evento.getLuogo(),
                evento.getCivilta(),
                evento.getCategoria(),
                evento.getDescrizione());
    }

    public static EventoDTO fromEntityWithId(Evento evento) {
        return new EventoDTO(
                evento.getId(),
                null,
                evento.getAnno(),
                evento.getTitolo(),
                evento.getLuogo(),
                evento.getCivilta(),
                evento.getCategoria(),
                evento.getDescrizione());
    }

    public Evento toEntity(EventoDTO dto) {
        return new Evento(dto.anno, dto.titolo, dto.luogo, dto.civilta, dto.categoria, dto.descrizione);
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
