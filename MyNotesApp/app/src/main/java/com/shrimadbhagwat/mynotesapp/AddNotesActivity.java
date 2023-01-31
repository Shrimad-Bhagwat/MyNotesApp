package com.shrimadbhagwat.mynotesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddNotesActivity extends Activity implements View.OnClickListener {


    private Button addButton;
    ImageView backButton;
    private EditText subjectEditText,descEditText;
    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_add_notes_acrivity);
        subjectEditText = findViewById(R.id.subject_edittext);
        descEditText = findViewById(R.id.description_edittext);
        addButton = findViewById(R.id.add_record_btn);

        dbManager = new DBManager(this);
        dbManager.open();
        addButton.setOnClickListener(this);
        backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();
            }
        });

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_record_btn:
                final String name = subjectEditText.getText().toString();
                final String desc = descEditText.getText().toString();
                dbManager.insert(name,desc);

                Intent main = new Intent(AddNotesActivity.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
    }
    public void changeActivity() {
        startActivity(new Intent(this, MainActivity.class));

    }
}