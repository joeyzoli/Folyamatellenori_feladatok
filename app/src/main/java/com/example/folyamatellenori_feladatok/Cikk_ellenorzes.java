package com.example.folyamatellenori_feladatok;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

public class Cikk_ellenorzes extends AppCompatActivity
{
    TextView nxt_mezo;
    TextView cikkszam;
    TextView idoo1;TextView idoo2;TextView idoo3;TextView idoo4;TextView idoo5;TextView idoo6;TextView idoo7;TextView idoo8;TextView idoo9;TextView idoo10;TextView idoo11;TextView idoo12;
    TextView idoo13;TextView idoo14;TextView idoo15;TextView idoo16;
    EditText cikk1;
    EditText batch1;
    EditText vizsgalt1;
    EditText hiba1;
    EditText arany1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cikk_ellenorzes);
        nxt_mezo = findViewById(R.id.nxt_mezo4);
        cikkszam = findViewById(R.id.cikkszam_mezo4);
        Intent beillesztes2 = getIntent();
        String nxt = beillesztes2.getStringExtra("Kuldo");
        String cik = beillesztes2.getStringExtra("Cikkszam");
        nxt_mezo.setText(nxt);
        cikkszam.setText(cik);
        muszakido();
    }

    public void ujoldal_nxt(View view)
    {
        new Mentes().execute();
        Intent intent = new Intent(Cikk_ellenorzes.this, nxt_valasztas.class);
        startActivity(intent);
    }

    private void muszakido(){
        idoo1 = findViewById(R.id.ido1);idoo2 = findViewById(R.id.ido2);idoo3 = findViewById(R.id.ido3);idoo4 = findViewById(R.id.ido4);idoo5 = findViewById(R.id.ido5);idoo6 = findViewById(R.id.ido6);
        idoo7 = findViewById(R.id.ido7);idoo8 = findViewById(R.id.ido8);idoo9 = findViewById(R.id.ido9);idoo10 = findViewById(R.id.ido10);idoo11 = findViewById(R.id.ido11);idoo12 = findViewById(R.id.ido12);
        idoo13 = findViewById(R.id.ido13);idoo14 = findViewById(R.id.ido14);idoo15 = findViewById(R.id.ido15);idoo16 = findViewById(R.id.ido16);
        idoo1.setText(MainActivity.muszak.get(0)[0]);
        idoo2.setText(MainActivity.muszak.get(0)[1]);
        idoo3.setText(MainActivity.muszak.get(0)[2]);
        idoo4.setText(MainActivity.muszak.get(0)[3]);
        idoo5.setText(MainActivity.muszak.get(0)[4]);
        idoo6.setText(MainActivity.muszak.get(0)[5]);
        idoo7.setText(MainActivity.muszak.get(0)[6]);
        idoo8.setText(MainActivity.muszak.get(0)[7]);
        idoo9.setText(MainActivity.muszak.get(0)[8]);
        idoo10.setText(MainActivity.muszak.get(0)[9]);
        idoo11.setText(MainActivity.muszak.get(0)[10]);
        idoo12.setText(MainActivity.muszak.get(0)[11]);
        idoo13.setText(MainActivity.muszak.get(0)[12]);
        idoo14.setText(MainActivity.muszak.get(0)[13]);
        idoo15.setText(MainActivity.muszak.get(0)[14]);
        idoo16.setText(MainActivity.muszak.get(0)[15]);
    }

    @SuppressLint("StaticFieldLeak")
    public class Mentes extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();
            CheckBox ref1 = findViewById(R.id.ref1);
            cikk1 = findViewById(R.id.cikk1_mezo);
            batch1 = findViewById(R.id.batch1_mezo);
            vizsgalt1 = findViewById(R.id.vizsgalt1_mezo);
            hiba1 = findViewById(R.id.hiba1_mezo);
            arany1 = findViewById(R.id.arany1_mezo);
            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                if(ref1.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo1.getText().toString() +
                            "','"+ folyamat +"','"+ cikk1.getText().toString() +"','"+ batch1.getText().toString() +"','"+ vizsgalt1.getText().toString() +
                            "','"+ hiba1.getText().toString() +"','"+ arany1.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }


            } catch (Exception e) {
                System.out.println(e);
            }

            return info;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class Visszatolt extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();



            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                String sql = "INSERT INTO  ";
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