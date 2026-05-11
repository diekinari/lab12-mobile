package com.example.navigation12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "it_geniuses.db";
    private static String DB_LOCATION;
    private static final int DB_VERSION = 1;

    private final Context myContext;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.myContext = context;
        DB_LOCATION = context.getApplicationInfo().dataDir + "/databases/";
        if (!checkDB()) {
            copyDB();
        }
    }

    private boolean checkDB() {
        File fileDB = new File(DB_LOCATION + DB_NAME);
        return fileDB.exists();
    }

    private void copyDB() {
        try {
            // Создаём папку databases, если её нет
            File dbDir = new File(DB_LOCATION);
            if (!dbDir.exists()) {
                dbDir.mkdirs();
            }

            InputStream inputStream = myContext.getAssets().open(DB_NAME);
            File outFile = new File(DB_LOCATION + DB_NAME);
            OutputStream outputStream = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // БД уже создана и скопирована из assets
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // При обновлении — пересоздаём
        File dbFile = new File(DB_LOCATION + DB_NAME);
        if (dbFile.exists()) {
            dbFile.delete();
        }
        copyDB();
    }
}
