package com.example.navigation12;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ListView listDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowDB = findViewById(R.id.btnShowDB);
        listDB = findViewById(R.id.listDB);

        dbHelper = new DBHelper(this);

        try {
            database = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnShowDB.setOnClickListener(v -> loadData());
    }

    private void loadData() {
        ArrayList<HashMap<String, String>> persons = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM persons", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            HashMap<String, String> person = new HashMap<>();
            person.put("name", cursor.getString(1));
            person.put("achievement", cursor.getString(2));
            persons.add(person);
            cursor.moveToNext();
        }

        cursor.close();

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                persons,
                R.layout.listview_item,
                new String[]{"name", "achievement"},
                new int[]{R.id.textPerson, R.id.textAchievement}
        );

        listDB.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null) {
            database.close();
        }
    }
}
