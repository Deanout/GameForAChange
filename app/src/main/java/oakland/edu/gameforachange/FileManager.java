package oakland.edu.gameforachange;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by User on 3/17/2015.
 */
public class FileManager {

    /**
     * Load a <b>File</b> as a <b>String</b>.
     *
     * @param file
     *            The file to load
     * @return The File as a String
     */
    public static String readFromFile(final File file) {
        String ret = "";

        try {
            FileInputStream inputStream = new FileInputStream(file);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * Write a <b>String</b> as a <b>File</b>.
     *
     * @param file
     *            The File where the data will be written
     * @param data
     *            The data to write
     */
    public static void writeToFile(final File file, final String data) {
        new Thread() {
            @Override
            public void run() {
                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                            outputStream);
                    outputStreamWriter.write(data);
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.run();
    }

    /**
     * Private constructor of <b>FileManager</b>. <i>Static class should not be
     * instanced</i>
     */
    private FileManager() {
    }
}