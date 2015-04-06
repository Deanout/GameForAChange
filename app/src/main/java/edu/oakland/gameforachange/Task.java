package edu.oakland.gameforachange;

import java.io.Serializable;

/**
 * Created by User on 3/25/2015.
 */

import java.io.Serializable;

/**
 * Created by User on 3/25/2015.
 */
public class Task implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7395222581084443899L;
    private int score = 0;
    private String task;
    private boolean firstRun = true;
    private boolean taskComplete = false;
    private boolean exists = false;
    private int completionRatio = 0;

    public Task() {

    }

    public Task(int s, int cR, String t, boolean fr, boolean tc, boolean e) {
        this.score = s;
        this.task = t;
        this.firstRun = fr;
        this.taskComplete = tc;
        this.exists = e;
        this.completionRatio = cR;
    }

    public Task(int s, String t) {
        this.score = s;
        this.task = t;
    }

    public void setCompletionRatio(int n) {
        this.completionRatio = n;
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
     * Used to flag a task as complete.
     * @param b
     */
    public void setTaskStatus(boolean b) {
        this.taskComplete = b;
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
    public boolean getTaskComplete() {
        return taskComplete;
    }
    /**
     *
     * @return
     */
    public boolean getExist() {
        return exists;
    }
    /**
     * Test method.
     */
    public void printStuff() {
        System.out.println(task);
    }
}