package com.beerquiz.beerq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.widget.Button;


import org.firezenk.bubbleemitter.BubbleEmitterView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button buttonStartQuiz;
    BubbleEmitterView bubbleEmitter;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bubbleEmitter = findViewById(R.id.bubbleEmitter);
        emiterBubbles();
       buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

    }
    private void emiterBubbles()
    {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int size = new Random().nextInt(81)+20;
                bubbleEmitter.emitBubble(size);
                emiterBubbles();
            }
        },
                new Random().nextInt(301)+100);
    }
    private void startQuiz() {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        startActivity(intent);
    }
}
