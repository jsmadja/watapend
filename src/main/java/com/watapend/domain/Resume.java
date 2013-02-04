package com.watapend.domain;

import com.google.common.base.Function;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Joiner.on;
import static com.google.common.collect.Collections2.transform;

public class Resume {

    private List<String> lignes = new ArrayList<String>();

    public void ajouter(Nouveaute nouveaute) {
        lignes.add(nouveaute.toString());
    }

    @Override
    public String toString() {
        String items = on(System.getProperty("line.separator")).join(transform(lignes, new Function<String, String>() {
            @Override
            public String apply(String input) {
                return "<li>" + input + "</li>";
            }
        }));
        return "<ul>"+ items +"</ul>";
    }
}
