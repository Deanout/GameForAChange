package edu.oakland.gameforachange;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;



/**
 * Created by Dean on 2/21/2015.
 * @author Dean DeHart
 * @version v3.1 150409
 * @since v1.0 150221
 *
 */
public class Splash extends Activity implements View.OnClickListener {
    /**
     * How long you want the Splash screen to run, before auto redirecting to main menu.
     */
    private int timerTime = 3000;
    /**
     * Creates a public instance of the TaskReader object. This is used to scan in the serializable
     * task object. -Dean
     */
    public TaskReader taskReader;
    /**
     * Creates an instance of the TaskWriter object. This is used to output the serializable task
     * object to a file, allowing for the user's previous information to be reused. -Dean
     */
    public static TaskWriter taskWriter;
    /**
     * This button is the image. If clicked, the user will proceed to the main menu. -Dean
     */
    public ImageView proceedButton;
    /**
     * The number of tasks completed by the user. Initialized to 0. -Dean
     */
    public static int score = 0;
    /**
     * Creates a static instance of the Task object. This object contains all of the necessary
     * variables used in all of the functions. -Dean
     */
    public static Task task;
    /**
     * Establishes the Android root directory. Used to store files in a predetermined location,
     * allowing for deletion, manipulation, or reuse. -Dean
     */
    public static File root = android.os.Environment.getExternalStorageDirectory();
    /**
     * Obtains the absolute path. -Dean
     */
    public static File dir = new File (root.getAbsolutePath() + "/download");

    /**
     * Creates the activity. -Dean
     * @param icicle
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        /**
         * ContentView is the .xml layout file that is essentially the window. -Dean
         */
        setContentView(R.layout.splash);

        /**
         * The proceed button is typecast as ImageView, then assigned to the splashLogo ImageView -Dean
         */
        proceedButton = (ImageView)findViewById(R.id.splashLogo);
        /**
         * The ImageView is treated as a button with this onClickListener -Dean
         */
        proceedButton.setOnClickListener(this);
        /**
         * Calls the read task method from the TaskReader class. This scans in the Task object.
         * If the object does not exist, creates a new object and then writes it to the file.
         * If the object already exists, gets the number of completed tasks and sets the firstRun
         * boolean to false. This boolean is currently unused, though is necessary for future updates. -Dean
         */
        taskReader.readTask();
        if (task == null) {
            task = new Task();
            taskWriter.writeTask(task);
        }
        else {
            task.setFirstRun(false);
            score = task.getScore();
        }

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // your code here
                        splashLogoClick();
                    }
                },
                timerTime
        );


    }





    /**
     * The onClickListener that causes clicking the image to start the next activity. -Dean
     */
    private void splashLogoClick() {
        /**
         * Directs the user to the MainMenu page when the button is clicked. -Dean
         */
        startActivity(new Intent("oakland.edu.gameforachange.MainMenu"));
    }

    /**
     * This switch case is what handles all screen clicks. If the screen region clicked is
     * a button, then the corresponding switch is run. Else, the default is run and nothing happens. -Dean
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * Case 1: The splash image is clicked. -Dean
             */
            case R.id.splashLogo:
                splashLogoClick();
                break;
            /**
             * Default: The splash logo is not clicked. -Dean
             */
            default:
                break;
        }
    }
}
