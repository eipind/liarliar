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

import java.util.Random;


/**
 * Created by Ed on 10/10/2017.
 */

public final class Utils {

    private Utils(){}
    private static class LiarLiarData implements Serializable{
        // TODO: implement interface for hasNextStuff
        // TODO: getNextPlayer
        private int index;
        private boolean [] arr;
        private String word;
        public static String LiarLiarDataFilename = "lld";

        public LiarLiarData(int index, boolean [] arr, String word){
            this.index = index;
            this.arr = arr;
            this.word = word;
        }

        public boolean hasNext(){
            return index < arr.length;
        }

        public boolean getNext(){
            index++;
            return arr[index-1];
        }

        public String getWord(){
            return word;
        }
    }

    private static final Random RANDOM = new Random();

    public static void start(AppCompatActivity activity, boolean [] arr){
        saveObj(activity, new LiarLiarData(0, arr, choseNewWord(activity)));
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
        // activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public static LiarLiarData getData(AppCompatActivity activity){
        LiarLiarData lld = null;
        String LIARLIARDATA_FILENAME = LiarLiarData.LiarLiarDataFilename;
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File(new File(activity.getFilesDir(),"")+File.separator+ LIARLIARDATA_FILENAME)))) {
            lld = (LiarLiarData) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lld;
    }

    public static boolean hasNextPlayer(AppCompatActivity activity){
        return getData(activity).hasNext();
    }

    public static boolean getNextPlayer(AppCompatActivity activity){
        LiarLiarData lld = getData(activity);
        boolean res = lld.getNext();
        saveObj(activity, lld);
        return res;
    }

    private static String choseNewWord(AppCompatActivity activity){
        InputStream in;
        String word = null;
        String WORDLIST_FILENAME ="Wordlist.txt";
        try {
            in = activity.getAssets().open(WORDLIST_FILENAME);
            int numOfWords = numOfWords(new BufferedReader(new InputStreamReader(in)));
            int index = RANDOM.nextInt(numOfWords);
            in = activity.getAssets().open(WORDLIST_FILENAME);
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

    public static String getCurrentWord(AppCompatActivity activity) {
        LiarLiarData lld = getData(activity);
        return lld.getWord();
    }

    protected static Random getRandom(){
        return RANDOM;
    }

    public static void setUpToolBar(AppCompatActivity activity, String message){
        Toolbar tb = activity.findViewById(R.id.toolbar);
        tb.setTitle("");
        activity.setSupportActionBar(tb);
        TextView tv = activity.findViewById(R.id.toolbar_title);
        Typeface face = Typeface.createFromAsset(activity.getAssets(), activity.getResources().getString(R.string.actionbar_font_filename));

        tv.setText(message);
        tv.setTypeface(face);
    }

    public static void setUpTitleBar(AppCompatActivity activity){
        setUpToolBar(activity, activity.getResources().getString(R.string.app_name));
        Toolbar tb = activity.findViewById(R.id.toolbar);
    }

    public static double getPercentage(int resId, AppCompatActivity activity){
        int percentage = activity.getResources().getInteger(resId);
        return (double) percentage/100d;
    }

}
