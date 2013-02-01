package com.watapend.domain;

import com.google.common.base.Predicate;
import org.joda.time.DateMidnight;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static com.google.common.collect.Collections2.filter;


public class Nouveautes implements Iterable<Nouveaute> {

    private Set<Nouveaute> nouveautes = new TreeSet<Nouveaute>();

    public Nouveautes(Collection<Nouveaute> nouveautes) {
        this.nouveautes.addAll(nouveautes);
    }

    public Nouveautes() {

    }

    @Override
    public Iterator<Nouveaute> iterator() {
        return nouveautes.iterator();
    }

    public boolean estVide() {
        return nouveautes.isEmpty();
    }

    public void ajouter(Nouveaute nouveaute) {
        this.nouveautes.add(nouveaute);
    }

    public Nouveautes filtrerParDate(final DateMidnight date) {
        return new Nouveautes(filter(nouveautes, new Predicate<Nouveaute>() {
            @Override
            public boolean apply(Nouveaute nouveaute) {
                return nouveaute.getDate().equals(date);
            }
        }));
    }
}
