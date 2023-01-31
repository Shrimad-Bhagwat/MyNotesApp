package com.shrimadbhagwat.mynotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ModifyNoteActivity extends Activity implements View.OnClickListener {

    private EditText titleText,descText;
    private Button updateBtn, deleteBtn;
    ImageView backButton;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_modify_note);
        dbManager = new DBManager(this);
        dbManager.open();

        titleText = findViewById(R.id.subject_edittext);
        descText = findViewById(R.id.description_edittext);
        updateBtn = findViewById(R.id.update_record);
        deleteBtn = findViewById(R.id.delete_record);
        backButton = findViewById(R.id.back_btn);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("description");

        _id = Long.parseLong(id);
        titleText.setText(name);
        descText.setText(desc);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();
            }
        });



    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.update_record:

                String name = titleText.getText().toString();
                String desc = descText.getText().toString();
                dbManager.update(_id,name,desc);
                this.returnHome();
                break;


            case R.id.delete_record:
                dbManager.delete(_id);
                this.returnHome();
                break;

        }
    }

    public void returnHome(){
        Intent home_intent = new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);


    }
    public void changeActivity() {
        startActivity(new Intent(this, MainActivity.class));

    }
}