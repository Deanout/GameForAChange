package edu.oakland.gameforachange;

import java.io.Serializable;

/**
 * Created by User on 3/25/2015.
 */
public class Task implements Serializable {
    public String task = null;
    public int goal = 1;
    public boolean exists = false;


    public Task() {

    }
}