package com.example.folyamatellenori_feladatok;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class Cikk_ellenorzes extends AppCompatActivity
{
    TextView nxt_mezo;
    TextView cikkszam;
    TextView idoo1;TextView idoo2;TextView idoo3;TextView idoo4;TextView idoo5;TextView idoo6;TextView idoo7;TextView idoo8;TextView idoo9;TextView idoo10;TextView idoo11;TextView idoo12;
    TextView idoo13;TextView idoo14;TextView idoo15;TextView idoo16;
    static int letezik;
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
        nxt_mezo.setTextColor(Color.BLUE);
        cikkszam.setText(cik);
        cikkszam.setTextColor(Color.BLUE);
        muszakido();
        new Visszatolt().execute();
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

            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                TableLayout jtable = (TableLayout) findViewById(R.id.tabla);
                String folyamat = "";
                String sql2 = "select * from qualitydb.Folyamatellenori_nxt where Nev ='" + MainActivity.Nev + "' and " +
                        "Datum = '" + MainActivity.Datum + "' and NXT = '"+ nxt_mezo.getText().toString() +"'";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.execute();
                ResultSet eredmeny2 = statement2.getResultSet();
                if (eredmeny2.next()) {
                    letezik = 1;
                }
                for(int szamlalo = 2; szamlalo < jtable.getChildCount(); szamlalo++){
                    TableRow row = (TableRow) jtable.getChildAt(szamlalo);
                    TextView ido1 = (TextView) row.getChildAt(0);
                    String ell_ideje = ido1.getText().toString();
                    CheckBox   reff1 = (CheckBox  ) row.getChildAt(1);
                    CheckBox   smdd1 = (CheckBox  ) row.getChildAt(2);
                    EditText cikkk = (EditText) row.getChildAt(3);
                    EditText batchh = (EditText) row.getChildAt(4);
                    EditText vizsgaltt = (EditText) row.getChildAt(5);
                    EditText hibaa = (EditText) row.getChildAt(6);
                    EditText aranyy = (EditText) row.getChildAt(7);
                    String adat1 = cikkk.getText().toString();
                    String adat2 = batchh.getText().toString();
                    String adat3 = vizsgaltt.getText().toString();
                    String adat4 = hibaa.getText().toString();
                    String adat5 = aranyy.getText().toString();

                    if(reff1.isChecked()) {
                        folyamat = "Reflow";
                    }
                    else if(smdd1.isChecked()) {
                        folyamat = "SMD";
                    }
                    else {
                        folyamat = "-";
                    }
                    String sql = "";

                    if (letezik == 1) {
                        sql = "update qualitydb.Folyamatellenori_nxt set Folyamat = '"+ folyamat +"', Cikkszam = '"+ adat1 +"', Batch = '"+ adat2 +"', Vizsgalt_db = '"+ adat3 +
                                "', Hiba_db = '"+ adat4 +"', Hiba_arany = '"+ adat5 +"' where Nev = '"+ MainActivity.Nev +"' and Datum = '"+ MainActivity.Datum +"' and " +
                                " NXT = '"+ nxt_mezo.getText().toString() +"' and Muszak_ido = '"+ ell_ideje +"'" ;

                    }
                    else {
                        sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                                "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + ell_ideje +
                                "','" + folyamat + "','" + adat1 + "','" + adat2 + "','" + adat3 +
                                "','" + adat4 + "','" + adat5 + "')";
                    }
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
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

                String sql = "select * from qualitydb.Folyamatellenori_nxt where Nev ='" + MainActivity.Nev + "' and " +
                        "Datum = '" + MainActivity.Datum + "' and NXT = '"+ nxt_mezo.getText().toString() +"'";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
                ResultSet eredmeny = statement.getResultSet();
                if (eredmeny.next()) {
                    TableLayout jtable = (TableLayout) findViewById(R.id.tabla);
                    for (int szamlalo = 2; szamlalo < jtable.getChildCount(); szamlalo++)
                    {
                        TableRow row = (TableRow) jtable.getChildAt(szamlalo);
                        CheckBox   reff1 = (CheckBox  ) row.getChildAt(1);
                        CheckBox   smdd1 = (CheckBox  ) row.getChildAt(2);
                        EditText cikkk = (EditText) row.getChildAt(3);
                        EditText batchh = (EditText) row.getChildAt(4);
                        EditText vizsgaltt = (EditText) row.getChildAt(5);
                        EditText hibaa = (EditText) row.getChildAt(6);
                        EditText aranyy = (EditText) row.getChildAt(7);
                        if(eredmeny.getString(5).equals("Reflow")) {
                            reff1.setChecked(true);
                        }
                        else if (eredmeny.getString(5).equals("SMD")) {
                            smdd1.setChecked(true);
                        }
                        cikkk.setText(eredmeny.getString(6));
                        batchh.setText(eredmeny.getString(7));
                        vizsgaltt.setText(eredmeny.getString(8));
                        hibaa.setText(eredmeny.getString(9));
                        aranyy.setText(eredmeny.getString(10));
                        eredmeny.next();
                    }
                    letezik = 1;
                }
                else {
                    letezik = 0;
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
            return info;
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }
}