package com.watapend;

import com.watapend.domain.RssSource;
import org.joda.time.DateMidnight;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.watapend.domain.TypeSource.ARTICLES;
import static org.fest.assertions.Assertions.assertThat;

public class WatapendIT {

    private Watapend watapend = new Watapend();

    @Test
    public void doit_lister_les_nouveautes_de_plusieurs_sources() throws MalformedURLException {
        watapend.ajouterUneSource(new RssSource("Bas Gros Poing", new URL("http://basgrospoing.fr/feed/")));
        watapend.ajouterUneSource(new RssSource("Xebia", new URL("http://blog.xebia.fr/feed/?dualfeed=2")));

        String resume = watapend.genereLeResumeAPartirDu(new DateMidnight().minusYears(1)).toString();

        assertThat(resume.isEmpty()).isFalse();

        System.err.println(resume);
    }

    @Test
    public void doit_lister_les_nouveautes_du_jour() throws MalformedURLException {
        watapend.ajouterUneSource(new RssSource("PC INPACT", ARTICLES, new URL("http://www.pcinpact.com/rss/news.xml")));

        String resume = watapend.genereLeResumeAPartirDu(DateMidnight.now()).toString();

        assertThat(resume.isEmpty()).isFalse();

        System.err.println(resume);
    }


}
