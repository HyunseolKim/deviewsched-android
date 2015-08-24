package com.gdgssu.android_deviewsched.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by flashgugu on 15. 8. 24.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailSessionInfo implements Serializable {

    public int id;
    public String title;
    public String description;
    public ArrayList<Speaker> speakers;
    public int day;
    public int track;
    public int year;

}
