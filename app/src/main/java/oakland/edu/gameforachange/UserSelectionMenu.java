package oakland.edu.gameforachange;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dean on 2/21/2015.
 */
public class UserSelectionMenu extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button acceptTask;
    private Button assignTask;
    private ListView taskSelection;

    public Button assignTaskButton;



    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.userselectionmenu_fullscreen);

        acceptTask = (Button) findViewById(R.id.acceptTaskButton);
        assignTask = (Button) findViewById(R.id.assignTaskButton);
        acceptTask.setOnClickListener(this);

        taskSelection = (ListView) findViewById(R.id.taskSelectionBox);
        ArrayList<String> taskChoices = new ArrayList<>();
        taskChoices.add("Open the door for someone");
        taskChoices.add("Buy Dean some food");


        // This is an array adapter. It takes the context of the activity as a first parameter,
        // the type of list view as a second parameter, and an array as a third.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                taskChoices);
        taskSelection.setAdapter(arrayAdapter);
        taskSelection.setOnItemClickListener(this);

        assignTaskButton = (Button)findViewById(R.id.assignTaskButton);
        assignTaskButton.setOnClickListener(this);
    }

    private void acceptTaskClick() {
        startActivity(new Intent("oakland.edu.gameforachange.DisplayCurrentTaskMenu"));
    }

    private void assignTaskClick() {
       startActivity(new Intent("oakland.edu.gameforachange.AssignUserMenu"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.acceptTaskButton:
                acceptTaskClick();
                break;
            case R.id.assignTaskButton:
                assignTaskClick();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        String item = adapter.getItemAtPosition(position).toString();
        Toast.makeText(UserSelectionMenu.this, "You clicked on: " + item, Toast.LENGTH_SHORT).show();
    }
}
