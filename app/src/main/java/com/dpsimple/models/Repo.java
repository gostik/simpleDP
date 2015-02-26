package com.dpsimple.models;

import java.util.ArrayList;

/**
 * Created by user_sca on 26.02.2015.
 */
public class Repo {

    public static ArrayList<CarModel> detailModelsRepoMonth =  new ArrayList<>();
    static {

        detailModelsRepoMonth.add(new CarModel(1l, "Mercedes"));
        detailModelsRepoMonth.add(new CarModel(2l, "Porsche"));
        detailModelsRepoMonth.add(new CarModel(3l, "Buick"));
    }

}
