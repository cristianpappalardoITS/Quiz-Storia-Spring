package com.pappalardo.quiz_storia.dataLoader;

import com.pappalardo.quiz_storia.repositories.EventoRepository;
import com.pappalardo.quiz_storia.services.EventoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Componente di bootstrap per il caricamento iniziale dei dati degli eventi storici nel database all'avvio dell'applicazione.
 */
@Component
public class DataLoader implements CommandLineRunner {

    /**
     * Servizio per la gestione degli eventi e l'importazione da file JSON.
     */
    private final EventoService eventoService;

    /**
     * Repository per la verifica della presenza di dati pregressi.
     */
    private final EventoRepository eventoRepository;

    /**
     * Costruisce una nuova istanza di {@link DataLoader}.
     *
     * @param eventoService    il servizio per la gestione degli eventi
     * @param eventoRepository il repository degli eventi
     */
    public DataLoader(
            EventoService eventoService,
            EventoRepository eventoRepository
    ) {
        this.eventoService = eventoService;
        this.eventoRepository = eventoRepository;
    }

    /**
     * Esegue la logica di inizializzazione all'avvio dell'applicazione.
     * Se la tabella degli eventi è vuota, carica tutti gli eventi dai file JSON.
     *
     * @param args argomenti passati da riga di comando
     * @throws Exception in caso di errori durante il caricamento dei dati
     */
    @Override
    public void run(String... args) throws Exception {
        if (eventoRepository.count() == 0) {
            eventoService.salvaTutti();
        }
    }
}

