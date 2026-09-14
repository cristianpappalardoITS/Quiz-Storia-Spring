package com.pappalardo.quiz_storia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventoController {

    @RequestMapping({"/eventi", "/eventi/lista"})
    public String eventi() {
        return "eventi/lista";
    }

    @RequestMapping("eventi/dettaglio")
    public String dettaglioEvento() {
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
