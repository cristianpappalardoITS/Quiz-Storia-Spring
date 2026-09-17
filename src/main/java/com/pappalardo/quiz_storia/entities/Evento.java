package com.pappalardo.quiz_storia.entities;

import jakarta.persistence.*;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public int anno;
    public String titolo;
    public String luogo;
    public String civilta;
    public String categoria;
    public String descrizione;

    public Evento() {
    }

    public Evento(int anno, String titolo, String luogo, String civilta, String categoria, String descrizione) {
        this.anno = anno;
        this.titolo = titolo;
        this.luogo = luogo;
        this.civilta = civilta;
        this.categoria = categoria;
        this.descrizione = descrizione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getCivilta() {
        return civilta;
    }

    public void setCivilta(String civilta) {
        this.civilta = civilta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

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
