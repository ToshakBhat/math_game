package com.example.math_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button addition;
    Button subtraction;
    Button multiplication;
    Button division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addition = findViewById(R.id.add);
        subtraction = findViewById(R.id.sub);
        multiplication = findViewById(R.id.mul);
        division = findViewById(R.id.div);
        //TextView t = findViewById(R.id.demo);

        addition.setOnClickListener(view -> {
            Intent intent  = new Intent(MainActivity.this, GamePage.class);
            intent.putExtra("val","Addition");
            startActivity(intent);
            finish();
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GamePage.class);
                intent.putExtra("val","Subtraction");
                startActivity(intent);
                finish();
            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GamePage.class);
                intent.putExtra("val","Multiplication");
                startActivity(intent);
                finish();
            }
        });
        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GamePage.class);
                intent.putExtra("val","Division");
                startActivity(intent);
                finish();
            }
        });
    }
}