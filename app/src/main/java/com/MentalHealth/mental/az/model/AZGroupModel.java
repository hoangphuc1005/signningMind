package com.MentalHealth.mental.az.model;

import java.util.ArrayList;

/**
 * Created by hoangphuc on 15/07/2018.
 */
public class AZGroupModel {
    private String name;
    private ArrayList<AZChildModel>listChild=new ArrayList<>();

    public AZGroupModel(String name, ArrayList<AZChildModel> listChild) {
        this.name = name;
        this.listChild = listChild;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<AZChildModel> getListChild() {
        return listChild;
    }

    public void setListChild(ArrayList<AZChildModel> listChild) {
        this.listChild = listChild;
    }
}
