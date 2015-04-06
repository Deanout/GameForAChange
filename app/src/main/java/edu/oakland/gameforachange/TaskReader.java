package edu.oakland.gameforachange;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import edu.oakland.gameforachange.Task;

/**
 * Created by Dean on 4/6/2015.
 */
public class TaskReader {


    public static void readTask() {
        try {
            FileInputStream fi = new FileInputStream(Splash.dir + "task.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fi);
            Splash.task = (Task)objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
