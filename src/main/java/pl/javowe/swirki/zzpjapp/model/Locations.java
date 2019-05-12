package pl.javowe.swirki.zzpjapp.model;

import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import java.util.Locale;

public enum Locations {
    Poland("pl", "PL"), Ukraine("uk", "UA");
    private Locale locale;

    Locations(String language, String countryCode){
        this.locale = new Locale(language, countryCode);
    }
}
