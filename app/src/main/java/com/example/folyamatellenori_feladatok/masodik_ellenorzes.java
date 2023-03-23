package com.example.folyamatellenori_feladatok;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

public class masodik_ellenorzes extends AppCompatActivity
{
    String csek1= "nem";String csek2= "nem";String csek3= "nem";String csek4= "nem";String csek5= "nem";String csek6= "nem";
    String csek7= "nem";String csek8= "nem";String csek9= "nem";String csek10= "nem";String csek11= "nem";String csek12= "nem";
    String csek13= "nem";String csek14= "nem";
    RadioButton gomb1; RadioButton gomb2;RadioButton gomb3;RadioButton gomb4;RadioButton gomb5;
    RadioButton gomb6;RadioButton gomb7;RadioButton gomb8;RadioButton gomb9;
    RadioButton gomb10;RadioButton gomb11;RadioButton gomb12;RadioButton gomb13;RadioButton gomb14;
    TextView nxt_mezo;
    TextView cikkszam;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masodik_ellenorzes);
        nxt_mezo = findViewById(R.id.nxt_mezo);
        cikkszam = findViewById(R.id.cikkszam_mezo);
        Intent beillesztes2 = getIntent();
        String nxt = beillesztes2.getStringExtra("Kuldo");
        String cik = beillesztes2.getStringExtra("Cikkszam");
        nxt_mezo.setText(nxt);
        cikkszam.setText(cik);
    }

    public void nxt(View view)
    {
        new Csekk2().execute();
        Intent intent = new Intent(masodik_ellenorzes.this, nxt_valasztas.class);
        startActivity(intent);
    }

    public void ujoldal_cikk(View view)
    {
        new Csekk2().execute();
        Intent intent = new Intent(masodik_ellenorzes.this, Cikk_ellenorzes.class);
        intent.putExtra("Cikkszam", cikkszam.getText().toString() );
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    public class Csekk2 extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();

            gomb1 = (RadioButton)findViewById(R.id.csekk2_1);gomb2 = (RadioButton)findViewById(R.id.csekk2_2);gomb3 = (RadioButton)findViewById(R.id.csekk2_3);
            gomb4 = (RadioButton)findViewById(R.id.csekk2_4);gomb5 = (RadioButton)findViewById(R.id.csekk2_5);gomb6 = (RadioButton)findViewById(R.id.csekk2_6);
            gomb7 = (RadioButton)findViewById(R.id.csekk2_7);gomb8 = (RadioButton)findViewById(R.id.csekk2_8);gomb9 = (RadioButton)findViewById(R.id.csekk2_9);
            gomb10 = (RadioButton)findViewById(R.id.csekk2_10);gomb11 = (RadioButton)findViewById(R.id.csekk2_11);gomb12 = (RadioButton)findViewById(R.id.csekk2_12);
            gomb13 = (RadioButton)findViewById(R.id.csekk2_13);gomb14 = (RadioButton)findViewById(R.id.csekk2_14);

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
            if(gomb10.isChecked()) {
                csek10 = "igen";
            }
            if(gomb11.isChecked()) {
                csek11 = "igen";
            }
            if(gomb12.isChecked()) {
                csek12 = "igen";
            }
            if(gomb13.isChecked()) {
                csek13 = "igen";
            }
            if(gomb13.isChecked()) {
                csek13 = "igen";
            }
            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                String sql = "INSERT INTO  qualitydb.Folyamatellenori_gyartas (Nev, Datum, nxt, Csekk1, Csekk2, Csekk3, Csekk4, Csekk5, Csekk6" +
                        ", Csekk7, Csekk8, Csekk9, Csekk10, Csekk11, Csekk12, Csekk13, Csekk14) Values('"+ MainActivity.Nev +"','"+ MainActivity.Datum +
                        "', '"+ nxt_mezo.getText().toString() +"' ,'"+ csek1 +
                        "','"+ csek2 +"','"+ csek3 +"', '"+ csek4 +"','"+ csek5 +
                        "', '"+ csek6 +"', '"+ csek7 +"','"+ csek8 +"', '"+ csek9 +
                        "', '"+ csek10 +"', '"+ csek11 +"', '"+ csek12 +"', '"+ csek13 +"','"+ csek14 +"')";
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