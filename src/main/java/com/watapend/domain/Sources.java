package com.watapend.domain;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.watapend.domain.TypeSource.ARTICLES;
import static com.watapend.domain.TypeSource.VARIEES;
import static com.watapend.domain.TypeSource.VIDEOS;

public class Sources implements Iterable<Source> {
    private Set<Source> sources = new HashSet<Source>();

    public void ajouter(Source source) {
        this.sources.add(source);
    }

    @Override
    public Iterator<Source> iterator() {
        return sources.iterator();
    }

    public static Sources toutes() {
        try {
            Sources sources = new Sources();
            sources.ajouter(new RssSource("Bas Gros Poing", VARIEES, new URL("http://basgrospoing.fr/feed/")));
            sources.ajouter(new RssSource("Xebia", ARTICLES, new URL("http://blog.xebia.fr/feed/?dualfeed=2")));
            sources.ajouter(new RssSource("Octo", ARTICLES, new URL("http://blog.octo.com/feed/")));
            sources.ajouter(new RssSource("Youtube", VIDEOS, new URL("http://gdata.youtube.com/feeds/base/users/anzymus/newsubscriptionvideos")));
            return sources;
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void ajouter(Sources sources) {
        this.sources.addAll(sources.sources);
    }
}
