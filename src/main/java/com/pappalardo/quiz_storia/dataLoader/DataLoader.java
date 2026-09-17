package com.pappalardo.quiz_storia.dataLoader;

import com.pappalardo.quiz_storia.repositories.EventoRepository;
import com.pappalardo.quiz_storia.services.EventoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final EventoService eventoService;
    private final EventoRepository eventoRepository;

    public DataLoader(
            EventoService eventoService,
            EventoRepository eventoRepository
    ) {
        this.eventoService = eventoService;
        this.eventoRepository = eventoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (eventoRepository.count() == 0) {
            eventoService.salvaTutti();
        }
    }
}

