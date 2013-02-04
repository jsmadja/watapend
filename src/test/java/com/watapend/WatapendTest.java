package com.watapend;

import com.watapend.domain.Nouveaute;
import com.watapend.domain.Nouveautes;
import com.watapend.domain.Source;
import org.joda.time.DateMidnight;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Iterator;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class WatapendTest {

    private Watapend watapend = new Watapend();

    @Test
    public void doit_afficher_rien_du_tout_s_il_ne_s_est_rien_passe() {
        String resume = watapend.genereLeResumeComplet().toString();
        assertThat(resume).isEqualTo("<ul></ul>");
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
        when(sourceKenbogard.listerNouveautesAPartirDu(any(DateMidnight.class))).thenReturn(nouveautes);

        watapend.ajouterUneSource(sourceKenbogard);

        String resume = watapend.genereLeResumeComplet().toString();

        assertThat(resume).isEqualTo("<ul><li>Ken Bogard a publié une nouvelle vidéo sur Youtube : Topanga League</li></ul>");
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

        when(sourceKenbogard.listerNouveautesAPartirDu(any(DateMidnight.class))).thenReturn(nouveautes);

        watapend.ajouterUneSource(sourceKenbogard);

        String resume = watapend.genereLeResumeComplet().toString();

        assertThat(resume).isEqualTo("<ul><li>nouveauté 1</li>\n<li>nouveauté 2</li></ul>");
    }

}
