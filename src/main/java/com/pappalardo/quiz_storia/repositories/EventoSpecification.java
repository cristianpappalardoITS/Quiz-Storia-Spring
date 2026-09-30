package com.pappalardo.quiz_storia.repositories;

import com.pappalardo.quiz_storia.entities.Evento;
import org.springframework.data.jpa.domain.Specification;

/**
 * Classe di utilità contenente le {@link Specification} JPA per il filtraggio dinamico delle entità {@link Evento}.
 */
public class EventoSpecification {

    /**
     * Crea una specifica per filtrare gli eventi in base all'anno esatto.
     *
     * @param anno l'anno dell'evento da ricercare
     * @return la {@link Specification} per filtrare per anno
     */
    public static Specification<Evento> annoEquals(Integer anno) {
        return (root, query, cb) ->
                cb.equal(root.get("anno"), anno);
    }

    /**
     * Crea una specifica per filtrare gli eventi il cui titolo contiene la stringa fornita (case-insensitive).
     *
     * @param titolo il testo da ricercare nel titolo
     * @return la {@link Specification} per filtrare per titolo
     */
    public static Specification<Evento> titoloContains(String titolo) {
        return (root, query, cb) ->
                cb.like(
                        cb.lower(root.get("titolo")),
                        "%" + titolo.toLowerCase() + "%"
                );
    }

    /**
     * Crea una specifica per filtrare gli eventi in base alla categoria esatta.
     *
     * @param categoria la categoria tematica dell'evento
     * @return la {@link Specification} per filtrare per categoria
     */
    public static Specification<Evento> categoriaEquals(String categoria) {
        return (root, query, cb) ->
                cb.equal(root.get("categoria"), categoria);
    }

    /**
     * Crea una specifica per filtrare gli eventi in base alla civiltà esatta.
     *
     * @param civilta la civiltà associata all'evento
     * @return la {@link Specification} per filtrare per civiltà
     */
    public static Specification<Evento> civiltaEquals(String civilta) {
        return (root, query, cb) ->
                cb.equal(root.get("civilta"), civilta);
    }
}
