package com.beerquiz.beerq;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.firezenk.bubbleemitter.BubbleEmitterView;

import java.util.Random;

public class SummaryActivity extends AppCompatActivity {



    private Button endButton;
    BubbleEmitterView bubbleEmitter;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        bubbleEmitter = findViewById(R.id.bubbleEmitter);
        endButton = findViewById(R.id.button_end_quiz);
        Intent intent = getIntent();

        int finalScore = intent.getIntExtra("score",0);

        TextView textViewScore = findViewById(R.id.text_view_score);

        textViewScore.setText("Score: "+ finalScore);

        emiterBubbles();

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(SummaryActivity.this,MainActivity.class);
                startActivity(intent);
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
}
