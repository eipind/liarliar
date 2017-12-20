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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Boolean> playersList;

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
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.go_left_enter, R.anim.go_left_exit);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){}

    public void startSorting(View view) {
        EditText numberOfPlayersText = findViewById(R.id.numOfPlayers);
        Integer players;
        try{
            players = Integer.parseInt(numberOfPlayersText.getText().toString().split(" ")[0]);

            if(!playersValid(players)) return;

            double liars_percentage = Utils.getPercentage(R.integer.max_num_liars_percentage, this);

            int liars = Utils.getRandom().nextInt((int)(players*liars_percentage)) + 1;

            initialiseList(players, liars);
            Utils.start(this, playersList);
            startActivity(SortingActivity.newIntent(this, liars, players));
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
    private void initialiseList(int players, int liars){
        playersList = new ArrayList<Boolean>(players);

        for(int i = 0; i<players; i++){
            playersList.add((i >= liars));
        }

        Collections.shuffle(playersList);
    }

}
