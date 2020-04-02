package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int currentPlayer=0;
    //2 means unplayed
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){
        ImageView counter=(ImageView)view;
        TextView textView=(TextView)findViewById(R.id.textView);
        Button button=(Button)findViewById(R.id.button);
        Button restart=(Button)findViewById(R.id.restart);
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2){
            gameState[tappedCounter]=currentPlayer;
            counter.setTranslationY(-1000f);
            if(currentPlayer==0){
                counter.setImageResource(R.drawable.doctor);
                currentPlayer=1;
            } else{
                counter.setImageResource(R.drawable.coronavirus);
                currentPlayer=0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
            for(int[] winningPosition: winningPositions){
                if((gameState[winningPosition[0]]==gameState[winningPosition[1]]) &&
                        (gameState[winningPosition[1]]==gameState[winningPosition[2]]) &&
                        (gameState[winningPosition[0]]!=2)){
                    if(currentPlayer==1){
                        textView.setText("Doctor has won");
                    }
                    if(currentPlayer==0){
                        textView.setText("Corona has won");
                    }
                    textView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    restart.setVisibility(View.INVISIBLE);

                }
            }
        }

    }
    public void playAgain(View view){
        TextView textView=(TextView)findViewById(R.id.textView);
        Button restart=(Button)findViewById(R.id.restart);
        Button button=(Button)findViewById(R.id.button);
        textView.setVisibility(View.INVISIBLE);
        restart.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
        currentPlayer=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=(TextView)findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        Button button=(Button)findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

    }
}
