package com.edmond.liarliar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;
public class MainActivity extends AppCompatActivity implements KeyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.setUpTitleBar(this);
        suffixEditTextView();
    }

    private void suffixEditTextView() {
        final EditText numberOfPlayersText = findViewById(R.id.numOfPlayers);
        numberOfPlayersText.addTextChangedListener(new TextWatcher() {
            private Integer currentNumber = -1;
            private int lengthOfNumber;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try{

                    if(currentNumber != Integer.parseInt(s.toString().split(" ")[0])){

                        currentNumber = Integer.parseInt(s.toString().split(" ")[0]);
                        lengthOfNumber = String.valueOf(currentNumber).length();

                        InputFilter[] filters = s.getFilters();
                        s.setFilters(new InputFilter[] {});
                        s.delete(lengthOfNumber, s.length());

                        if(currentNumber == 1){
                            s.append(" player");
                        }else{
                            s.append(" players");
                        }
                        numberOfPlayersText.setSelection(lengthOfNumber);
                        s.setFilters(filters);
                    }

                }catch(NumberFormatException e){
                    s.clear();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.instructions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_instructions:
                Intent intent = new Intent(this, InstructionsActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "MenuItem (item) not recognised.", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void startSorting(View view) {
        Intent intent = new Intent(this, SortingActivity.class);
        EditText numberOfPlayersText = findViewById(R.id.numOfPlayers);
        Integer players;
        try{
            players = Integer.parseInt(numberOfPlayersText.getText().toString().split(" ")[0]);
            if(!playersValid(players)) return;
            Random r = new Random();
            int liars = r.nextInt((int)(players*0.4)) + 1;
            String word = Utils.choseNewWord(this);
            Utils.start(this, createList(players, liars), word);
            startActivity(intent);
            overridePendingTransition(R.anim.go_left_enter, R.anim.go_left_exit);
        }catch (NumberFormatException e){
            Toast.makeText(this, "Number format not recognised.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean playersValid(int players){
        int min_num_of_players = 2;
        int max_num_of_players = 99;
        if(players <= min_num_of_players ||
                players >  max_num_of_players ){
            Toast t;
            if(players <= min_num_of_players){
                t = Toast.makeText(this, "Must have more than 2 players.", Toast.LENGTH_SHORT);
            }else{
                t = Toast.makeText(this, "Must have less than 99 players.", Toast.LENGTH_SHORT);
            }
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
            return false;
        }
        return true;
    }
    private boolean [] createList(int players, int liars){
        boolean [] playerList = new boolean[players];
        for(int i = 0; i<players; i++){
            playerList[i] = (i >= liars);
        }
        shuffleArray(playerList);
        return playerList;
    }

    private static void shuffleArray(boolean[] array){
        int index;
        Random random = Utils.getRandom();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }

    @Override
    public int getInputType() {
        return 0;
    }

    @Override
    public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyOther(View view, Editable text, KeyEvent event) {
        return false;
    }

    @Override
    public void clearMetaKeyState(View view, Editable content, int states) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
