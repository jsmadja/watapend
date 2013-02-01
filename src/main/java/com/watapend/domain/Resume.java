package com.watapend.domain;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

public class Resume {

    private List<String> lignes = new ArrayList<String>();

    public void ajouter(String resume) {
        lignes.add(resume);
    }

    @Override
    public String toString() {
        return Joiner.on(System.getProperty("line.separator")).join(lignes);
    }
}
