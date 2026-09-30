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

/**
 * Controller MVC per la gestione delle richieste web relative agli eventi storici.
 * Gestisce visualizzazione, inserimento, modifica, eliminazione e ricerca degli eventi.
 */
@Controller
public class EventoController {

    /**
     * Servizio per la gestione della logica di business legata agli eventi.
     */
    private final EventoService eventoService;

    /**
     * Costruisce una nuova istanza di {@link EventoController}.
     *
     * @param eventoService il servizio per la gestione degli eventi
     */
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    /**
     * Mostra la lista di tutti gli eventi storici. Se il database è vuoto, effettua un primo popolamento.
     *
     * @param model il modello Spring MVC per il passaggio dati alla vista
     * @return il nome del template per la lista degli eventi ("eventi/lista")
     */
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

    /**
     * Mostra i dettagli di un singolo evento storico.
     *
     * @param id    l'identificatore univoco dell'evento
     * @param model il modello Spring MVC per il passaggio dati alla vista
     * @return il nome del template di dettaglio ("eventi/dettaglio")
     * @throws IOException se si verifica un errore durante il recupero dell'evento
     */
    @GetMapping("/eventi/dettaglio/{id}")
    public String dettaglioEvento(@PathVariable Long id, Model model) throws IOException {
        EventoDTO evento = eventoService.trovaUno(id);
        model.addAttribute("evento", evento);
        return "eventi/dettaglio";
    }

    /**
     * Mostra la pagina con il modulo di modifica per un evento storico esistente.
     *
     * @param id    l'identificatore univoco dell'evento da modificare
     * @param model il modello Spring MVC per il passaggio dati alla vista
     * @return il nome del template di modifica ("eventi/modifica")
     * @throws IOException se si verifica un errore durante il recupero dell'evento
     */
    @GetMapping("/eventi/modifica/{id}")
    public String modificaEvento(@PathVariable Long id, Model model) throws IOException {
        EventoDTO evento = eventoService.trovaUno(id);
        model.addAttribute("evento", evento);
        return "eventi/modifica";
    }

    /**
     * Gestisce la sottomissione del modulo di modifica di un evento storico.
     *
     * @param id     l'identificatore univoco dell'evento da modificare
     * @param evento i dati aggiornati dell'evento
     * @return il redirect alla pagina di dettaglio dell'evento modificato
     * @throws IOException se si verifica un errore durante l'aggiornamento
     */
    @PostMapping("/eventi/modifica/{id}")
    public String aggiornaEvento(@PathVariable Long id, @ModelAttribute EventoDTO evento) throws IOException {
        eventoService.aggiorna(id, evento);
        return "redirect:/eventi/dettaglio/" + id;
    }

    /**
     * Gestisce l'eliminazione di un evento storico tramite il suo identificatore.
     *
     * @param id l'identificatore univoco dell'evento da eliminare
     * @return il redirect alla lista degli eventi
     * @throws IOException se si verifica un errore durante l'eliminazione
     */
    @PostMapping("eventi/elimina/{id}")
    public String eliminaEvento(@PathVariable Long id) throws IOException {
        eventoService.elimina(id);
        return "redirect:/eventi/lista";
    }

    /**
     * Mostra il modulo per l'inserimento di un nuovo evento storico.
     *
     * @return il nome del template per la creazione del nuovo evento ("eventi/nuovo")
     */
    @GetMapping("/eventi/nuovo")
    public String mostraNuovoEvento() {
        return "eventi/nuovo";
    }

    /**
     * Gestisce la sottomissione e il salvataggio di un nuovo evento storico.
     *
     * @param evento i dati del nuovo evento inseriti dall'utente
     * @return il redirect alla lista degli eventi
     */
    @PostMapping("/eventi/nuovo")
    public String salvaNuovoEvento(@ModelAttribute EventoDTO evento) {
        eventoService.nuovo(evento);
        return "redirect:/eventi/lista";
    }

    /**
     * Mostra la pagina con il modulo di ricerca avanzata degli eventi.
     *
     * @return il nome del template di ricerca ("eventi/cerca")
     */
    @GetMapping("/eventi/cerca")
    public String cercaEventi() {
        return "eventi/cerca";
    }

    /**
     * Esegue la ricerca filtrata degli eventi in base ai parametri specificati e mostra i risultati.
     *
     * @param eventoSearchDTO i criteri di filtro selezionati dall'utente
     * @param model           il modello Spring MVC per il passaggio dei risultati alla vista
     * @return il nome del template con gli eventi filtrati ("eventi/eventiFiltrati")
     */
    @GetMapping("/eventi/eventiFiltrati")
    public String mostraEvento(@ModelAttribute EventoSearchDTO eventoSearchDTO, Model model) {
        List<EventoDTO> eventi = eventoService.cerca(eventoSearchDTO);
        model.addAttribute("eventi", eventi);
        return "eventi/eventiFiltrati";
    }

}
