package com.gdgssu.android_deviewsched.model;

import java.util.ArrayList;

public class FavoriteSession {

    private ArrayList<Integer> favorList;

    public FavoriteSession() {
        this.favorList = new ArrayList<>();
    }

    public void addSession(int sessionId){
        if (favorList.contains(sessionId)){
        }else{
            favorList.add(sessionId);
        }
    }

    public void removeSession(int sessionId){
        if (favorList.contains(sessionId)){
            favorList.remove(sessionId);
        }else{
        }
    }

    @Override
    public String toString() {
        return favorList.toString();
    }
}