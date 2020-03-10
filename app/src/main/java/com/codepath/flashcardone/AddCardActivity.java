package com.codepath.flashcardone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);


        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editQuestion = ((EditText) findViewById(R.id.editTextQ)).getText().toString();
                String editAnswer = ((EditText) findViewById(R.id.editTextA)).getText().toString();
                Intent data = new Intent();
                data.putExtra("Question", editQuestion);
                data.putExtra("Answer", editAnswer);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
