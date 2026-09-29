package com.pappalardo.quiz_storia.repositories;

import com.pappalardo.quiz_storia.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByAnno(int anno);
    List<Evento> findByTitolo(String titolo);
    List<Evento> findByCategoria(String categoria);
    List<Evento> findByCivilta(String civilta);
}
