package edu.oakland.gameforachange;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.HttpMethod;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.MessageDialog;
import com.facebook.share.widget.ShareDialog;


import java.io.ByteArrayOutputStream;
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
    private CallbackManager callBackManager;
    private ShareDialog shareDialog;
    private String APP_ID = "400483653459879";


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

        txtView = (TextView)findViewById(R.id.txtCurrentTask);


        btnDecrease = (Button)findViewById(R.id.btnCompleteTask);
        btnDecrease.setOnClickListener(this);

        btnIncrease = (Button)findViewById(R.id.btnCompleteTask);
        btnIncrease.setOnClickListener(this);

        FacebookSdk.sdkInitialize(getApplicationContext());

        callBackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);



    }

    /**
     * Method to read object? -Dean -WIP
     */
    public void setText() {
        txtView.setText(readFile());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Method used to decrease current score. -Dean
     */
    private void btnCompleteTaskClick() {
        Bitmap bitmap = takeScreenshot();
        saveBitmap(bitmap);

        Toast.makeText(DisplayCurrentTaskMenu.this, "Task Complete, Share Dialog Pops Up Now", Toast.LENGTH_SHORT).show();
        counter = calculate.decreaseScore(counter);
        writeToSDFile(counter);
        txtView.append(Integer.toString(readFile()));
        finish();
        /*
        if (shareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Hello Facebook")
                    .setContentDescription(
                            "The 'Hello Facebook' sample  showcases simple Facebook integration")
                    .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                    .build();

            shareDialog.show(linkContent);
        }
        */



    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callBackManager.onActivityResult(requestCode, resultCode, data);

    }


    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }



    ///////////////////////////////////////////////////////////////////////////////////////////

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
