package com.edmond.liarliar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * Created by Ed on 10/10/2017.
 */

public final class Utils {

    private Utils(){}
    final static class LiarLiarData implements Serializable{
        private int index;
        private Boolean[] arr;
        final String word;
        private static String LiarLiarDataFilename = "lld";

        private LiarLiarData(List<Boolean> listIt, String word){
            this.index = 0;
            this.arr = listIt.toArray(new Boolean[listIt.size()]);
            this.word = word;
        }

        boolean hasNext(){
            return index < arr.length;
        }

        boolean getNext(){
            index++;
            return arr[index-1];
        }
    }

    private static final Random RANDOM = new Random();

    public static void start(AppCompatActivity activity, List<Boolean> list){
        saveObj(activity, new LiarLiarData(list, choseNewWord(activity)));
    }
    private static void saveObj(AppCompatActivity activity, LiarLiarData lld){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try(ObjectOutputStream oos = new ObjectOutputStream(bos)){
            oos.writeObject(lld);
            FileOutputStream fos = activity.openFileOutput(LiarLiarData.LiarLiarDataFilename, Context.MODE_PRIVATE);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUpBackIndicator(AppCompatActivity activity) {
        activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_arrow_back);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static LiarLiarData getData(AppCompatActivity activity){
        LiarLiarData lld = null;
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File
                (new File(activity.getFilesDir(),"") +
                        File.separator + LiarLiarData.LiarLiarDataFilename)))) {
            lld = (LiarLiarData) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lld;
    }

    private static String choseNewWord(AppCompatActivity activity){
        InputStream in;
        String word = null;
        String word_list_filename = activity.getResources().getString(R.string.word_list_filename);
        try {
            in = activity.getAssets().open(word_list_filename);
            int numOfWords = numOfWords(new BufferedReader(new InputStreamReader(in)));
            int index = RANDOM.nextInt(numOfWords);
            in = activity.getAssets().open(word_list_filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            int counter = 0;
            while(counter != index){
                word = reader.readLine();
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return word;
    }

    private static int numOfWords(BufferedReader reader){
        int counter = 0;
        try {
            while (reader.readLine() != null) counter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }

    protected static Random getRandom(){
        return RANDOM;
    }

    public static void setUpTitleBar(AppCompatActivity activity){
        Toolbar tb = activity.findViewById(R.id.toolbar);
        tb.setTitle("");
        activity.setSupportActionBar(tb);
        TextView tv = activity.findViewById(R.id.toolbar_title);
        Typeface face = Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.actionbar_font_filename));

        tv.setText(activity.getResources().getString(R.string.app_name));
        tv.setTypeface(face);
    }

    public static double getPercentage(int resId, AppCompatActivity activity){
        return activity.getResources().getInteger(resId)/100d;
    }

}
