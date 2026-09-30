package com.pappalardo.quiz_storia.repositories;

import com.pappalardo.quiz_storia.entities.Evento;
import org.springframework.data.jpa.domain.Specification;

public class EventoSpecification {

    public static Specification<Evento> annoEquals(Integer anno) {
        return (root, query, cb) ->
                cb.equal(root.get("anno"), anno);
    }

    public static Specification<Evento> titoloContains(String titolo) {
        return (root, query, cb) ->
                cb.like(
                        cb.lower(root.get("titolo")),
                        "%" + titolo.toLowerCase() + "%"
                );
    }

    public static Specification<Evento> categoriaEquals(String categoria) {
        return (root, query, cb) ->
                cb.equal(root.get("categoria"), categoria);
    }

    public static Specification<Evento> civiltaEquals(String civilta) {
        return (root, query, cb) ->
                cb.equal(root.get("civilta"), civilta);
    }
}
