package com.watapend.domain;

import org.apache.commons.lang.StringUtils;

public class Auteur {
    private String nom;

    public Auteur(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public boolean estAnonyme() {
        return StringUtils.isBlank(nom);
    }
}
