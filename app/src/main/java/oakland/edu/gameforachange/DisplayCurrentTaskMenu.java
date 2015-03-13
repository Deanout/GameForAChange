package oakland.edu.gameforachange;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Dean on 2/21/2015.
 * @author Dean DeHart
 * @version v2.1
 * @since v1.0 150221
 */
public class DisplayCurrentTaskMenu extends Activity implements View.OnClickListener {
    /**
     * This class is largely still a WiP. No comments yet.
     */
    private Button btnDecrease;
    private Button btnIncrease;
    private Button btnGetInt;
    private TextView txtView;
    private AssetManager assetManager;
    private String line = null;
    private ArrayList<Integer> assetVector = new ArrayList();

    ////////////////////////////////////////////////////////

    public String testRead;
    public String testWrite = "4";
    public String testWrite2 = "42";
    public int myInteger;

    /**
     * Creates the activity. -Dean
     * @param icicle
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.displaycurrenttaskmenu);


        btnDecrease = (Button)findViewById(R.id.btnDecrease);
        btnDecrease.setOnClickListener(this);

        btnIncrease = (Button)findViewById(R.id.btnIncrease);
        btnIncrease.setOnClickListener(this);

        btnGetInt = (Button)findViewById(R.id.btnGetInt);
        btnGetInt.setOnClickListener(this);

        txtView = (TextView)findViewById(R.id.txtView);

    }

    private void btnDecreaseClick() {
        try {
            FileOutputStream fos = openFileOutput("test.txt",
                    Context.MODE_APPEND | Context.MODE_WORLD_READABLE);
            fos.write(testWrite.toString().getBytes());
            fos.close();

            String storageState = Environment.getExternalStorageState();
            if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(getExternalFilesDir(null),
                        "test.txt");
                FileOutputStream fos2 = new FileOutputStream(file);
                fos2.write(testWrite.toString().getBytes());
                fos2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void btnIncreaseClick() {
        getInt();
        myInteger = assetVector.get(0);
        myInteger++;
        assetVector.set(1, myInteger);
        txtView.setText(assetVector.get(1).toString());
    }

    private void btnGetIntClick() {
        getInt();
        txtView.setText(assetVector.get(0).toString());
    }

    public void getInt() {
        assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("tasklist.txt");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                line = line.toUpperCase();
                assetVector.add(Integer.parseInt(line));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDecrease:
                btnDecreaseClick();
                break;
            case R.id.btnIncrease:
                btnIncreaseClick();
                break;
            case R.id.btnGetInt:
                btnGetIntClick();
                break;
            default:
                break;
        }
    }
}
