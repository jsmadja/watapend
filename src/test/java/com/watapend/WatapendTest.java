package com.watapend;

import com.watapend.domain.*;
import org.joda.time.DateMidnight;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import static com.watapend.domain.TypeSource.ARTICLES;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class WatapendTest {

    private Watapend watapend = new Watapend();

    @Test
    public void doit_afficher_rien_du_tout_s_il_ne_s_est_rien_passe() {
        String resume = watapend.genereLeResumeComplet();
        assertThat(resume).isEqualTo("");
    }

    @Test
    public void doit_afficher_la_derniere_video_de_ken_bogard() {
        Nouveaute nouveaute = Mockito.mock(Nouveaute.class);
        when(nouveaute.toString()).thenReturn("Ken Bogard a publié une nouvelle vidéo sur Youtube : Topanga League");

        Iterator<Nouveaute> nouveautesIterator = Mockito.mock(Iterator.class);
        when(nouveautesIterator.hasNext()).thenReturn(true, false);
        when(nouveautesIterator.next()).thenReturn(nouveaute);

        Nouveautes nouveautes = Mockito.mock(Nouveautes.class);
        when(nouveautes.iterator()).thenReturn(nouveautesIterator);

        Source sourceKenbogard = Mockito.mock(Source.class);
        when(sourceKenbogard.listerNouveautes()).thenReturn(nouveautes);

        watapend.ajouterUneSource(sourceKenbogard);

        String resume = watapend.genereLeResumeComplet();

        assertThat(resume).isEqualTo("Ken Bogard a publié une nouvelle vidéo sur Youtube : Topanga League");
    }

    @Test
    public void doit_afficher_les_deux_dernieres_videos_de_ken_bogard() {
        Source sourceKenbogard = Mockito.mock(Source.class);
        Nouveautes nouveautes = Mockito.mock(Nouveautes.class);

        Iterator<Nouveaute> nouveautesIterator = Mockito.mock(Iterator.class);
        when(nouveautesIterator.hasNext()).thenReturn(true, true, false);
        Nouveaute nouveaute1 = Mockito.mock(Nouveaute.class);
        when(nouveaute1.toString()).thenReturn("nouveauté 1");

        Nouveaute nouveaute2 = Mockito.mock(Nouveaute.class);
        when(nouveaute2.toString()).thenReturn("nouveauté 2");
        when(nouveautesIterator.next()).thenReturn(nouveaute1, nouveaute2);

        when(nouveautes.iterator()).thenReturn(nouveautesIterator);

        when(sourceKenbogard.listerNouveautes()).thenReturn(nouveautes);

        watapend.ajouterUneSource(sourceKenbogard);

        String resume = watapend.genereLeResumeComplet();

        assertThat(resume).isEqualTo("nouveauté 1\nnouveauté 2");
    }

    @Test
    public void doit_lister_les_nouveautes_de_plusieurs_sources() throws MalformedURLException {
        watapend.ajouterUneSource(new RssSource("Bas Gros Poing", new URL("http://basgrospoing.fr/feed/")));
        watapend.ajouterUneSource(new RssSource("Xebia", new URL("http://blog.xebia.fr/feed/?dualfeed=2")));

        String resume = watapend.genereLeResumeComplet();

        assertThat(resume.isEmpty()).isFalse();

        System.err.println(resume);
    }

    @Test
    public void doit_lister_les_nouveautes_du_jour() throws MalformedURLException {
        watapend.ajouterUneSource(new RssSource("Le Monde", ARTICLES, new URL("http://www.lemonde.fr/rss/")));

        String resume = watapend.genereLeResumeALaDateDu(DateMidnight.now());

        assertThat(resume.isEmpty()).isFalse();

        System.err.println(resume);
    }

}
