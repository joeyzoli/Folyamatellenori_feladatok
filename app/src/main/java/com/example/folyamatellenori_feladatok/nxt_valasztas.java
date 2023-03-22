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
import java.sql.SQLException;
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
        spinner = findViewById(R.id.nxt_box);
    }

    public void ujoldal(View view)
    {
        new Melyik_oldal().execute();
    }

    public void kezdooldal(View view)
    {
        Intent intent = new Intent(nxt_valasztas.this, MainActivity.class);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    public class Melyik_oldal extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();

            spinner = findViewById(R.id.nxt_box);
            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                String sql = "select * from qualitydb.Folyamatellenori_alap where Ellenor = '"+ MainActivity.Nev +"' and " +
                        "Datum = '"+ MainActivity.Datum +"' and NXT = '"+ spinner.getSelectedItem().toString() +"'"  ;
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next())
                {
                    Intent intent = new Intent(nxt_valasztas.this, Cikk_valaszto.class);
                    intent.putExtra("Kuldo", spinner.getSelectedItem().toString() );
                    startActivity(intent);
                }
                else
                {
                    try (Connection connection2 = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                        String sql2 = "INSERT INTO qualitydb.Folyamatellenori_alap (Datum, Ellenor, Instruktor, Muvez, NXT) " +
                                "VALUES('"+ MainActivity.Datum +"', " +
                                "'"+ MainActivity.Nev +
                                "', '"+ MainActivity.Instruktor +
                                "', '"+ MainActivity.Muvez +
                                "', '"+ spinner.getSelectedItem().toString() +"' )";
                        PreparedStatement statement2 = connection2.prepareStatement(sql2);
                        statement2.executeUpdate();
                    Intent intent = new Intent(nxt_valasztas.this, elso_ellenorzes.class);
                    intent.putExtra("Kuldo", spinner.getSelectedItem().toString() );
                    startActivity(intent);
                } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return info;
        }
    }

}