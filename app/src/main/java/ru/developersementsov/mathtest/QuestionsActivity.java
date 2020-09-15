package ru.developersementsov.mathtest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {

    private Button trueBtn;
    private Button falseBtn;
    private TextView textView;
    private int currentIndex = 0;
    private Button showAnswer;
    private String TAG = "Информация о запуске метода: ";
    private ListView answersListView;
    private ArrayList<String> answersList = new ArrayList<>();
    private Button restartBtn;
    StringBuilder sb = new StringBuilder();

    private Question[] additionArray = {
            new Question(R.string.addition1, true),
            new Question(R.string.addition2, false),
            new Question(R.string.addition3, true),
            new Question(R.string.addition4, false),
            new Question(R.string.addition5, true)
    };

    private Question[] subtractionArray = {
            new Question(R.string.subtraction1, true),
            new Question(R.string.subtraction2, false),
            new Question(R.string.subtraction3, false),
            new Question(R.string.subtraction4, true),
            new Question(R.string.subtraction5, false)
    };

    private Question[] multiplicationArray = {
            new Question(R.string.multiplication1, true),
            new Question(R.string.multiplication2, false),
            new Question(R.string.multiplication3, false),
            new Question(R.string.multiplication4, false),
            new Question(R.string.multiplication5, true)
    };

    private Question[] divisionArray = {
            new Question(R.string.division1, true),
            new Question(R.string.division2, false),
            new Question(R.string.division3, true),
            new Question(R.string.division4, false),
            new Question(R.string.division5, true)
    };


    private Question[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Метод onCreate() запущен!");
        setContentView(R.layout.activity_questions);


        trueBtn = findViewById(R.id.trueBtn);
        falseBtn = findViewById(R.id.falseBtn);
        textView = findViewById(R.id.textView);
        showAnswer = findViewById(R.id.showAnswer);
        answersListView = findViewById(R.id.answersListView);
        restartBtn = findViewById(R.id.restartBtn);


        if (getIntent().getStringExtra("array").equals("additionArray"))
            questions = additionArray;
        else if (getIntent().getStringExtra("array").equals("subtractionArray"))
            questions = subtractionArray;
        else if (getIntent().getStringExtra("array").equals("multiplicationArray"))
            questions = multiplicationArray;
        else if (getIntent().getStringExtra("array").equals("divisionArray"))
            questions = divisionArray;


        int question = questions[currentIndex].getQuestionTextId();
        textView.setText(question);

        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                updateQuestion();
            }
        });

        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                updateQuestion();
            }
        });

        showAnswer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(QuestionsActivity.this, questions[currentIndex].isAnswerTrue() ? "Верно" : "Неверно", Toast.LENGTH_SHORT).show();
            }
        });

        restartBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "Restart Button Pressed");
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

    private void updateQuestion() {
        currentIndex = (currentIndex + 1) % questions.length;
        textView.setText(questions[currentIndex].getQuestionTextId());
        if (currentIndex == 0) {
            for (int i = 0; i < answersList.size(); i++) {
                sb.append(answersList.get(i));
                sb.append(System.lineSeparator());
            }
            textView.setText("");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, answersList);
            answersListView.setAdapter(adapter);

            showAnswer.setVisibility(View.INVISIBLE);
            trueBtn.setVisibility(View.INVISIBLE);
            falseBtn.setVisibility(View.INVISIBLE);
            restartBtn.setVisibility(View.VISIBLE);
        }
    }


    private void checkAnswer(boolean userAnswer) {
        if (questions[currentIndex].isAnswerTrue() == userAnswer) {
            //Toast.makeText(QuestionsActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            answersList.add("На вопрос (" + textView.getText() + ") Вы ответили Правильно!");
        } else {
            //Toast.makeText(QuestionsActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();

            answersList.add("На вопрос (" + textView.getText() + ") Вы ответили Неправильно!");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "Сохранение onSaveInstanceState() запущено...");
        TextView saveTextView = (TextView) findViewById(R.id.textView);
        ListView saveAnswersListView = (ListView) findViewById(R.id.answersListView);
        savedInstanceState.putInt("index", currentIndex);
        savedInstanceState.putStringArrayList("list", answersList);
        super.onSaveInstanceState(savedInstanceState);
        onDestroy();
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "Восстановление запущено....!");
        currentIndex = savedInstanceState.getInt("index");
        answersList = savedInstanceState.getStringArrayList("list");
        if(currentIndex == 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, answersList);
            answersListView.setAdapter(adapter);
            textView.setText("");
            showAnswer.setVisibility(View.INVISIBLE);
            trueBtn.setVisibility(View.INVISIBLE);
            falseBtn.setVisibility(View.INVISIBLE);
            restartBtn.setVisibility(View.VISIBLE);
        }
       else
           textView.setText(questions[currentIndex].getQuestionTextId());
    }
}