package com.watapend.domain;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.joda.time.DateMidnight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.watapend.domain.TypeNouveaute.ARTICLE;
import static com.watapend.domain.TypeNouveaute.PODCAST;

public class RssSource extends Source {

    private final URL url;

    private static final Logger LOG = LoggerFactory.getLogger(RssSource.class);

    public RssSource(String nom, TypeSource typeSource, URL url) {
        super(nom, typeSource);
        this.url = url;
    }

    public RssSource(String nom, URL url) {
        super(nom, TypeSource.VARIEES);
        this.url = url;
    }

    @Override
    public void rafraichir() {
        try {
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url.openConnection()));
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry entry : entries) {
                ajouterNouveaute(nouveaute(entry));
            }
        } catch (FeedException e) {
            LOG.error("Impossible de mettre à jour la source: "+getNom(), e);
        } catch (IOException e) {
            LOG.error("Impossible de mettre à jour la source: " + getNom(), e);
        }
    }

    private Nouveaute nouveaute(SyndEntry entry) {
        DateMidnight date = new DateMidnight(entry.getPublishedDate());
        Auteur auteur = new Auteur(entry.getAuthor());
        String titre = entry.getTitle();
        String link = entry.getLink();
        if(entry.getEnclosures().isEmpty()) {
            return new Nouveaute(date, this, auteur, titre, ARTICLE, link);
        }
        return new Nouveaute(date, this, auteur, titre, PODCAST, link);
    }
}
