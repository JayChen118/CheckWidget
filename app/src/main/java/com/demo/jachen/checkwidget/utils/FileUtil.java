package com.demo.jachen.checkwidget.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by jachen on 12/7/2017.
 */

public class FileUtil {

    public static void save(String json) {
        save(generateFileName("books"), json);
    }

    public static void save(String name, String json) {
        if (makeDirectory()) {
            File directory = getExternalStorageDirectory();
            File file = new File(directory, name);
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(json.getBytes());
                outputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String read(String name) {
        File directory = getExternalStorageDirectory();
        File file = new File(directory, name);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            StringBuilder buffer = new StringBuilder();
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                buffer.append(new String(bytes, 0, length));
            }
            inputStream.close();
            return buffer.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String generateFileName(String prefix) {
        return prefix + System.currentTimeMillis() + ".txt";
    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public static File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("Jay", "Directory not created");
        }
        return file;
    }

    public static File getExternalStorageDirectory() {
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "CheckWidget");
    }

    public static boolean makeDirectory() {
        File file = getExternalStorageDirectory();
        return file.exists() || file.mkdir();
    }
}
