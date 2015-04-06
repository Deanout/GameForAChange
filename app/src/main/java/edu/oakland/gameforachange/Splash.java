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
 * @version v2.1
 * @since v1.0 150221
 *
 */
public class Splash extends Activity implements View.OnClickListener {
    public TaskReader taskReader;
    public static TaskWriter taskWriter;
    public ImageView proceedButton;
    public static int score = 0;
    public static Task task;
    public static File root = android.os.Environment.getExternalStorageDirectory();
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
        taskReader.readTask();
        if (task == null) {
            task = new Task();
            taskWriter.writeTask(task);
        }
        else {
            task.setFirstRun(false);
            score = task.getScore();
        }


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
     * This switch case is what handles all screen clicks. If the screen region clicked is -Dean
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
