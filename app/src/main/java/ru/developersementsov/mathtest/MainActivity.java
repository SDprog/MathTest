package ru.developersementsov.mathtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent browserIntent;
        switch(id){
            case R.id.about_menu :
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Dmitry+Sementsov"));
                startActivity(browserIntent);
                return true;
            case R.id.privacy_menu:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developersementsov.github.io/App-Privacy-Policy"));
                startActivity(browserIntent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}