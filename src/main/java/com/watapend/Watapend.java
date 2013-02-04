package com.watapend;

import com.watapend.domain.*;
import org.joda.time.DateMidnight;

public class Watapend {

    private Sources sources = new Sources();

    public Resume genereLeResumeComplet() {
        return genereLeResumeAPartirDu(DateMidnight.now().minusYears(1));
    }

    public void ajouterUneSource(Source source) {
        this.sources.ajouter(source);
    }

    public Resume genereLeResumeAPartirDu(DateMidnight date) {
        Resume resume = new Resume();
        for(Source source:sources) {
            source.rafraichir();
            Nouveautes nouveautes = source.listerNouveautesAPartirDu(date);
            for (Nouveaute nouveaute : nouveautes) {
                resume.ajouter(nouveaute);
            }
        }
        return resume;
    }

    public void ajouterLesSources(Sources sources) {
        this.sources.ajouter(sources);
    }
}
