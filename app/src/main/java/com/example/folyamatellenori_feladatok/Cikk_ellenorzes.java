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
    EditText cikk1;EditText cikk2;EditText cikk3;EditText cikk4;EditText cikk5;EditText cikk6;EditText cikk7;EditText cikk8;EditText cikk9;EditText cikk10;EditText cikk11;EditText cikk12;
    EditText cikk13;EditText cikk14;EditText cikk15;EditText cikk16;
    EditText batch1;EditText batch2;EditText batch3;EditText batch4;EditText batch5;EditText batch6;EditText batch7;EditText batch8;EditText batch9;EditText batch10;EditText batch11;EditText batch12;
    EditText batch13;EditText batch14;EditText batch15;EditText batch16;
    EditText vizsgalt1;EditText vizsgalt2;EditText vizsgalt3;EditText vizsgalt4;EditText vizsgalt5;EditText vizsgalt6;EditText vizsgalt7;EditText vizsgalt8;EditText vizsgalt9;EditText vizsgalt10;EditText vizsgalt11;EditText vizsgalt12;
    EditText vizsgalt13;EditText vizsgalt14;EditText vizsgalt15;EditText vizsgalt16;
    EditText hiba1;EditText hiba2;EditText hiba3;EditText hiba4;EditText hiba5;EditText hiba6;EditText hiba7;EditText hiba8;EditText hiba9;EditText hiba10;EditText hiba11;EditText hiba12;
    EditText hiba13;EditText hiba14;EditText hiba15;EditText hiba16;
    EditText arany1;EditText arany2;EditText arany3;EditText arany4;EditText arany5;EditText arany6;EditText arany7;EditText arany8;EditText arany9;EditText arany10;EditText arany11;EditText arany12;
    EditText arany13;EditText arany14;EditText arany15;EditText arany16;
    CheckBox ref1;CheckBox ref2;CheckBox ref3;CheckBox ref4;CheckBox ref5;CheckBox ref6;CheckBox ref7;CheckBox ref8;CheckBox ref9;CheckBox ref10;CheckBox ref11;CheckBox ref12;CheckBox ref13;CheckBox ref14;CheckBox ref15;CheckBox ref16;
    CheckBox smd1;CheckBox smd2;CheckBox smd3;CheckBox smd4;CheckBox smd5;CheckBox smd6;CheckBox smd7;CheckBox smd8;CheckBox smd9;CheckBox smd10;CheckBox smd11;CheckBox smd12;CheckBox smd13;CheckBox smd14;CheckBox smd15;CheckBox smd16;

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
                for(int szamlalo = 2; szamlalo < jtable.getChildCount(); szamlalo++){
                    TableRow row = (TableRow) jtable.getChildAt(szamlalo);
                    TextView ido1 = (TextView) row.getChildAt(0);
                    String ell_ideje = ido1.getText().toString();
                    CheckBox reff1 = (CheckBox) row.getChildAt(1);
                    CheckBox smdd1 = (CheckBox) row.getChildAt(2);
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
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + ell_ideje +
                            "','"+ folyamat +"','"+ adat1 +"','"+ adat2 +"','"+ adat3 +
                            "','"+ adat4 +"','"+ adat5 +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }

            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
                Toast.makeText(Cikk_ellenorzes.this, "ERROR " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
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
                        "Datum = '" + MainActivity.Datum + "'";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
                ResultSet eredmeny = statement.getResultSet();
                if (eredmeny.next()) {
                    TableLayout jtable = (TableLayout) findViewById(R.id.tabla);
                    for (int szamlalo = 2; szamlalo < jtable.getChildCount(); szamlalo++)
                    {
                        TableRow row = (TableRow) jtable.getChildAt(szamlalo);
                        CheckBox reff1 = (CheckBox) row.getChildAt(1);
                        CheckBox smdd1 = (CheckBox) row.getChildAt(2);
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
                }

            }
            catch (Exception e) {
                System.out.println(e);
            }

            return info;
        }
    }

}