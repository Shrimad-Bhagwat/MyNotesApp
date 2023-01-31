package com.shrimadbhagwat.mynotesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    ImageView add_btn;

    final String[] from = new String[] {DatabaseHelper._ID,
    DatabaseHelper.SUBJECT,DatabaseHelper.DESCRIPTION};

    final int[] to = new int[] {R.id.id, R.id.title, R.id.description};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        add_btn = findViewById(R.id.add_btn);
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));
        adapter = new SimpleCursorAdapter(this,R.layout.activity_view_record,cursor,from,to,0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_note();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView idTextView = view.findViewById(R.id.id);
                TextView titleTextView = view.findViewById(R.id.title);
                TextView descTextView = view.findViewById(R.id.description);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String description = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(),ModifyNoteActivity.class);
                modify_intent.putExtra("id",id);
                modify_intent.putExtra("title",title);
                modify_intent.putExtra("description",description);

                startActivity(modify_intent);



            }
        });


    }


    private void add_note(){

        Intent add_mem = new Intent(MainActivity.this,AddNotesActivity.class);
        startActivity(add_mem);
    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        try {
//
//        if(id == R.id.add_record_btn){
//            Intent add_mem = new Intent(MainActivity.this,AddNotesActivity.class);
//            startActivity(add_mem);
//
//        }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.i("Error","Something went wrong with add button");
//            Log.i("Error",e.toString());
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
}