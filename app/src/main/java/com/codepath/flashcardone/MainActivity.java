package com.codepath.flashcardone;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean isShowingAnswers = true;
    FlashcardDatabase flashcardDatabase;
    // a list of flashcards
    List<Flashcard> allFlashcards;
    // int variable to represent current index of list, allFlashcards
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // loading animation resource files to use
        final Animation leftOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_out);
        final Animation rightIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_in);

        leftOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // this method is called when the animation first starts

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // this method is called when the animation is finished playing
                findViewById(R.id.flashcard_question).startAnimation(rightIn);
                // displaying the next question
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // we don't need to worry about this method
            }
        });

        flashcardDatabase = new FlashcardDatabase(this);
        // flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        // to update local variable holding the list of flashcards
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_ans3copy)).setText(allFlashcards.get(0).getAnswer());
        }


        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // increment index to show the next card
                currentCardDisplayedIndex++;

                // to avoid an IndexOutOfBoundsError to when checking for the last indexed card in the list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }


                // set the question and answer TextViews with data from database => leftOut's onAnimationEnd
                // findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE); => execute animation leftOut below
                findViewById(R.id.flashcard_question).startAnimation(leftOut);
                ((TextView) findViewById(R.id.flashcard_ans3copy)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                findViewById(R.id.flashcard_ans3copy).setVisibility(View.INVISIBLE);

            }
        });

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

        // animation to present correct answer side of flashcard
        final View answerView = findViewById(R.id.flashcard_ans3copy);

        // to hide question and display correct answer when question is clicked => now animation
        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // findViewById(R.id.flashcard_ans3copy).setVisibility(View.VISIBLE);
                // findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);

                // get the center for the clipping circle
                int cx = answerView.getWidth() / 2;
                int cy = answerView.getHeight() / 2;

                // obtain the radius
                float finalRadius = (float) Math.hypot(cx, cy);

                // create the animator for this view
                Animator anim = ViewAnimationUtils.createCircularReveal(answerView, cx, cy, 0f, finalRadius);

                // hide the question and show the correct answer to prepate for playing the animation
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
                answerView.setVisibility(View.VISIBLE);

                anim.setDuration(3000);
                anim.start();
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
                    findViewById(R.id.flashcard_ans1).setVisibility(View.GONE);
                    findViewById(R.id.flashcard_ans2).setVisibility(View.GONE);
                    findViewById(R.id.flashcard_ans3).setVisibility(View.GONE);
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
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

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
            // to insert flashcard
            flashcardDatabase.insertCard(new Flashcard(Question, Answer));

        }
    }


}
