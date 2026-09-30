package com.pappalardo.quiz_storia.repositories;

import com.pappalardo.quiz_storia.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository Spring Data JPA per la gestione della persistenza delle entità {@link Evento}.
 * Fornisce operazioni CRUD standard e supporto all'esecuzione di {@link org.springframework.data.jpa.domain.Specification}.
 */
public interface EventoRepository extends JpaRepository<Evento, Long>, JpaSpecificationExecutor<Evento> {
}
