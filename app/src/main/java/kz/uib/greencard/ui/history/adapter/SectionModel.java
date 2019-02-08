package kz.uib.greencard.ui.history.adapter;

import java.util.ArrayList;
import java.util.List;

import kz.uib.greencard.repository.model.History;

public class SectionModel {
    private String sectionLabel;
    private List<History> itemArrayList;

    public SectionModel(String sectionLabel, List<History> itemArrayList) {
        this.sectionLabel = sectionLabel;
        this.itemArrayList = itemArrayList;
    }

    public String getSectionLabel() {
        return sectionLabel;
    }

    public List<History> getItemArrayList() {
        return itemArrayList;
    }
}
