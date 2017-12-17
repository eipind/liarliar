package com.edmond.liarliar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SortingActivity extends AppCompatActivity {

    public static final String PLAYERS_ARG = "number_of_players";
    public static final String LIARS_ARG = "number_of_liars";

    private static Scene S1, S2, S3, S4;
    private static ViewGroup VG;
    private static Transition SLIDE_TRANS = new Slide(Gravity.START);
    private String reveal_card_body;
    private int num_of_players;
    private int num_of_liars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);
        Utils.setUpBackIndicator(this);
        setUpScenes();
        num_of_liars = getIntent().getIntExtra(LIARS_ARG,0);
        num_of_players = getIntent().getIntExtra(PLAYERS_ARG,0);
        reveal_card_body = getResources().getQuantityString(R.plurals.reveal_card_body, num_of_liars, num_of_liars);
        showRevealCard();
    }

    public static Intent newIntent(Context context, int liars, int players){
        Intent intent = new Intent(context, SortingActivity.class);
        intent.putExtra(LIARS_ARG, liars);
        intent.putExtra(PLAYERS_ARG, players);
        return intent;
    }
    private void setUpScenes() {
        VG = findViewById(R.id.fLayout);
        S1 = Scene.getSceneForLayout(VG, R.layout.reveal_card, this);
        S2 = Scene.getSceneForLayout(VG, R.layout.word_card, this);
        S3 = Scene.getSceneForLayout(VG, R.layout.liar_card, this);
        S4 = Scene.getSceneForLayout(VG, R.layout.sorting_end, this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.go_right_enter, R.anim.go_right_exit);
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed(){}

    public void onClickReveal(View view){
        if(Utils.getNextPlayer(this)){
            showActivity(State.REVEALING_WORD);
        }else{
            showActivity(State.REVEALING_LIAR);
        }
    }

    public void onClickNextPlayer(View view) {
        if(Utils.hasNextPlayer(this)){
            showActivity(State.HIDING);
        }else{
            showActivity(State.END);
        }
    }

    public void onClickEnd(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.go_left_enter, R.anim.go_left_exit);
    }

    public void showActivity(State s){
        switch(s){
            case HIDING:
                TransitionManager.go(S1, SLIDE_TRANS);
                showRevealCard();
                break;
            case REVEALING_WORD:
                TransitionManager.go(S2, SLIDE_TRANS);
                showWordCard();
                break;
            case REVEALING_LIAR:
                TransitionManager.go(S3, SLIDE_TRANS);
                break;
            case END:
                TransitionManager.go(S4, SLIDE_TRANS);
                break;
        }
    }

    private void showRevealCard() {
        TextView title = (TextView) findViewById(R.id.reveal_card_title);
        TextView body = (TextView) findViewById(R.id.reveal_card_body);
        title.setText(getResources().getQuantityString(R.plurals.reveal_card_tile, num_of_players, num_of_players));
        body.setText(reveal_card_body);
        num_of_players--;
    }

    private void showWordCard(){
        TextView gameWordText = findViewById(R.id.word_card_body);
        gameWordText.setText(Utils.getCurrentWord(this).toUpperCase());
    }

    private enum State {
        HIDING,
        REVEALING_WORD,
        REVEALING_LIAR,
        END
    }
}
