package edu.oakland.gameforachange;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dean on 2/21/2015.
 * @author Dean DeHart
 * @version v2.1
 * @since v1.0 150221
 *
 */
public class UserSelectionMenu extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, Serializable {
    /**
     * Creates a button object named acceptTaskButton. -Dean
     */
    private Button acceptTaskButton;
    /**
     * Creates the all important listView object that will contain the list of tasks. -Dean
     */
    private ListView taskSelection;
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

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    acceptTaskClick();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };


    /**
     * Creates the activity. -Dean
     * @param icicle
     */
    @Override
    public void onCreate(Bundle icicle) {
        /**
         * Calls the super (activity) -Dean
         */
        super.onCreate(icicle);
        /**
         * Sets the current view to the userselectionmenu_fullscreen.xml's specifications. -Dean
         */
        setContentView(R.layout.userselectionmenu_fullscreen);
        /**
         * This is the listView. The arrayList populates this with the help of the arrayAdapter. -Dean
         */
        taskSelection = (ListView) findViewById(R.id.taskSelectionBox);
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

        /**
         * This is the arrayAdapter. -Dean
         * First parameter: The context of the activity (this). -Dean
         * Second parameter: The type of listview. -Dean
         * Third parameter: An arraylist. -Dean
         */
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                taskChoices);
        /**
         * Assigns the arrayAdapter to the taskSelection listview. -Dean
         */
        taskSelection.setAdapter(arrayAdapter);
        /**
         * The onClickListener for the listview. -Dean
         */
        taskSelection.setOnItemClickListener(this);
        /**
         * Casts the acceptTaskButton as a button, then assigns it to acceptTaskButton. -Dean
         */
        acceptTaskButton = (Button) findViewById(R.id.acceptTaskButton);
        /**
         * The onClickListener for the acceptTaskButton. -Dean
         */
        acceptTaskButton.setOnClickListener(this);



    }

    /**
     * The acceptTaskButton onClickListener. Takes you to the CurrentTaskMenu. -Dean
     */
    private void acceptTaskClick() {

        /**
         * If this button is clicked, task.exists = true.
         */
            Splash.task.setTask(item);
            Splash.task.setTaskExists(true);
            Splash.task.setTasksAccepted(1);
            Splash.taskWriter.writeTask(Splash.task);
            startActivity(new Intent("oakland.edu.gameforachange.DisplayCurrentTaskMenu"));
            Toast.makeText(UserSelectionMenu.this, "Task Accepted!", Toast.LENGTH_SHORT).show();
            finish();


    }

    /**
     * The taskSelection ListView onClickListener. Current does nothing, should save string to file. -Dean
     */
    private void taskSelectionClick() {

    }

    private void confirmDiag() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserSelectionMenu.this);
        builder.setMessage("Are you sure you're okay with: " + Splash.task.getTask() + "?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No, let me pick again.", dialogClickListener).show();

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
             * Case 1: The acceptTaskButton is clicked. -Dean
             */
            case R.id.acceptTaskButton:
                if (Splash.task.getExist()) {
                    confirmDiag();
                }
                else {
                    Toast.makeText(UserSelectionMenu.this, "You haven't selected a task yet!", Toast.LENGTH_SHORT).show();
                }
                break;

            /**
             * Case 2: The ListView is clicked. -Dean
             */
            case R.id.taskSelectionBox:
                taskSelectionClick();
                break;
            /**
             * Default: Something else is clicked. -Dean
             */
            default:
                break;
        }
    }

    /**
     * This beast is what causes the toast when an item in the ListView is clicked. -Dean
     * @param adapter The Adapter, obviously. -Dean
     * @param view Same as the switch case. -Dean
     * @param position The position that was clicked. -Dean
     * @param id The ID. -Dean
     */
    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

        this.item = adapter.getItemAtPosition(position).toString();
        Splash.task.setTaskExists(true);

        Toast.makeText(UserSelectionMenu.this, "You clicked on: " + item, Toast.LENGTH_SHORT).show();
        Splash.task.setTask(item);

    }

}
