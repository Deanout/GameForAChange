package edu.oakland.gameforachange;

import java.io.Serializable;


/**
 * Created by Dean on 3/25/2015.
 */
public class Task implements Serializable {

    private static final long serialVersionUID = -7395222581084443899L;
    private int score = 0;
    private String task;
    private boolean firstRun = true;
    private boolean exists = false;
    private double completionRatio = 0;
    private int tasksAccepted = 0;


    public Task() {

    }

    public Task(int s, double cR, int tA, String t, boolean fr, boolean e) {
        this.score = s;
        this.task = t;
        this.firstRun = fr;
        this.exists = e;
        this.completionRatio = cR;
        this.tasksAccepted = tA;
    }

    public Task(int s, String t) {
        this.score = s;
        this.task = t;
    }

    public void setTasksAccepted(int n) {
        this.tasksAccepted += n;
    }
    public void calculateCompletionRatio() {
        this.completionRatio = (this.score/this.tasksAccepted);
    }


    public void setTask(String s) {
        this.task = s;
    }
    /**
     * Used to set whether the program has run before.
     * @param b Default is true, pass false to declare that the program has run before.
     */
    public void setFirstRun(boolean b) {
        this.firstRun = b;
    }

    /**
     *
     * @param b
     */
    public void setTaskExists(boolean b) {
        this.exists = b;
    }

    public void setScore(int n) {
        this.score = n;
    }
    public int getTasksAccepted() {
        return tasksAccepted;
    }
    public double getCompletionRatio() {
        return completionRatio;
    }
    public String getTask() {
        return task;
    }
    public int getScore() {
        return score;
    }
    /**
     *
     * @return
     */
    public boolean getFirstRun() {
        return firstRun;
    }
    /**
     *
     * @return
     */

    public boolean getExist() {
        return exists;
    }

}