package com.gdgssu.android_deviewsched.model;

import java.util.ArrayList;

public class FavoriteSession {

    private ArrayList<Integer> favorList;

    public FavoriteSession() {
        this.favorList = new ArrayList<>();
    }

    public void selectSession(int sessionId){
        if (favorList.contains(sessionId)){
            for (int i=0;i<favorList.size();i++){
                if (sessionId==favorList.get(i))
                    favorList.remove(i);
            }
        }else{
            favorList.add(sessionId);
        }
    }

    @Override
    public String toString() {
        return favorList.toString();
    }
}
