package com.watapend.domain;

import org.joda.time.DateMidnight;

import static java.lang.String.format;

public class Nouveaute implements Comparable<Nouveaute> {

    private String titre;
    private final Source source;
    private final Auteur auteur;
    private DateMidnight date;
    private TypeNouveaute typeNouveaute;
    private final String lien;

    public Nouveaute(DateMidnight date, Source source, Auteur auteur, String titre, String lien) {
        this.date = date;
        this.source = source;
        this.auteur = auteur;
        this.titre = titre;
        this.lien = lien;
    }

    public Nouveaute(DateMidnight date, Source source, Auteur auteur, String titre, TypeNouveaute typeNouveaute, String lien) {
        this(date, source, auteur, titre, lien);
        this.typeNouveaute = typeNouveaute;
    }

    @Override
    public String toString() {
        String nomAuteur = auteur.getNom();
        String nomSource = source.getNom();

        if(auteur.estAnonyme()) {
            if(typeNouveaute == null) {
                return format("%s sur %s : <a href='%s'>%s</a>", source.getType().getPhrase(), nomSource, lien, titre);
            }
            return format("%s sur %s : <a href='%s'>%s</a>", typeNouveaute.getPhrase(), nomSource, lien, titre);
        }

        if(typeNouveaute == null) {
            String phrase = source.getType().getPhrase();
            return format("%s a publié %s sur %s : <a href='%s'>%s</a>", nomAuteur, phrase, nomSource, lien, titre);
        }
        String phrase = typeNouveaute.getPhrase();
        return format("%s a publié %s sur %s : <a href='%s'>%s</a>", nomAuteur, phrase, nomSource, lien, titre);
    }

    public DateMidnight getDate() {
        return date;
    }

    @Override
    public int compareTo(Nouveaute nouveaute) {
        return nouveaute.date.compareTo(this.date);
    }
}
