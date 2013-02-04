package com.watapend.domain;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.watapend.domain.TypeSource.VARIEES;
import static org.fest.assertions.Assertions.assertThat;

public class SourceIT {

    @Test
    public void doit_creer_une_source_de_type_rss() throws MalformedURLException {
        Source source = new RssSource("Bas Gros Poing", VARIEES, new URL("http://basgrospoing.fr/feed/"));
        source.rafraichir();
        Nouveautes nouveautes = source.listerNouveautes();
        assertThat(nouveautes.estVide()).isFalse();
        for (Nouveaute nouveaute : nouveautes) {
            System.err.println(nouveaute);
        }
    }

}
