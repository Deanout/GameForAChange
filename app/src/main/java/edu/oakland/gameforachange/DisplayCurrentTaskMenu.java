package edu.oakland.gameforachange;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button btnAbandonTask;
    private Button btnCompleteTask;
    private TextView txtView;
    private static Context context;

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


        btnAbandonTask = (Button)findViewById(R.id.btnAbandonTask);
        btnAbandonTask.setOnClickListener(this);

        btnCompleteTask = (Button)findViewById(R.id.btnCompleteTask);
        btnCompleteTask.setOnClickListener(this);
    }

    private void btnCompleteTaskClick() {
        Toast.makeText(DisplayCurrentTaskMenu.this, "Task Completed!", Toast.LENGTH_SHORT).show();
        counter = Splash.task.getScore();
        counter++;
        Splash.task.setScore(counter);

        Splash.task.calculateCompletionRatio();
        Splash.task.setTaskExists(false);
        Splash.task.setTask(null);
        Splash.taskWriter.writeTask(Splash.task);

        startActivity(new Intent("oakland.edu.gameforachange.MainMenu"));
        finish();

    }


    ///////////////////////////////////////////////////////////////////////////////////////////

    private void btnAbandonTaskClick() {
        Toast.makeText(DisplayCurrentTaskMenu.this, "Task Abandoned...", Toast.LENGTH_SHORT).show();
        Splash.task.calculateCompletionRatio();
        Splash.task.setTaskExists(false);
        Splash.task.setTask(null);
        Splash.taskWriter.writeTask(Splash.task);

        startActivity(new Intent("oakland.edu.gameforachange.MainMenu"));
        finish();




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
