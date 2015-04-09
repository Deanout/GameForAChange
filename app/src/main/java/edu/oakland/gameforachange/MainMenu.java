package edu.oakland.gameforachange;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Created by Dean on 2/21/2015.
 * @author Dean DeHart
 * @version v3.1 150409
 * @since v1.0 150221
 *
 */
public class MainMenu extends Activity implements View.OnClickListener {
    /**
     * Used to read from the asset file containing the list of tasks. -Dean
     */
    public InputStream input;
    /**
     * Used to get the individual lines from the asset file. -Dean
     * @see {@link #line}
     */
    public BufferedReader reader;
    /**
     * The String where the parsed lines are stored. -Dean
     */
    public String line;
    /**
     * The arrayList where the tasks are stored. -Dean
     */
    public static ArrayList<String> taskChoices = new ArrayList<>();
    public String item = null;

    public Button btnAssignTask;
    /**
     * Lists the number of tasks the user has successfully completed. -Dean
     */
    public TextView txtView;
    /**
     * Displays the successful completion ratio to the user. -Dean
     */
    public TextView txtCompletionRatio;
    /**
     * This button takes you to the UserSelectionMenu. -Dean
     */
    public Button chooseTaskButton;
    /**
     * Used to format the completion ratio. -Dean
     */
    DecimalFormat df = new DecimalFormat("##0.00");

    Display display;
    Point size = new Point();

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    assignTaskButtonClick();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

    /**
     * Creates the activity. -Dean
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        display = getWindowManager().getDefaultDisplay();
        /**
         * Sets the current view to the mainmenu_fullscreen.xml's specifications. -Dean
         */
        setContentView(R.layout.mainmenu_fullscreen);


        /**
         * Gets the ID of the textview, then casts the task object's score to a string, then
         * displays the number. -Dean
         */
        txtView = (TextView) findViewById((R.id.txtScoreInt));
        txtView.setText(Integer.toString(Splash.task.getScore()));

        /**
         * Same as txtView, however casts the Double to string. -Dean
         */
        txtCompletionRatio = (TextView) findViewById(R.id.txtCompletionRatio);
        txtCompletionRatio.setText(Double.toString(Splash.task.getCompletionRatio()));
        /**
         * Formats the completion ratio.
         */
        txtCompletionRatio.setText(String.valueOf(df.format(Splash.task.getCompletionRatio())));

        btnAssignTask = (Button)findViewById(R.id.btnAssignTask);
        btnAssignTask.setOnClickListener(this);
        /**
         * This code casts the chooseTaskButton from the xml as a button, then assigns it to the chooseTaskButton. -Dean
         */
        chooseTaskButton = (Button)findViewById(R.id.chooseTaskButton);
        /**
         * The onClickListener that causes the button to take you to the next menu. -Dean
         */
        chooseTaskButton.setOnClickListener(this);
        /**
         * Changes the text of the main menu button based on whether the user already has a task. -Dean
         */
        if (!Splash.task.getExist()) {
            chooseTaskButton.setText("Choose Task");
            btnAssignTask.setText("Assign Task");
        }
        else {
            chooseTaskButton.setText("View Current Task");
            display.getSize(size);
            int width = size.x;
            int height = size.y;
            btnAssignTask.setVisibility(View.GONE);
            chooseTaskButton.setWidth(width);

        }
        /**
         * The try catch block that populates the arraylist with the lines from the asset file. -Dean
         */
        try {
            /**
             * Opens the asset file. -Dean
             */
            input = this.getAssets().open("tasklist.txt");
            /**
             * Prepares the reader object. -Dean
             */
            reader = new BufferedReader(new InputStreamReader(input));
            /**
             * This string stores the read line. Will be used in the following while loop in the same manner. -Dean
             * Used here to assign it to a value other than null, so that the while loop can run.  -Dean
             */
            line = reader.readLine();
            /**
             * Adds the first read line to the taskChoices arrayList, which is then added with the arrayList adapter to the listView. -Dean
             */
            taskChoices.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            /**
             * The while loop. Performs same functionality as above, but continues to do so until EOF is reached. -Dean
             */
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    taskChoices.add(line);
                }
                else {
                    /**
                     * Closes the reader if line == null.
                     */
                    reader.close();
                    /**
                     * Closes the inputstream if line == null.
                     */
                    input.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    private void assignTaskButtonClick() {
        int maximum = taskChoices.size();
        int random = randomWithRange(maximum, 1);
        Splash.task.setTask(taskChoices.get(random));
        /**
         * If this button is clicked, task.exists = true.
         */
        Splash.task.setTaskExists(true);
        Splash.task.setTasksAccepted(1);
        Splash.taskWriter.writeTask(Splash.task);
        startActivity(new Intent("oakland.edu.gameforachange.DisplayCurrentTaskMenu"));
        finish();
    }

    /**
     * The onClickListener that takes you to the UserSelectionMenu. -Dean
     * If you've already selected a task, will take you straight to the DisplayCurrentTaskMenu. -Dean
     */
    private void chooseTaskButtonClick() {
        if (!Splash.task.getExist()) {
            startActivity(new Intent("oakland.edu.gameforachange.UserSelectionMenu"));
            finish();
        }
        else {
            startActivity(new Intent("oakland.edu.gameforachange.DisplayCurrentTaskMenu"));
            finish();
        }
    }

    private void confirmDiag() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);
        builder.setMessage("Are you sure you want to have a task assigned to you?").setPositiveButton("Yup!", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

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
             * Case 1: The chooseTaskButton is clicked.
             */
            case R.id.chooseTaskButton:
                chooseTaskButtonClick();
                break;
            case R.id.btnAssignTask:
                confirmDiag();
                break;
            /**
             * Default: Something else was clicked. Do nothing.
             */
            default:
                break;
        }
    }

}
