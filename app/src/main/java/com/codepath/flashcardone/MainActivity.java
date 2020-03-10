package com.codepath.flashcardone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean isShowingAnswers = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

        // to hide question and display answer when question is clicked
        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_ans3copy).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
            }
        });

        // to hide answer and display the question when answer is clicked
        findViewById(R.id.flashcard_ans3copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_ans3copy).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });


        findViewById(R.id.toggle_choices_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when is set to true
                if (isShowingAnswers) {
                    findViewById(R.id.flashcard_ans1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_ans2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_ans3).setVisibility(View.INVISIBLE);
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.ic_iconmonstr_eye_8);
                    isShowingAnswers = false;
                }
                // when is set to false
                else {
                    findViewById(R.id.flashcard_ans1).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_ans2).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_ans3).setVisibility(View.VISIBLE);
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.ic_iconmonstr_eye_5);
                    isShowingAnswers = true;
                }

            }
        });

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String Question = data.getExtras().getString("Question");
            String Answer = data.getExtras().getString("Answer");
            TextView flashcardQuestion = findViewById(R.id.flashcard_question);
            flashcardQuestion.setText(Question);
            TextView flashcardAnswer = findViewById(R.id.flashcard_ans3copy);
            flashcardAnswer.setText(Answer);

        }
    }
}
