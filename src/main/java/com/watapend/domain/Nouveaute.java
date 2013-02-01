package com.watapend.domain;

import org.joda.time.DateMidnight;

import static java.lang.String.format;

public class Nouveaute implements Comparable<Nouveaute> {

    private String titre;
    private final Source source;
    private final Auteur auteur;
    private DateMidnight date;
    private TypeNouveaute typeNouveaute;

    public Nouveaute(DateMidnight date, Source source, Auteur auteur, String titre) {
        this.date = date;
        this.source = source;
        this.auteur = auteur;
        this.titre = titre;
    }

    public Nouveaute(DateMidnight date, Source source, Auteur auteur, String titre, TypeNouveaute typeNouveaute) {
        this(date, source, auteur, titre);
        this.typeNouveaute = typeNouveaute;
    }

    @Override
    public String toString() {
        String nomAuteur = auteur.getNom();
        String nomSource = source.getNom();
        if(typeNouveaute == null) {
            String phrase = source.getType().getPhrase();
            return format("%s a publié %s sur %s : %s", nomAuteur, phrase, nomSource, titre);
        }
        String phrase = typeNouveaute.getPhrase();
        return format("%s a publié %s sur %s : %s", nomAuteur, phrase, nomSource, titre);
    }

    public DateMidnight getDate() {
        return date;
    }

    @Override
    public int compareTo(Nouveaute nouveaute) {
        return nouveaute.date.compareTo(this.date);
    }
}
