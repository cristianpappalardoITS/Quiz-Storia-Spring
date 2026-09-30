package com.pappalardo.quiz_storia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale di configurazione e avvio dell'applicazione Spring Boot Quiz Storia.
 */
@SpringBootApplication
public class QuizStoriaApplication {

    /**
     * Costruttore predefinito dell'applicazione.
     */
    public QuizStoriaApplication() {
    }

	/**
	 * Metodo principale di ingresso (entry point) che avvia l'applicazione Spring Boot.
	 *
	 * @param args argomenti passati da riga di comando
	 */
	public static void main(String[] args) {
		SpringApplication.run(QuizStoriaApplication.class, args);
	}

}
