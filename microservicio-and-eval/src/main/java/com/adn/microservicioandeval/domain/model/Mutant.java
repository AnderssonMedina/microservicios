package com.adn.microservicioandeval.domain.model;

import java.io.Serializable;

public class Mutant implements Serializable {
    private String dna;
    private boolean isMutant;
    private String identify;



    public Mutant(String dna, boolean isMutant, String identify){
        this.dna = dna;
        this.isMutant = isMutant;
        this.identify = identify;
    }

    public Mutant(){}

    public boolean isMutant() {
        return isMutant;
    }



}
