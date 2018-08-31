package com.galukhin.introvert.model.luna2.managers;

import com.galukhin.introvert.model.luna2.db.DbHelper;
import com.galukhin.introvert.model.luna2.managers.interfaces.Observable;
import com.galukhin.introvert.model.luna2.managers.interfaces.Observer;
import com.galukhin.introvert.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class Manager implements Observable {
    private static TagsManager tagsManager;
    private static CatsManager catsManager;

    private List<Observer> observers = new ArrayList<>();
    DbHelper dbHelper = MainActivity.dbHelper2;


    public static TagsManager getTagsManager() {
        if (tagsManager == null) tagsManager = new TagsManager();
        return tagsManager;
    }

    public static CatsManager getCatsManager() {
        if (catsManager == null) catsManager = new CatsManager();
        return catsManager;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }


    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }


    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}