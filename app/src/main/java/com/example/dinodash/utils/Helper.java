package com.example.dinodash.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Helper {
    public static int SCREEN_WIDTH, SCREEN_HEIGHT;

    /**
     *  Get the width and height of the device
     * @param context
     */
    public static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        Helper.SCREEN_WIDTH = dm.widthPixels;
        Helper.SCREEN_HEIGHT = dm.heightPixels;
    }

    public static void writeFileOnInternalStorage(Context context,String filename, String body)  {
        File dir = new File(context.getFilesDir(),"gamedata");
        if(!dir.exists()){
            dir.mkdir();
        }
        try{
            File f = new File(dir, filename);
            FileWriter writer = new FileWriter(f,true);
            writer.append(body);
            writer.flush();
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String readFileFromInternalStorage(Context context, String filename){
        File dir =  new File(context.getFilesDir(),"gamedata");
        try{
            File f = new File(dir, filename);
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line).append("\n");
            }
            return sb.toString();
        }
        catch(FileNotFoundException e){
            return "";
        }
        catch (UnsupportedEncodingException e){
            return "";
        }
        catch (IOException e){
            return "";
        }

    }

    public static void writeHighScore(Context context,int points){
        writeFileOnInternalStorage(context,"highscores.txt",points+"\n");
    }

    public static String readHighScore(Context context){
        return readFileFromInternalStorage(context,"highscores.txt");
    }
}
