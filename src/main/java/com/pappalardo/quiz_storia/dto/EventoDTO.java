package com.pappalardo.quiz_storia.dto;

import com.pappalardo.quiz_storia.entities.Evento;

/**
 * Data Transfer Object (DTO) che rappresenta un evento storico con i suoi attributi.
 *
 * @param id          l'identificatore univoco dell'evento (può essere nullo per nuovi eventi)
 * @param epoca       l'epoca storica associata (se applicabile)
 * @param anno        l'anno in cui si è verificato l'evento
 * @param titolo      il titolo o nome dell'evento
 * @param luogo       il luogo geografico dell'evento
 * @param civilta     la civiltà o cultura correlata
 * @param categoria   la categoria tematica dell'evento
 * @param descrizione la descrizione dettagliata dell'evento
 */
public record EventoDTO(Long id, String epoca, int anno, String titolo, String luogo, String civilta, String categoria,
                        String descrizione) {

    /**
     * Crea un {@link EventoDTO} a partire da un'entità {@link Evento}, escludendo l'id.
     *
     * @param evento l'entità {@link Evento} sorgente
     * @return una nuova istanza di {@link EventoDTO} senza identificatore
     */
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

    /**
     * Crea un {@link EventoDTO} a partire da un'entità {@link Evento}, includendo l'id dell'entità.
     *
     * @param evento l'entità {@link Evento} sorgente
     * @return una nuova istanza di {@link EventoDTO} con l'identificatore dell'entità
     */
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

    /**
     * Converte un {@link EventoDTO} in una nuova entità {@link Evento}.
     *
     * @param dto l'oggetto {@link EventoDTO} da convertire
     * @return una nuova istanza di {@link Evento} popolata con i valori del DTO
     */
    public Evento toEntity(EventoDTO dto) {
        return new Evento(dto.anno, dto.titolo, dto.luogo, dto.civilta, dto.categoria, dto.descrizione);
    }

    /**
     * Aggiorna lo stato di un'entità {@link Evento} esistente con i valori presenti in questo DTO.
     *
     * @param evento l'entità {@link Evento} da aggiornare
     */
    public void updateEntity(Evento evento) {
        evento.setAnno(anno);
        evento.setTitolo(titolo);
        evento.setLuogo(luogo);
        evento.setCivilta(civilta);
        evento.setCategoria(categoria);
        evento.setDescrizione(descrizione);
    }
}
