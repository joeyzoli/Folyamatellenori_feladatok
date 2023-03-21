package com.example.folyamatellenori_feladatok;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class nxt_valasztas extends AppCompatActivity
{
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nxt_valasztas);
        spinner = (Spinner)findViewById(R.id.nxt_box);
    }

    public void ujoldal(View view)
    {
        //elso_ellenorzes.nxt = spinner.getSelectedItem().toString();
        new DB_iras().execute();
        Intent intent = new Intent(nxt_valasztas.this, elso_ellenorzes.class);
        intent.putExtra("Kuldo", spinner.getSelectedItem().toString() );
        startActivity(intent);
    }

    public void kezdooldal(View view)
    {
        //elso_ellenorzes.nxt = spinner.getSelectedItem().toString();
        Intent intent = new Intent(nxt_valasztas.this, MainActivity.class);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    public class DB_iras extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();


            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                String sql = "UPDATE qualitydb.Folyamatellenori_alap set NXT ='"+ spinner.getSelectedItem().toString()
                        +"' where id = '"+ MainActivity.id +"'" ;
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.executeUpdate();

            } catch (Exception e) {
                Log.e("DB_iras", "Error reading school information", e);
            }

            return info;
        }
    }

}