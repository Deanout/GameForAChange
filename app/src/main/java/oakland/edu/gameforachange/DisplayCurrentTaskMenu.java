package oakland.edu.gameforachange;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Dean on 2/21/2015.
 * @author Dean DeHart
 * @version v2.1
 * @since v1.0 150221
 *
 */
public class DisplayCurrentTaskMenu extends Activity implements View.OnClickListener {
    /**
     * This class is largely still a WiP. No comments yet.
     */
    private Button btnDecrease;
    private Button btnIncrease;
    private Button btnGetInt;
    private TextView txtView;
    private static final String TAG = "MEDIA";



    ////////////////////////////////////////////////////////
    public Calculate calculate = new Calculate();
    public int counter = Splash.score;



    /**
     * Creates the activity. -Dean
     * @param icicle
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.displaycurrenttaskmenu);


        btnDecrease = (Button)findViewById(R.id.btnCompleteTask);
        btnDecrease.setOnClickListener(this);

        btnIncrease = (Button)findViewById(R.id.btnCompleteTask);
        btnIncrease.setOnClickListener(this);
    }



    /**
     * Method used to decrease current score. -Dean
     */
    private void btnCompleteTaskClick() {



        /*counter = calculate.decreaseScore(counter);
        writeToSDFile(counter);
        txtView.append(Integer.toString(readFile()));
        */
    }

    private void btnAbandonTaskClick() {
        counter = calculate.increaseScore(counter);
        writeToSDFile(counter);
        txtView.append(Integer.toString(readFile()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCompleteTask:
                btnCompleteTaskClick();
                break;
            case R.id.btnAbandonTask:
                btnAbandonTaskClick();
                break;
            default:
                break;
        }
    }

    /** Method to write ascii text characters to file on SD card. Note that you must add a
     WRITE_EXTERNAL_STORAGE permission to the manifest file or this method will throw
     a FileNotFound Exception because you won't have write permission. */

    private void writeToSDFile(int num){
        String textToWrite;
        int numToWrite = num;
        textToWrite = Integer.toString(numToWrite);
        // Find the root of the external storage.
        // See http://developer.android.com/guide/topics/data/data-  storage.html#filesExternal

        File root = android.os.Environment.getExternalStorageDirectory();
        txtView.append("\nExternal file system root: " + root);

        // See http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

        File dir = new File (root.getAbsolutePath() + "/download");
        dir.mkdirs();
        File file = new File(dir, "textfile");

        try {
            FileOutputStream f = new FileOutputStream(file);
            FileWriter fw = new FileWriter(file);
            fw.write(textToWrite);
            fw.flush();
            fw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i(TAG, "******* File not found. Did you" +
                    " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtView.append("\n\nFile written to " + file);
    }

    public int readFile() {
        int numToRead = 0;
        File root = android.os.Environment.getExternalStorageDirectory();
        txtView.append("\nExternal file system root: " + root);

        // See http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

        File dir = new File (root.getAbsolutePath() + "/download");
        dir.mkdirs();
        File file = new File(dir, "textfile");

        try {
            Scanner scanner = new Scanner(file);
            numToRead = scanner.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return numToRead;
    }


}
