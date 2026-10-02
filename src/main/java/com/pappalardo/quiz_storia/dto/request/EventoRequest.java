package com.pappalardo.quiz_storia.dto.request;

/**
 * Data Transfer Object (DTO) per la ricerca e il filtraggio degli eventi storici.
 *
 * @param anno      l'anno dell'evento da filtrare (opzionale)
 * @param titolo    il titolo dell'evento o parte di esso per la ricerca (opzionale)
 * @param civilta   la civiltà associata all'evento (opzionale)
 * @param categoria la categoria tematica dell'evento (opzionale)
 */
public record EventoRequest(Integer anno, String titolo, String civilta, String categoria) {

}
