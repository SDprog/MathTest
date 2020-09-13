package ru.developersementsov.mathtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView testSelectionTV;
    Button additionBtn;
    Button subtractionBtn;
    Button multiplicationBtn;
    Button divisionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testSelectionTV = findViewById(R.id.testSelectionTV);
        additionBtn = findViewById(R.id.additionBtn);
        subtractionBtn = findViewById(R.id.subtractionBtn);
        multiplicationBtn = findViewById(R.id.multiplicationBtn);
        divisionBtn = findViewById(R.id.divisionBtn);

        /*additionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
                String str = "addition";
                intent.putExtra("array", str);
                startActivity(intent);
            }
        });
        subtractionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
                String str = "addition";
                intent.putExtra("array", str);
                startActivity(intent);
            }
        });

        multiplicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
                String str = "addition";
                intent.putExtra("array", str);
                startActivity(intent);
            }
        });

        divisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
                String str = "addition";
                intent.putExtra("array", str);
                startActivity(intent);
            }
        });
*/
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.additionBtn:
                action("additionArray");
                break;
            case R.id.subtractionBtn:
                action("subtractionArray");
                break;
            case R.id.multiplicationBtn:
                action("multiplicationArray");
                break;
            case R.id.divisionBtn:
                action("divisionArray");
                break;
        }
    }

    public void action(String array) {
        Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
        intent.putExtra("array", array);
        startActivity(intent);
    }

    /*public void goToQuestions(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
        //intent.putExtra("answer", questions);
        startActivity(intent);
    }*/
}