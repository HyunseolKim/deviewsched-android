package com.gdgssu.android_deviewsched.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by flashgugu on 15. 8. 24.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Speakers {
    public ArrayList<Speaker> speakers;
}
