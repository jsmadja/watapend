package com.watapend.domain;

import org.joda.time.DateMidnight;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.watapend.domain.TypeNouveaute.ARTICLE;
import static com.watapend.domain.TypeSource.VARIEES;
import static com.watapend.domain.TypeSource.VIDEOS;
import static org.fest.assertions.Assertions.assertThat;
import static org.joda.time.DateMidnight.parse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NouveauteTest {

    @Test
    public void doit_creer_le_resume_d_une_nouveaute_de_type_video() {
        Source source = mock(Source.class);

        when(source.getNom()).thenReturn("Youtube");
        when(source.getType()).thenReturn(VIDEOS);

        Auteur auteur = Mockito.mock(Auteur.class);
        when(auteur.getNom()).thenReturn("Ken Bogard");
        Nouveaute nouveaute = new Nouveaute(parse("2013-01-01"), source, auteur, "Topanga League");

        assertThat(nouveaute.toString()).isEqualTo("Ken Bogard a publié une nouvelle vidéo sur Youtube : Topanga League");
        assertThat(nouveaute.getDate()).isEqualTo(parse("2013-01-01"));
    }

    @Test
    public void doit_creer_le_resume_d_une_nouveaute_de_type_article() {
        Source source = mock(Source.class);

        when(source.getNom()).thenReturn("Gameblog");
        when(source.getType()).thenReturn(VARIEES);

        Auteur auteur = Mockito.mock(Auteur.class);
        when(auteur.getNom()).thenReturn("Julien C");
        Nouveaute nouveaute = new Nouveaute(parse("2013-01-01"), source, auteur, "Teaser PS4", ARTICLE);

        assertThat(nouveaute.toString()).isEqualTo("Julien C a publié un nouvel article sur Gameblog : Teaser PS4");
    }

    @Test
    public void doit_trier_les_nouveautes_par_ordre_ante_chronologique() {
        Nouveaute nouveaute1 = new Nouveaute(DateMidnight.parse("2012-01-01"), mock(Source.class), mock(Auteur.class), "titre1");
        Nouveaute nouveaute2 = new Nouveaute(DateMidnight.parse("2010-01-01"), mock(Source.class), mock(Auteur.class), "titre2");
        Nouveaute nouveaute3 = new Nouveaute(DateMidnight.parse("2013-01-01"), mock(Source.class), mock(Auteur.class), "titre3");

        List<Nouveaute> nouveautes = new ArrayList<Nouveaute>();
        nouveautes.add(nouveaute1);
        nouveautes.add(nouveaute2);
        nouveautes.add(nouveaute3);

        Collections.sort(nouveautes);

        assertThat(nouveautes).containsExactly(nouveaute3, nouveaute1, nouveaute2);
    }

}
