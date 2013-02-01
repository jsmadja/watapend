package com.watapend.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Sources implements Iterable<Source> {
    private Set<Source> sources = new HashSet<Source>();

    public void ajouter(Source source) {
        this.sources.add(source);
    }

    @Override
    public Iterator<Source> iterator() {
        return sources.iterator();
    }
}
