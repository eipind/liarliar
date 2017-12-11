package com.edmond.liarliar;

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

    private static Scene S1, S2, S3, S4;
    private static ViewGroup VG;
    private static Transition SLIDE_TRANS = new Slide(Gravity.START);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);
        Utils.setUpBackIndicator(this);
        setUpScenes();
    }

    private void setUpScenes() {
        VG = findViewById(R.id.fLayout);
        S1 = Scene.getSceneForLayout(VG, R.layout.to_reveal, this);
        S2 = Scene.getSceneForLayout(VG, R.layout.revealing_word, this);
        S3 = Scene.getSceneForLayout(VG, R.layout.fingers_crossed, this);
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

    private void showWord(){
        TextView gameWordText = findViewById(R.id.playing_word_text);
        // gameWordText.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/expressway rg.ttf"));
        gameWordText.setText(Utils.getCurrentWord(this));
    }

    public void showActivity(State s){
        switch(s){
            case HIDING:
                TransitionManager.go(S1, SLIDE_TRANS);
                break;
            case REVEALING_WORD:
                TransitionManager.go(S2, SLIDE_TRANS);
                showWord();
                break;
            case REVEALING_LIAR:
                TransitionManager.go(S3, SLIDE_TRANS);
                break;
            case END:
                TransitionManager.go(S4, SLIDE_TRANS);
                break;
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

    private enum State {
        HIDING,
        REVEALING_WORD,
        REVEALING_LIAR,
        END
    }
}
