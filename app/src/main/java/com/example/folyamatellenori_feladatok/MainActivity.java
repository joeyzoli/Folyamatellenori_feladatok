package com.example.folyamatellenori_feladatok;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    static final String URL = "jdbc:mysql://172.20.22.29";
    static final String USER = "veasquality";
    static final String PASSWORD = "kg6T$kd14TWbs9&gd";
    static int id;
    static String Nev;
    static String Datum;
    EditText datum;
    EditText ellenor;
    EditText instruktor;
    EditText muvez;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
    Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datum = findViewById(R.id.datum_mezo);
        datum.setText(formatter.format(date), TextView.BufferType.EDITABLE);
    }

    public void ujoldal(View view)
    {
        new DB_iras().execute();
        Intent intent = new Intent(MainActivity.this, nxt_valasztas.class);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    public class DB_iras extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();
            datum = findViewById(R.id.datum_mezo);
            ellenor = findViewById(R.id.nev_mezo);
            instruktor = findViewById(R.id.instruktor_mezo);
            muvez = findViewById(R.id.muvez_mezo);
            Nev = ellenor.getText().toString();
            Datum = datum.getText().toString();
                try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_alap (Datum, Ellenor, Instruktor, Muvez) " +
                            "VALUES('"+ datum.getText().toString() +"', " +
                            "'"+ ellenor.getText().toString() +
                            "', '"+ instruktor.getText().toString() +
                            "', '"+ muvez.getText().toString() +"' )";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                    String sql2 = "select id from qualitydb.Folyamatellenori_alap where Datum = '"+ datum.getText().toString() +
                            "' and Ellenor ='"+ ellenor.getText().toString() +"'";
                    PreparedStatement stmt = connection.prepareStatement(sql2);
                    stmt.execute();
                    ResultSet resultSet = stmt.getResultSet();
                    if(resultSet.next()) {
                        id = Integer.parseInt(resultSet.getString(1));
                    }
                    System.out.println(id + "******************************************************************");
                }
                catch (Exception e) {
                    System.out.println(e);
                }

            return info;
        }
    }
}