package com.example.a14201104.cervejariascatarinenses;

import java.util.List;

public class DataBrewery {

    public int breweryId;
    public String breweryName;
    public String icon;
    public String description;
    public long latitute;
    public long longitude;
    public List<Beers> cervejas;


    public class Beers {
        public String beerName;
        public String tipo;
        public double abv;
        public int ibu;
    }
}
