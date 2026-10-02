package com.pappalardo.quiz_storia.services;

import com.pappalardo.quiz_storia.dto.response.EventoResponse;
import com.pappalardo.quiz_storia.dto.request.EventoRequest;
import com.pappalardo.quiz_storia.entities.Evento;
import com.pappalardo.quiz_storia.repositories.EventoRepository;
import com.pappalardo.quiz_storia.repositories.EventoSpecification;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Servizio applicativo per la gestione degli eventi storici.
 * Fornisce metodi per caricamento dati da file JSON, operazioni CRUD e ricerca filtrata.
 */
@Service
public class EventoService {

    /**
     * Repository per l'accesso ai dati delle entità {@link Evento}.
     */
    private final EventoRepository eventoRepository;

    /**
     * Mapper JSON per la deserializzazione dei file di dati.
     */
    private final ObjectMapper objectMapper;

    /**
     * Costruisce una nuova istanza di {@link EventoService} con i componenti necessari.
     *
     * @param eventoRepository il repository per la gestione degli eventi
     * @param objectMapper     il mapper Jackson per l'elaborazione dei file JSON
     */
    public EventoService(
            EventoRepository eventoRepository,
            ObjectMapper objectMapper
    ) {
        this.eventoRepository = eventoRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Carica e salva nel database tutti gli eventi storici definiti nei file JSON
     * presenti nel classpath sotto il percorso "classpath:dati/*.json".
     *
     * @throws IOException se si verifica un errore durante la lettura dei file JSON
     */
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

    /**
     * Esegue il parsing di una risorsa JSON estraendo la lista di entità {@link Evento}.
     *
     * @param risorsa la risorsa JSON da analizzare
     * @return la lista di entità {@link Evento} estratte
     * @throws IOException se si verifica un errore durante la lettura dello stream
     */
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

    /**
     * Recupera tutti gli eventi presenti nel database convertendoli in {@link EventoResponse}.
     *
     * @return la lista di tutti gli eventi storici
     */
    public List<EventoResponse> trovaTutti() {
        return eventoRepository.findAllByOrderByAnnoAsc().stream()
                .map(EventoResponse::fromEntityWithId)
                .toList();
    }

    /**
     * Recupera un singolo evento per identificatore univoco.
     *
     * @param id l'identificatore univoco dell'evento da recuperare
     * @return l'oggetto {@link EventoResponse} corrispondente
     * @throws IOException              se si verifica un errore I/O
     * @throws IllegalArgumentException se l'id fornito è nullo
     * @throws java.util.NoSuchElementException se l'evento con l'id specificato non viene trovato
     */
    public EventoResponse trovaUno(Long id) throws IOException {
        if (id == null) {
            throw new IllegalArgumentException("Id non valido");
        }
        Evento evento = eventoRepository.findById(id).orElseThrow();
        return EventoResponse.fromEntityWithId(evento);
    }

    /**
     * Elimina un evento dal database tramite il suo identificatore univoco.
     *
     * @param id l'identificatore univoco dell'evento da eliminare
     */
    public void elimina(Long id) {
        eventoRepository.deleteById(id);
    }

    /**
     * Aggiorna un evento esistente nel database con i dati forniti nel DTO.
     *
     * @param id  l'identificatore univoco dell'evento da aggiornare
     * @param eventoResponse il DTO contenente i dati aggiornati
     * @throws RuntimeException se l'evento con l'id specificato non esiste
     */
    @Transactional
    public void aggiorna(Long id, EventoResponse eventoResponse) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));

        eventoResponse.updateEntity(evento);
    }

    /**
     * Crea e salva un nuovo evento nel database.
     *
     * @param eventoResponse l'oggetto {@link EventoResponse} contenente i dettagli del nuovo evento
     */
    public void nuovo(EventoResponse eventoResponse) {
        eventoRepository.save(eventoResponse.toEntity(eventoResponse));
    }

    /**
     * Cerca ed estrae gli eventi che soddisfano i criteri di filtro specificati nel DTO di ricerca.
     *
     * @param eventoRequest l'oggetto {@link EventoRequest} contenente i parametri di ricerca (anno, titolo, categoria, civiltà)
     * @return la lista di {@link EventoResponse} che corrispondono ai criteri indicati
     */
    public List<EventoResponse> cerca(EventoRequest eventoRequest) {

        Specification<Evento> specification = Specification.unrestricted();

        if (eventoRequest.anno() != null) {
            specification = specification.and(
                    EventoSpecification.annoEquals(eventoRequest.anno())
            );
        }

        if (eventoRequest.titolo() != null &&
                !eventoRequest.titolo().isBlank()) {

            specification = specification.and(
                    EventoSpecification.titoloContains(eventoRequest.titolo())
            );
        }

        if (eventoRequest.categoria() != null &&
                !eventoRequest.categoria().isBlank()) {

            specification = specification.and(
                    EventoSpecification.categoriaEquals(eventoRequest.categoria())
            );
        }

        if (eventoRequest.civilta() != null &&
                !eventoRequest.civilta().isBlank()) {

            specification = specification.and(
                    EventoSpecification.civiltaEquals(eventoRequest.civilta())
            );
        }

        return eventoRepository.findAll(specification).stream()
                .map(EventoResponse::fromEntityWithId)
                .toList();
    }
}
