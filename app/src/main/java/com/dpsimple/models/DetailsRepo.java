package com.dpsimple.models;

import java.util.ArrayList;

/**
 * Created by user_sca on 26.02.2015.
 */
public class DetailsRepo {

    public static ArrayList<MonthModel> detailModelsRepoMonth =  new ArrayList<>();
    static {

        detailModelsRepoMonth.add(new MonthModel(1l, "Jan"));
        detailModelsRepoMonth.add(new MonthModel(2l, "Feb"));
        detailModelsRepoMonth.add(new MonthModel(3l, "Mar"));
    }

    public static ArrayList<ShapeModel> detailModelsRepoShape =  new ArrayList<>();
    static {

        detailModelsRepoShape.add(new ShapeModel(1l, "Jan"));
        detailModelsRepoShape.add(new ShapeModel(2l, "Feb"));
        detailModelsRepoShape.add(new ShapeModel(3l, "Mar"));
    }
}
