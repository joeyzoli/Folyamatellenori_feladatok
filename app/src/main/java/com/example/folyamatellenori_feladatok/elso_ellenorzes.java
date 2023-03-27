package com.example.folyamatellenori_feladatok;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class elso_ellenorzes extends AppCompatActivity
{
    String csek1= "nem";String csek2= "nem";String csek3= "nem";String csek4= "nem";String csek5= "nem";String csek6= "nem";
    String csek7= "nem";String csek8= "nem";String csek9= "nem";
    TextView nxt_mezo;
    RadioButton gomb1; RadioButton gomb2;RadioButton gomb3;RadioButton gomb4;RadioButton gomb5;
    RadioButton gomb6;RadioButton gomb7;RadioButton gomb8;RadioButton gomb9;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elso_ellenorzes);
        nxt_mezo = (TextView) findViewById(R.id.nxt_mezo);
        Intent beillesztes = getIntent();
        String nxt = beillesztes.getStringExtra("Kuldo");
        nxt_mezo.setText(nxt);
    }

    public void ujoldal(View view)
    {
        new Csekk1().execute();
        Intent intent = new Intent(elso_ellenorzes.this, Cikk_valaszto.class);
        intent.putExtra("Kuldo", nxt_mezo.getText().toString() );
        startActivity(intent);
    }

    public void nxt(View view)
    {
        new Csekk1().execute();
        Intent intent = new Intent(elso_ellenorzes.this, nxt_valasztas.class);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    public class Csekk1 extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();

            gomb1 = (RadioButton)findViewById(R.id.csekk1);gomb2 = (RadioButton)findViewById(R.id.csekk2);gomb3 = (RadioButton)findViewById(R.id.csekk3);
            gomb4 = (RadioButton)findViewById(R.id.csekk4);gomb5 = (RadioButton)findViewById(R.id.csekk5);gomb6 = (RadioButton)findViewById(R.id.csekk6);
            gomb7 = (RadioButton)findViewById(R.id.csekk7);gomb8 = (RadioButton)findViewById(R.id.csekk8);gomb9 = (RadioButton)findViewById(R.id.csekk9);

            if(gomb1.isChecked()) {
                csek1 = "igen";
            }
            if(gomb2.isChecked()) {
                csek2 = "igen";
            }
            if(gomb3.isChecked()) {
                csek3 = "igen";
            }
            if(gomb4.isChecked()) {
                csek4 = "igen";
            }
            if(gomb5.isChecked()) {
                csek5 = "igen";
            }
            if(gomb6.isChecked()) {
                csek6 = "igen";
            }
            if(gomb7.isChecked()) {
                csek7 = "igen";
            }
            if(gomb8.isChecked()) {
                csek8 = "igen";
            }
            if(gomb9.isChecked()) {
                csek9 = "igen";
            }
            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                String sql = "UPDATE qualitydb.Folyamatellenori_alap set Csekk1 = '"+ csek1 +
                        "', Csekk2 = '"+ csek2 +"', Csekk3 = '"+ csek3 +"', Csekk4 = '"+ csek4 +"', Csekk5 = '"+ csek5 +
                        "', Csekk6 = '"+ csek6 +"', Csekk7 = '"+ csek7 +"', Csekk8 = '"+ csek8 +"', Csekk9 = '"+ csek9 +
                        "' where Ellenor = '"+ MainActivity.Nev +"' and NXT = '"+ nxt_mezo.getText().toString() +"' and Datum = '"+ MainActivity.Datum +"'" ;
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.executeUpdate();
            }
            catch (Exception e) {
                System.out.println(e);
            }

            return info;
        }
    }
}