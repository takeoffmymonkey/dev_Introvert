package com.galukhin.introvert.model.luna2;

import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.data.TextData;
import com.galukhin.introvert.model.luna2.data.TextListData;

import java.util.ArrayList;
import java.util.List;

public class Note {
    TextListData cats;

    {
        List<String> c = new ArrayList<>();
        c.add("2");
        c.add("22");
        cats = new TextListData(c);
    }

    TextListData tags;

    {
        List<String> t = new ArrayList<>();
        t.add("gbgfggb");
        t.add("gbsdfsdfggb");
        t.add("gbgwewwqwwqwdfggb");
        tags = new TextListData(t);
    }

    List<Data> datas = new ArrayList<>();

    {
        datas.add(new TextData("4435"));
        datas.add(new TextData("25"));
        datas.add(new TextData("gbgf253235ggb"));
        datas.add(new TextData("235"));
    }

    public TextListData getCats() {
        return cats;
    }

    public TextListData getTags() {
        return tags;
    }

    public List<Data> getDatas() {
        return datas;
    }
}