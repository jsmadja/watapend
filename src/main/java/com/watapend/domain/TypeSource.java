package com.watapend.domain;

public enum TypeSource {

    VIDEOS("une nouvelle vidéo"), VARIEES("variée"), ARTICLES("un nouvel article");

    private final String phrase;

    TypeSource(String phrase) {
        this.phrase = phrase;
    }
    public String getPhrase() {
        return phrase;
    }
}
