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
    static String Instruktor;
    static String Muvez;
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
        datum = findViewById(R.id.datum_mezo);
        ellenor = findViewById(R.id.nev_mezo);
        instruktor = findViewById(R.id.instruktor_mezo);
        muvez = findViewById(R.id.muvez_mezo);
        Nev = ellenor.getText().toString();
        Datum = datum.getText().toString();
        Instruktor = instruktor.getText().toString();
        Muvez = muvez.getText().toString();
        Intent intent = new Intent(MainActivity.this, nxt_valasztas.class);
        startActivity(intent);
    }
}