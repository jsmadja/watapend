package com.watapend.domain;

public enum TypeNouveaute {

    ARTICLE("un nouvel article"), PODCAST("un nouveau podcast");

    private String phrase;

    private TypeNouveaute(String phrase) {
        this.phrase = phrase;
    }

    public String getPhrase() {
        return phrase;
    }
}
