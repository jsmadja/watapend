package com.watapend;

import com.watapend.domain.*;
import org.joda.time.DateMidnight;

public class Watapend {

    private Sources sources = new Sources();

    public String genereLeResumeComplet() {
        return genereLeResumeALaDateDu(DateMidnight.now().minusYears(1));
    }

    public void ajouterUneSource(Source source) {
        this.sources.ajouter(source);
    }

    public String genereLeResumeALaDateDu(DateMidnight date) {
        Resume resume = new Resume();
        for(Source source:sources) {
            source.rafraichir();
            Nouveautes nouveautes = source.listerNouveautesALaDateDu(date);
            for (Nouveaute nouveaute : nouveautes) {
                resume.ajouter(nouveaute.toString());
            }
        }
        return resume.toString();
    }
}
