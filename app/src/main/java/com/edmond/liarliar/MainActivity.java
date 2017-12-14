package com.edmond.liarliar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.setUpTitleBar(this);
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

            double liars_percentage = Utils.getPercentage(R.integer.max_num_liars_percentage, this);

            int liars = Utils.getRandom().nextInt((int)(players*liars_percentage)) + 1;

            Utils.start(this, createList(players, liars));
            startActivity(intent);
            overridePendingTransition(R.anim.go_left_enter, R.anim.go_left_exit);
        }catch (NumberFormatException e){
            Toast.makeText(this, R.string.number_format_exception, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean playersValid(int players){
        int min_num_of_players = getResources().getInteger(R.integer.min_num_players);
        if(players <= min_num_of_players ||
                players >  getResources().getInteger(R.integer.max_num_players)){
            Toast t;
            if(players <= min_num_of_players){
                t = Toast.makeText(this, R.string.num_player_hint_err_less_2, Toast.LENGTH_SHORT);
            }else{
                t = Toast.makeText(this, R.string.num_player_hint_err_more_99, Toast.LENGTH_SHORT);
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

}
