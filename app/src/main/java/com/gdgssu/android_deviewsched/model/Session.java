package com.gdgssu.android_deviewsched.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Session implements Serializable {

    public int id;
    public String title;
    public ArrayList<Speaker> speakers;

}
