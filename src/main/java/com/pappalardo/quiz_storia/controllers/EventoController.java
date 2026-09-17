package com.pappalardo.quiz_storia.controllers;

import com.pappalardo.quiz_storia.dto.EventoDto;
import com.pappalardo.quiz_storia.services.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class EventoController {
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping({"/eventi", "/eventi/lista"})
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

    @GetMapping("/eventi/dettaglio/{id}")
    public String dettaglioEvento(@PathVariable Long id, Model model) throws IOException {
        EventoDto evento = eventoService.trovaUno(id);
        model.addAttribute("evento", evento);
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
