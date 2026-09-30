package com.pappalardo.quiz_storia.controllers;

import com.pappalardo.quiz_storia.dto.EventoDTO;
import com.pappalardo.quiz_storia.dto.EventoSearchDTO;
import com.pappalardo.quiz_storia.services.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

        List<EventoDTO> eventi = eventoService.trovaTutti();
        model.addAttribute("eventi", eventi);

        return "eventi/lista";
    }

    @GetMapping("/eventi/dettaglio/{id}")
    public String dettaglioEvento(@PathVariable Long id, Model model) throws IOException {
        EventoDTO evento = eventoService.trovaUno(id);
        model.addAttribute("evento", evento);
        return "eventi/dettaglio";
    }


    @GetMapping("/eventi/modifica/{id}")
    public String modificaEvento(@PathVariable Long id, Model model) throws IOException {
        EventoDTO evento = eventoService.trovaUno(id);
        model.addAttribute("evento", evento);
        return "eventi/modifica";
    }

    @PostMapping("/eventi/modifica/{id}")
    public String aggiornaEvento(@PathVariable Long id, @ModelAttribute EventoDTO evento) throws IOException {
        eventoService.aggiorna(id, evento);
        return "redirect:/eventi/dettaglio/" + id;
    }

    @PostMapping("eventi/elimina/{id}")
    public String eliminaEvento(@PathVariable Long id) throws IOException {
        eventoService.elimina(id);
        return "redirect:/eventi/lista";
    }

    @GetMapping("/eventi/nuovo")
    public String mostraNuovoEvento() {
        return "eventi/nuovo";
    }

    @PostMapping("/eventi/nuovo")
    public String salvaNuovoEvento(@ModelAttribute EventoDTO evento) {
        eventoService.nuovo(evento);
        return "redirect:/eventi/lista";
    }

    @GetMapping("/eventi/cerca")
    public String cercaEventi() {
        return "eventi/cerca";
    }

    @GetMapping("/eventi/eventiFiltrati")
    public String mostraEvento(@ModelAttribute EventoSearchDTO eventoSearchDTO, Model model) {
        List<EventoDTO> eventi = eventoService.cerca(eventoSearchDTO);
        model.addAttribute("eventi", eventi);
        return "eventi/eventiFiltrati";
    }

}
