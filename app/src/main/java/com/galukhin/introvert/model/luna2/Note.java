package com.galukhin.introvert.model.luna2;

import com.galukhin.introvert.model.luna2.data.Data;

import java.util.ArrayList;
import java.util.List;

public class Note {
    private String name = null;
    private long created;
    private long edited;
    private long cat;
    private long subCat;
    private List<Data> datas = new ArrayList<>();

    public Note() {
    }

    public Note(String name, long created, long edited, long cat, long subCat, List<Data> datas) {
        this.name = name;
        this.created = created;
        this.edited = edited;
        this.cat = cat;
        this.subCat = subCat;
        this.datas = datas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getEdited() {
        return edited;
    }

    public void setEdited(long edited) {
        this.edited = edited;
    }

    public long getCat() {
        return cat;
    }

    public void setCat(long cat) {
        this.cat = cat;
    }

    public long getSubCat() {
        return subCat;
    }

    public void setSubCat(long subCat) {
        this.subCat = subCat;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }
}