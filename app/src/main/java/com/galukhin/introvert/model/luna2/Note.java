package com.galukhin.introvert.model.luna2;

import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.data.TextData;

import java.util.ArrayList;
import java.util.List;

public class Note {
    List<Data<String>> cats = new ArrayList<>();

    {
        cats.add(new TextData("2"));
        cats.add(new TextData("22"));
    }


    List<Data<String>> tags = new ArrayList<>();

    {
        tags.add(new TextData("gbgfggb"));
        tags.add(new TextData("gbsdfsdfggb"));
        tags.add(new TextData("gbgwewwqwwqwdfggb"));
    }


    List<Data<?>> datas = new ArrayList<>();

    {
        datas.add(new TextData("4435"));
        datas.add(new TextData("25"));
        datas.add(new TextData("gbgf253235ggb"));
        datas.add(new TextData("235"));
    }

    public List<Data<String>> getCats() {
        return cats;
    }

    public List<Data<String>> getTags() {
        return tags;
    }

    public List<Data<?>> getDatas() {
        return datas;
    }
}