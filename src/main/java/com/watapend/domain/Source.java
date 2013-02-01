package com.watapend.domain;

import org.joda.time.DateMidnight;

public abstract class Source {

    private String nom;
    private TypeSource type;
    private Nouveautes nouveautes = new Nouveautes();

    public Source(String nom, TypeSource type) {
        this.nom = nom;
        this.type = type;
    }

    public Nouveautes listerNouveautes() {
        return nouveautes;
    }

    public String getNom() {
        return nom;
    }

    public TypeSource getType() {
        return type;
    }

    public abstract void rafraichir();

    protected void ajouterNouveaute(Nouveaute nouveaute) {
        nouveautes.ajouter(nouveaute);
    }

    public Nouveautes listerNouveautesALaDateDu(DateMidnight date) {
        return nouveautes.filtrerParDate(date);
    }
}
