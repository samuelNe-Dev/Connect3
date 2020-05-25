package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    // rot: 0 , gelb: 1, leer: 2
    int activePlayer = 0;

    int[] gamestates = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;


    Button playAgainButton;
    TextView winnerTextView;

    public void dropIn(View view){
        ImageView field = (ImageView) view;
        int tappedField = Integer.parseInt(field.getTag().toString());
        Log.i("Tag: ", field.getTag().toString());

        if(gamestates[tappedField] == 2 && gameActive){
            gamestates[tappedField] = activePlayer;
            field.setTranslationY(-1500);
            if (activePlayer == 0) {
                field.setImageResource(R.drawable.red);
                activePlayer = 1;
            }
            else if (activePlayer == 1){
                field.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }
            field.animate().translationYBy(1500).setDuration(300);
        }

        for(int[] winningPosition : winningPositions){
            if(gamestates[winningPosition[0]] == gamestates[winningPosition[1]] && gamestates[winningPosition[0]] == gamestates[winningPosition[2]] && gamestates[winningPosition[0]] !=2){
                String winner = "";
                gameActive = false;

                if(activePlayer == 1){
                    winner = "Red";
                }
                else if(activePlayer == 0){
                    winner = "Gelb";
                }

                String winnerText = winner + " won the game!";

                playAgainButton = (Button) findViewById(R.id.button);
                winnerTextView = (TextView) findViewById(R.id.textView);
                winnerTextView.setText(winnerText);
                winnerTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }

    }

    public void resetGame(View view){
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout boardGrid = findViewById(R.id.boardGrid);

        for(int i = 0; i < boardGrid.getChildCount(); i++){
            ImageView field = (ImageView) boardGrid.getChildAt(i);
            field.setImageResource(0);
        }

        Arrays.fill(gamestates, 2);

        activePlayer = 0;
        gameActive = true;



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgainButton = (Button) findViewById(R.id.button);
        winnerTextView = (TextView) findViewById(R.id.textView);

    }
}
