package com.example.abhinav.tictactoedemo;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: ring      1: cross    2: empty

    Button reset ;
    TextView result ;
    int isCross = 1 ;
    boolean gameOn = true ;
    int counter = 0 ;
    int gameState[] = { 2, 2, 2, 2, 2, 2, 2, 2, 2 } ;
    int winningState[][] = { { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };

    private  boolean checkState(){

        for( int[] arr: winningState ){

            if( gameState[arr[0]] != 2 ){

                if( gameState[arr[0]] == gameState[arr[1]] && gameState[arr[1]] == gameState[arr[2]] ){

                    return true ;
                }
            }
        }

        return false ;
    }

    public void dropIn( View view ){

        ImageView token = (ImageView)view ;

        int tokenNo = Integer.parseInt(token.getTag().toString() ) ;

        if( gameState[tokenNo] == 2 && gameOn ) {

            gameState[tokenNo] = isCross ;

            token.setTranslationY(-1500);

            counter++ ;

            if (isCross == 1) {
                token.setImageResource(R.drawable.cross);
                isCross = 0;
            } else {
                token.setImageResource(R.drawable.disk);
                isCross = 1;
            }

            token.animate().translationYBy(1500).setDuration(500);

            if( checkState() || counter == 9 ){

                gameOn = false ;

                String msg ;

                if( checkState() ) {

                    if (isCross == 1)
                        msg = "Circle wins";
                    else
                        msg = "Cross wins";
                }
                else{

                    msg = "Match Draw" ;
                }

                Toast.makeText(this, "GAME OVER !! " + msg , Toast.LENGTH_SHORT).show();

                result.setTranslationY(-1500);
                result.setText( "GAME OVER !! " + msg ) ;
                result.setVisibility(View.VISIBLE);
                result.animate().translationYBy(1500).setDuration(500) ;

                reset.setTranslationY(500);
                reset.setVisibility(View.VISIBLE) ;
                reset.animate().translationYBy(-500).setDuration(500) ;
            }

        }
    }

    public void reset( View view ){

        gameOn = true ;
        isCross = 1 ;
        counter = 0 ;

        for( int i = 0; i < gameState.length; i++ )
            gameState[i] = 2 ;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout) ;

        for( int i = 0; i < gridLayout.getChildCount(); i++ ){

            ImageView token = (ImageView) gridLayout.getChildAt(i) ;
            token.setImageDrawable(null) ;
        }

//        result.animate().translationXBy(1500).setDuration(1500) ;
        result.setVisibility(View.INVISIBLE) ;
//        result.setTranslationX(-1500); ;

//        reset.animate().translationXBy(-1500).setDuration(1500) ;
        reset.setVisibility(View.INVISIBLE) ;
//        reset.setTranslationX(1500) ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = (Button) findViewById(R.id.playAgainButton) ;
        result = (TextView) findViewById(R.id.resultTextView) ;
    }
}
