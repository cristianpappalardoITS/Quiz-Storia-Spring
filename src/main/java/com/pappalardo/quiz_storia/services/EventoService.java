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
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;


@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final ObjectMapper objectMapper;

    public EventoService(
            EventoRepository eventoRepository,
            ObjectMapper objectMapper
    ) {
        this.eventoRepository = eventoRepository;
        this.objectMapper = objectMapper;
    }

    public void salvaTutti() throws IOException {
        Resource[] risorse = new PathMatchingResourcePatternResolver()
                .getResources("classpath:dati/*.json");

        List<Evento> eventi = Arrays.stream(risorse)
                .flatMap(risorsa -> {
                    try {
                        return parseEventi(risorsa).stream();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })
                .toList();

        if (!eventi.isEmpty()) {
            eventoRepository.saveAll(eventi);
        }
    }

    private List<Evento> parseEventi(Resource risorsa) throws IOException {
        try (InputStream inputStream = risorsa.getInputStream()) {
            JsonNode root = objectMapper.readTree(inputStream);

            List<Evento> eventi = new ArrayList<>();

            for (JsonNode nodo : root.path("eventi")) {
                eventi.add(new Evento(
                        nodo.path("anno").asInt(),
                        nodo.path("titolo").asText(),
                        nodo.path("luogo").asText(),
                        nodo.path("civilta").asText(),
                        nodo.path("categoria").asText(),
                        nodo.path("descrizione").asText()
                ));
            }

            return eventi;
        }
    }


    public List<EventoDto> trovaTutti() {
        return StreamSupport.stream(eventoRepository.findAll().spliterator(), false)
                .map(EventoDto::fromEntity)
                .toList();
    }

    public Evento trovaUno(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public void elimina(Long id) {
        eventoRepository.deleteById(id);
    }

}
