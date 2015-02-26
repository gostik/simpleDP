package com.dpsimple.models;

/**
 * Created by user_sca on 26.02.2015.
 */
public abstract class Model {

    Long id; //TODO generate id

    String name = "";

    public String getName() {
        return name;
    }

    public Model(Long id, String name) {
        this.id = id;

        this.name = name;
    }


}
