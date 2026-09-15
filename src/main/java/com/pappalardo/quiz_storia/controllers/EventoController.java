package com.pappalardo.quiz_storia.controllers;

import com.pappalardo.quiz_storia.dto.EventoDto;
import com.pappalardo.quiz_storia.repositories.EventoRepository;
import com.pappalardo.quiz_storia.services.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Controller
public class EventoController {
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @RequestMapping({"/eventi", "/eventi/lista"})
    public String eventi(Model model) {
        if (eventoService.trovaTutti().isEmpty()) {
            try {
                eventoService.salvaTutti();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        List<EventoDto> eventi = eventoService.trovaTutti();
        model.addAttribute("eventi", eventi);

        return "eventi/lista";
    }

    @RequestMapping("eventi/dettaglio")
    public String dettaglioEvento(Long id) {
        return "eventi/dettaglio";
    }

    @RequestMapping("/eventi/nuovo")
    public String nuovoEvento() {
        return "eventi/nuovo";
    }

    @RequestMapping("/eventi/modifica")
    public String modificaEvento() {
        return "eventi/modifica";
    }

    @RequestMapping("eventi/elimina")
    public String eliminaEvento() {
        return "eventi/elimina";
    }
}
