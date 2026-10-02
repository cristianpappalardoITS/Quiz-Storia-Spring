package com.pappalardo.quiz_storia.dto.response;

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
public record EventoResponse(Long id, String epoca, int anno, String titolo, String luogo, String civilta, String categoria,
                             String descrizione) {

    /**
     * Crea un {@link EventoResponse} a partire da un'entità {@link Evento}, escludendo l'id.
     *
     * @param evento l'entità {@link Evento} sorgente
     * @return una nuova istanza di {@link EventoResponse} senza identificatore
     */
    public static EventoResponse fromEntity(Evento evento) {
        return new EventoResponse(
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
     * Crea un {@link EventoResponse} a partire da un'entità {@link Evento}, includendo l'id dell'entità.
     *
     * @param evento l'entità {@link Evento} sorgente
     * @return una nuova istanza di {@link EventoResponse} con l'identificatore dell'entità
     */
    public static EventoResponse fromEntityWithId(Evento evento) {
        return new EventoResponse(
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
     * Converte un {@link EventoResponse} in una nuova entità {@link Evento}.
     *
     * @param dto l'oggetto {@link EventoResponse} da convertire
     * @return una nuova istanza di {@link Evento} popolata con i valori del DTO
     */
    public Evento toEntity(EventoResponse dto) {
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
