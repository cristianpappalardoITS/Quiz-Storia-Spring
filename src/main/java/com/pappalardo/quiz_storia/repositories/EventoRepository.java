package com.pappalardo.quiz_storia.repositories;

import com.pappalardo.quiz_storia.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
