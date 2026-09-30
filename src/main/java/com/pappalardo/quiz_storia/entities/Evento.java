package com.pappalardo.quiz_storia.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Rappresenta un'entità per un evento storico all'interno dell'applicazione.
 */
@Entity
public class Evento {

    /**
     * Identificatore univoco dell'evento (chiave primaria generata automaticamente).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /**
     * Anno in cui si è verificato l'evento storico (può essere negativo per anni a.C.).
     */
    public int anno;

    /**
     * Titolo o nome dell'evento storico.
     */
    public String titolo;

    /**
     * Luogo geografico in cui si è svolto l'evento.
     */
    public String luogo;

    /**
     * Civiltà o cultura associata all'evento storico.
     */
    public String civilta;

    /**
     * Categoria tematica dell'evento (ad es. politica, guerra, cultura, scienza).
     */
    public String categoria;

    /**
     * Descrizione dettagliata dell'evento storico.
     */
    public String descrizione;

    /**
     * Costruttore predefinito senza argomenti (richiesto da JPA).
     */
    public Evento() {
    }

    /**
     * Costruisce una nuova istanza di {@link Evento} con i dettagli specificati.
     *
     * @param anno        l'anno dell'evento
     * @param titolo      il titolo dell'evento
     * @param luogo       il luogo dell'evento
     * @param civilta     la civiltà associata
     * @param categoria   la categoria tematica
     * @param descrizione la descrizione dell'evento
     */
    public Evento(int anno, String titolo, String luogo, String civilta, String categoria, String descrizione) {
        this.anno = anno;
        this.titolo = titolo;
        this.luogo = luogo;
        this.civilta = civilta;
        this.categoria = categoria;
        this.descrizione = descrizione;
    }

    /**
     * Restituisce l'identificatore univoco dell'evento.
     *
     * @return l'identificatore dell'evento
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'identificatore univoco dell'evento.
     *
     * @param id l'identificatore dell'evento
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce l'anno dell'evento storico.
     *
     * @return l'anno dell'evento
     */
    public int getAnno() {
        return anno;
    }

    /**
     * Imposta l'anno dell'evento storico.
     *
     * @param anno l'anno dell'evento
     */
    public void setAnno(int anno) {
        this.anno = anno;
    }

    /**
     * Restituisce il titolo dell'evento.
     *
     * @return il titolo dell'evento
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Imposta il titolo dell'evento.
     *
     * @param titolo il titolo dell'evento
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * Restituisce il luogo dell'evento.
     *
     * @return il luogo dell'evento
     */
    public String getLuogo() {
        return luogo;
    }

    /**
     * Imposta il luogo dell'evento.
     *
     * @param luogo il luogo dell'evento
     */
    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    /**
     * Restituisce la civiltà associata all'evento.
     *
     * @return la civiltà associata
     */
    public String getCivilta() {
        return civilta;
    }

    /**
     * Imposta la civiltà associata all'evento.
     *
     * @param civilta la civiltà associata
     */
    public void setCivilta(String civilta) {
        this.civilta = civilta;
    }

    /**
     * Restituisce la categoria dell'evento.
     *
     * @return la categoria dell'evento
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Imposta la categoria dell'evento.
     *
     * @param categoria la categoria dell'evento
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Restituisce la descrizione dell'evento.
     *
     * @return la descrizione dell'evento
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione dell'evento.
     *
     * @param descrizione la descrizione dell'evento
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce una rappresentazione in formato stringa dell'oggetto {@link Evento}.
     *
     * @return stringa rappresentante l'evento
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Evento{");
        sb.append("id=").append(id);
        sb.append(", anno=").append(anno);
        sb.append(", titolo='").append(titolo).append('\'');
        sb.append(", luogo='").append(luogo).append('\'');
        sb.append(", civilta='").append(civilta).append('\'');
        sb.append(", categoria='").append(categoria).append('\'');
        sb.append(", descrizione='").append(descrizione).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
