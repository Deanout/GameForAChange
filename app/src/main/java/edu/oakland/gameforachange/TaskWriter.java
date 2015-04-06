package edu.oakland.gameforachange;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by User on 4/6/2015.
 */
public class TaskWriter {


    public static void writeTask(Task t) {
        Task myTask = t;

        try {
            FileOutputStream fs = new FileOutputStream(Splash.dir + "task.txt");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(myTask);
            os.close();
            fs.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
