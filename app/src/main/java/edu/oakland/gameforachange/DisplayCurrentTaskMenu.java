package edu.oakland.gameforachange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    private TextView txtView;
    private static final String TAG = "MEDIA";

    public int counter;



    /**
     * Creates the activity. -Dean
     * @param icicle
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.displaycurrenttaskmenu);

        txtView = (TextView)findViewById(R.id.txtCurrentTask);
        txtView.setText("Current Task: " + Splash.task.getTask());


        btnDecrease = (Button)findViewById(R.id.btnCompleteTask);
        btnDecrease.setOnClickListener(this);

        btnIncrease = (Button)findViewById(R.id.btnCompleteTask);
        btnIncrease.setOnClickListener(this);

        // txtView = (TextView) findViewById((R.id.txtScoreInt));
        // txtView.setText(Integer.toString(Splash.task.getScore()));

    }

    private void btnCompleteTaskClick() {
        Toast.makeText(DisplayCurrentTaskMenu.this, "Task Complete, Share Dialog Pops Up Now", Toast.LENGTH_SHORT).show();
        counter = Splash.task.getScore();
        counter++;
        Splash.task.setScore(counter);
        Splash.task.setTaskExists(false);
        Splash.task.setTask(null);
        Splash.taskWriter.writeTask(Splash.task);
        startActivity(new Intent("oakland.edu.gameforachange.MainMenu"));
        finish();

    }


    ///////////////////////////////////////////////////////////////////////////////////////////

    private void btnAbandonTaskClick() {

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


}
