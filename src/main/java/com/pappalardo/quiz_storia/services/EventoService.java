package com.pappalardo.quiz_storia.services;

import com.pappalardo.quiz_storia.dto.EventoDto;
import com.pappalardo.quiz_storia.entities.Evento;
import com.pappalardo.quiz_storia.repositories.EventoRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class EventoService {
    private final EventoRepository eventoRepository;
    private final ObjectMapper objectMapper;

    public EventoService(EventoRepository eventoRepository, ObjectMapper objectMapper) {
        this.eventoRepository = eventoRepository;
        this.objectMapper = objectMapper;
    }

    public void salva(Evento evento) {
        eventoRepository.save(evento);
    }

    public void salvaTutti() throws IOException {
        Resource[] risorse = new PathMatchingResourcePatternResolver()
                .getResources("classpath:dati/*.json");

        List<Evento> eventi = new ArrayList<>();
        for (Resource risorsa : risorse) {
            JsonNode root = objectMapper.readTree(risorsa.getInputStream());
            for (JsonNode nodo : root.get("eventi")) {
                eventi.add(new Evento(
                        nodo.get("anno").asInt(),
                        nodo.get("titolo").asText(),
                        nodo.get("luogo").asText(),
                        nodo.get("civilta").asText(),
                        nodo.get("categoria").asText(),
                        nodo.get("descrizione").asText()
                ));
            }
        }
        eventoRepository.saveAll(eventi);
    }

    public List<EventoDto> trovaTutti() {
        List<EventoDto> eventiDto = new ArrayList<>();
        Iterable<Evento> eventi = null;
        try {
            eventi = eventoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (Evento evento : eventi){
            eventiDto.add(EventoDto.fromEntity(evento));
        }
        return eventiDto;
    }

    public Evento trovaUno(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public void elimina(Long id) {
        eventoRepository.deleteById(id);
    }

}
