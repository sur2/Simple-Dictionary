package com.example.simpledictionary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn01;
    private Button btn02;
    private Button btn03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initializeElement();
        this.setListener();
    }

    private void initializeElement() {
        btn01 = findViewById(R.id.Button01);
        btn02 = findViewById(R.id.Button02);
        btn03 = findViewById(R.id.Button03);
    }

    private void setListener() {
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, btn01.getText(), Toast.LENGTH_SHORT).show();
                Intent sample01 = new Intent(MainActivity.this, Sub01Activity.class);
                sample01.putExtra("ActionBar Title", btn01.getText());
                startActivity(sample01);
            }
        });

        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, btn02.getText(), Toast.LENGTH_SHORT).show();
                Intent sample01 = new Intent(MainActivity.this, Sub01Activity.class);
                sample01.putExtra("ActionBar Title", btn02.getText());
                startActivity(sample01);
            }
        });

        btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, btn03.getText(), Toast.LENGTH_SHORT).show();
                Intent sample01 = new Intent(MainActivity.this, Sub01Activity.class);
                sample01.putExtra("ActionBar Title", btn03.getText());
                startActivity(sample01);
            }
        });

    }
}
