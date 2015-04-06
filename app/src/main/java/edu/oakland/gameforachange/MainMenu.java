package edu.oakland.gameforachange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;



/**
 * Created by Dean on 2/21/2015.
 * @author Dean DeHart
 * @version v2.2 150313
 * @since v1.0 150221
 *
 */
public class MainMenu extends Activity implements View.OnClickListener {
    public TextView txtView;
    public TextView txtCompletionRatio;
    /**
     * This button takes you to the UserSelectionMenu. -Dean
     */
    public Button chooseTaskButton;
    public String completionRatio;
    DecimalFormat df = new DecimalFormat("##0.00");



    /**
     * Creates the activity. -Dean
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Sets the current view to the mainmenu_fullscreen.xml's specifications. -Dean
         */
        setContentView(R.layout.mainmenu_fullscreen);



        txtView = (TextView) findViewById((R.id.txtScoreInt));
        txtView.setText(Integer.toString(Splash.task.getScore()));

        txtCompletionRatio = (TextView) findViewById(R.id.txtCompletionRatio);
        txtCompletionRatio.setText(Double.toString(Splash.task.getCompletionRatio()));
        txtCompletionRatio.setText(String.valueOf(df.format(Splash.task.getCompletionRatio())));



        /**
         * This code casts the chooseTaskButton from the xml as a button, then assigns it to the chooseTaskButton. -Dean
         */
        chooseTaskButton = (Button)findViewById(R.id.chooseTaskButton);
        /**
         * The onClickListener that causes the button to take you to the next menu. -Dean
         */
        chooseTaskButton.setOnClickListener(this);
        if (!Splash.task.getExist()) {
            chooseTaskButton.setText("Choose Task");
        }
        else {
            chooseTaskButton.setText("View Current Task");
        }



    }


    /**
     * The onClickListener that takes you to the UserSelectionMenu. -Dean
     * If you've already selected a task, will take you straight to the DisplayCurrentTaskMenu. -Dean
     */
    private void chooseTaskButtonClick() {
        if (!Splash.task.getExist()) {
            chooseTaskButton.setText("Choose Task");
            startActivity(new Intent("oakland.edu.gameforachange.UserSelectionMenu"));
            finish();
        }
        else {
            chooseTaskButton.setText("View Current Task");
            startActivity(new Intent("oakland.edu.gameforachange.DisplayCurrentTaskMenu"));
            finish();
        }
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
            /**
             * Default: Something else was clicked. Do nothing.
             */
            default:
                break;
        }
    }

}
