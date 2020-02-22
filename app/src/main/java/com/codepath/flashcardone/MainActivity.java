package com.codepath.flashcardone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // boolean isShowingAnswers; to be implemented at a later time
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // isShowingAnswers = false;


        // setting background color of a wrong choice to red when selected
        findViewById(R.id.flashcard_ans1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_ans1).setBackgroundColor(getResources().getColor(R.color.wrongRed, null));
            }
        });

        // setting background color of a wrong choice to red when selected
        findViewById(R.id.flashcard_ans2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_ans2).setBackgroundColor(getResources().getColor(R.color.wrongRed, null));
            }
        });

        // setting background color of the correct choice to green when selected
        findViewById(R.id.flashcard_ans3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_ans3).setBackgroundColor(getResources().getColor(R.color.correctGreen, null));
            }
        });

        // to reset backgrounds of answer choices & visibility icon by clicking on the background of the app
        findViewById(R.id.rootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_ans1).setBackgroundResource(R.drawable.ans_background);
                findViewById(R.id.flashcard_ans2).setBackgroundResource(R.drawable.ans_background);
                findViewById(R.id.flashcard_ans3).setBackgroundResource(R.drawable.ans_background);
                // ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.ic_iconmonstr_eye_8);
            }
        });

        /* following is code for the required tasks
        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_ans).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.flashcard_ans1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_ans).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });
         */

        /* to be implemented at a later time - visibility icons using boolean isShowingAnswers

        findViewById(R.id.toggle_choices_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowingAnswers = true;
                ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.ic_iconmonstr_eye_5);

            }
        });

        if (isShowingAnswers == true){
            findViewById(R.id.flashcard_ans1).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_ans2).setVisibility(View.INVISIBLE);
            findViewById(R.id.flashcard_ans3).setVisibility(View.INVISIBLE);
        }
        */

    }
}
