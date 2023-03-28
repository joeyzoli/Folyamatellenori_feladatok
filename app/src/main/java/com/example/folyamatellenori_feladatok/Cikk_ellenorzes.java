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

            letrehoz();
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
                if(smd1.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo1.getText().toString() +
                            "','"+ folyamat +"','"+ cikk1.getText().toString() +"','"+ batch1.getText().toString() +"','"+ vizsgalt1.getText().toString() +
                            "','"+ hiba1.getText().toString() +"','"+ arany1.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref2.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo2.getText().toString() +
                            "','"+ folyamat +"','"+ cikk2.getText().toString() +"','"+ batch2.getText().toString() +"','"+ vizsgalt2.getText().toString() +
                            "','"+ hiba2.getText().toString() +"','"+ arany2.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd2.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo2.getText().toString() +
                            "','"+ folyamat +"','"+ cikk2.getText().toString() +"','"+ batch2.getText().toString() +"','"+ vizsgalt2.getText().toString() +
                            "','"+ hiba2.getText().toString() +"','"+ arany2.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref3.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo3.getText().toString() +
                            "','"+ folyamat +"','"+ cikk3.getText().toString() +"','"+ batch3.getText().toString() +"','"+ vizsgalt3.getText().toString() +
                            "','"+ hiba3.getText().toString() +"','"+ arany3.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd3.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo3.getText().toString() +
                            "','"+ folyamat +"','"+ cikk3.getText().toString() +"','"+ batch3.getText().toString() +"','"+ vizsgalt3.getText().toString() +
                            "','"+ hiba3.getText().toString() +"','"+ arany3.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref4.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo4.getText().toString() +
                            "','"+ folyamat +"','"+ cikk4.getText().toString() +"','"+ batch4.getText().toString() +"','"+ vizsgalt4.getText().toString() +
                            "','"+ hiba4.getText().toString() +"','"+ arany4.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd4.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo4.getText().toString() +
                            "','"+ folyamat +"','"+ cikk4.getText().toString() +"','"+ batch4.getText().toString() +"','"+ vizsgalt4.getText().toString() +
                            "','"+ hiba4.getText().toString() +"','"+ arany4.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref5.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo5.getText().toString() +
                            "','"+ folyamat +"','"+ cikk5.getText().toString() +"','"+ batch5.getText().toString() +"','"+ vizsgalt5.getText().toString() +
                            "','"+ hiba5.getText().toString() +"','"+ arany5.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd5.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo5.getText().toString() +
                            "','"+ folyamat +"','"+ cikk5.getText().toString() +"','"+ batch5.getText().toString() +"','"+ vizsgalt5.getText().toString() +
                            "','"+ hiba5.getText().toString() +"','"+ arany5.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref6.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo6.getText().toString() +
                            "','"+ folyamat +"','"+ cikk6.getText().toString() +"','"+ batch6.getText().toString() +"','"+ vizsgalt6.getText().toString() +
                            "','"+ hiba6.getText().toString() +"','"+ arany6.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd6.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo6.getText().toString() +
                            "','"+ folyamat +"','"+ cikk6.getText().toString() +"','"+ batch6.getText().toString() +"','"+ vizsgalt6.getText().toString() +
                            "','"+ hiba6.getText().toString() +"','"+ arany6.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref7.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo7.getText().toString() +
                            "','"+ folyamat +"','"+ cikk7.getText().toString() +"','"+ batch7.getText().toString() +"','"+ vizsgalt7.getText().toString() +
                            "','"+ hiba7.getText().toString() +"','"+ arany7.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd7.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo7.getText().toString() +
                            "','"+ folyamat +"','"+ cikk7.getText().toString() +"','"+ batch7.getText().toString() +"','"+ vizsgalt7.getText().toString() +
                            "','"+ hiba7.getText().toString() +"','"+ arany7.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref8.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo8.getText().toString() +
                            "','"+ folyamat +"','"+ cikk8.getText().toString() +"','"+ batch8.getText().toString() +"','"+ vizsgalt8.getText().toString() +
                            "','"+ hiba8.getText().toString() +"','"+ arany8.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd8.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo8.getText().toString() +
                            "','"+ folyamat +"','"+ cikk8.getText().toString() +"','"+ batch8.getText().toString() +"','"+ vizsgalt8.getText().toString() +
                            "','"+ hiba8.getText().toString() +"','"+ arany8.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref9.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo9.getText().toString() +
                            "','"+ folyamat +"','"+ cikk9.getText().toString() +"','"+ batch9.getText().toString() +"','"+ vizsgalt9.getText().toString() +
                            "','"+ hiba9.getText().toString() +"','"+ arany9.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd9.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo9.getText().toString() +
                            "','"+ folyamat +"','"+ cikk9.getText().toString() +"','"+ batch9.getText().toString() +"','"+ vizsgalt9.getText().toString() +
                            "','"+ hiba9.getText().toString() +"','"+ arany9.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref10.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo10.getText().toString() +
                            "','"+ folyamat +"','"+ cikk10.getText().toString() +"','"+ batch10.getText().toString() +"','"+ vizsgalt10.getText().toString() +
                            "','"+ hiba10.getText().toString() +"','"+ arany10.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd10.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo10.getText().toString() +
                            "','"+ folyamat +"','"+ cikk10.getText().toString() +"','"+ batch10.getText().toString() +"','"+ vizsgalt10.getText().toString() +
                            "','"+ hiba10.getText().toString() +"','"+ arany10.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref11.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo11.getText().toString() +
                            "','"+ folyamat +"','"+ cikk11.getText().toString() +"','"+ batch11.getText().toString() +"','"+ vizsgalt11.getText().toString() +
                            "','"+ hiba11.getText().toString() +"','"+ arany11.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd11.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo11.getText().toString() +
                            "','"+ folyamat +"','"+ cikk11.getText().toString() +"','"+ batch11.getText().toString() +"','"+ vizsgalt11.getText().toString() +
                            "','"+ hiba11.getText().toString() +"','"+ arany11.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref12.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo12.getText().toString() +
                            "','"+ folyamat +"','"+ cikk12.getText().toString() +"','"+ batch12.getText().toString() +"','"+ vizsgalt12.getText().toString() +
                            "','"+ hiba12.getText().toString() +"','"+ arany12.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd12.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo12.getText().toString() +
                            "','"+ folyamat +"','"+ cikk12.getText().toString() +"','"+ batch12.getText().toString() +"','"+ vizsgalt12.getText().toString() +
                            "','"+ hiba12.getText().toString() +"','"+ arany12.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref13.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo13.getText().toString() +
                            "','"+ folyamat +"','"+ cikk13.getText().toString() +"','"+ batch13.getText().toString() +"','"+ vizsgalt13.getText().toString() +
                            "','"+ hiba13.getText().toString() +"','"+ arany13.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd13.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo13.getText().toString() +
                            "','"+ folyamat +"','"+ cikk13.getText().toString() +"','"+ batch13.getText().toString() +"','"+ vizsgalt13.getText().toString() +
                            "','"+ hiba13.getText().toString() +"','"+ arany13.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref14.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo14.getText().toString() +
                            "','"+ folyamat +"','"+ cikk14.getText().toString() +"','"+ batch14.getText().toString() +"','"+ vizsgalt14.getText().toString() +
                            "','"+ hiba14.getText().toString() +"','"+ arany14.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd14.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo14.getText().toString() +
                            "','"+ folyamat +"','"+ cikk14.getText().toString() +"','"+ batch14.getText().toString() +"','"+ vizsgalt14.getText().toString() +
                            "','"+ hiba14.getText().toString() +"','"+ arany14.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref15.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo15.getText().toString() +
                            "','"+ folyamat +"','"+ cikk15.getText().toString() +"','"+ batch15.getText().toString() +"','"+ vizsgalt15.getText().toString() +
                            "','"+ hiba15.getText().toString() +"','"+ arany15.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd15.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo15.getText().toString() +
                            "','"+ folyamat +"','"+ cikk15.getText().toString() +"','"+ batch15.getText().toString() +"','"+ vizsgalt15.getText().toString() +
                            "','"+ hiba15.getText().toString() +"','"+ arany15.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(ref16.isChecked()) {
                    String folyamat = "Reflow";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo16.getText().toString() +
                            "','"+ folyamat +"','"+ cikk16.getText().toString() +"','"+ batch16.getText().toString() +"','"+ vizsgalt16.getText().toString() +
                            "','"+ hiba16.getText().toString() +"','"+ arany16.getText().toString() +"')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                if(smd16.isChecked()) {
                    String folyamat = "SMD";
                    String sql = "INSERT INTO qualitydb.Folyamatellenori_nxt (Nev,Datum,NXT,Muszak_ido,Folyamat,Cikkszam,Batch,Vizsgalt_db,Hiba_db,Hiba_arany) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo.getText().toString() + "','" + idoo16.getText().toString() +
                            "','"+ folyamat +"','"+ cikk16.getText().toString() +"','"+ batch16.getText().toString() +"','"+ vizsgalt16.getText().toString() +
                            "','"+ hiba16.getText().toString() +"','"+ arany16.getText().toString() +"')";
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

    private void letrehoz(){
        ref1 = findViewById(R.id.ref1);ref2 = findViewById(R.id.ref2);ref3 = findViewById(R.id.ref3);ref4 = findViewById(R.id.ref4);ref5 = findViewById(R.id.ref5);ref6 = findViewById(R.id.ref6);
        ref7 = findViewById(R.id.ref7);ref8 = findViewById(R.id.ref8);ref9 = findViewById(R.id.ref9);ref10 = findViewById(R.id.ref10);ref11 = findViewById(R.id.ref11);ref12 = findViewById(R.id.ref12);ref13 = findViewById(R.id.ref13);
        ref14 = findViewById(R.id.ref14);ref15 = findViewById(R.id.ref15);ref16 = findViewById(R.id.ref16);

        smd1 = findViewById(R.id.smd1);smd2 = findViewById(R.id.smd2);smd3 = findViewById(R.id.smd3);smd4 = findViewById(R.id.smd4);smd5 = findViewById(R.id.smd5);smd6 = findViewById(R.id.smd6);
        smd7 = findViewById(R.id.smd7);smd8 = findViewById(R.id.smd8);smd9 = findViewById(R.id.smd9);smd10 = findViewById(R.id.smd10);smd11 = findViewById(R.id.smd11);smd12 = findViewById(R.id.smd12);
        smd13 = findViewById(R.id.smd13);smd14 = findViewById(R.id.smd14);smd15 = findViewById(R.id.smd15);smd16 = findViewById(R.id.smd16);

        cikk1 = findViewById(R.id.cikk1_mezo);cikk2 = findViewById(R.id.cikk2_mezo);cikk3 = findViewById(R.id.cikk3_mezo);cikk4 = findViewById(R.id.cikk4_mezo);cikk5 = findViewById(R.id.cikk5_mezo);cikk6 = findViewById(R.id.cikk6_mezo);
        cikk7 = findViewById(R.id.cikk7_mezo);cikk8 = findViewById(R.id.cikk8_mezo);cikk9 = findViewById(R.id.cikk9_mezo);cikk10 = findViewById(R.id.cikk10_mezo);cikk11 = findViewById(R.id.cikk11_mezo);cikk12 = findViewById(R.id.cikk12_mezo);
        cikk13 = findViewById(R.id.cikk13_mezo);cikk14 = findViewById(R.id.cikk14_mezo);cikk15 = findViewById(R.id.cikk15_mezo);cikk16 = findViewById(R.id.cikk16_mezo);

        batch1 = findViewById(R.id.batch1_mezo);batch2 = findViewById(R.id.batch2_mezo);batch3 = findViewById(R.id.batch3_mezo);batch4 = findViewById(R.id.batch4_mezo);batch5 = findViewById(R.id.batch5_mezo);batch6 = findViewById(R.id.batch6_mezo);
        batch7 = findViewById(R.id.batch7_mezo);batch8 = findViewById(R.id.batch8_mezo);batch9 = findViewById(R.id.batch9_mezo);batch10 = findViewById(R.id.batch10_mezo);batch11 = findViewById(R.id.batch11_mezo);batch12 = findViewById(R.id.batch12_mezo);
        batch13 = findViewById(R.id.batch13_mezo);batch14 = findViewById(R.id.batch14_mezo);batch15 = findViewById(R.id.batch15_mezo);batch16 = findViewById(R.id.batch16_mezo);

        vizsgalt1 = findViewById(R.id.vizsgalt1_mezo);vizsgalt2 = findViewById(R.id.vizsgalt2_mezo);vizsgalt3 = findViewById(R.id.vizsgalt3_mezo);vizsgalt4 = findViewById(R.id.vizsgalt4_mezo);vizsgalt5 = findViewById(R.id.vizsgalt5_mezo);vizsgalt6 = findViewById(R.id.vizsgalt6_mezo);
        vizsgalt7 = findViewById(R.id.vizsgalt7_mezo);vizsgalt8 = findViewById(R.id.vizsgalt8_mezo);vizsgalt9 = findViewById(R.id.vizsgalt9_mezo);vizsgalt10 = findViewById(R.id.vizsgalt10_mezo);vizsgalt11 = findViewById(R.id.vizsgalt11_mezo);vizsgalt12 = findViewById(R.id.vizsgalt12_mezo);
        vizsgalt13 = findViewById(R.id.vizsgalt13_mezo);vizsgalt14 = findViewById(R.id.vizsgalt14_mezo);vizsgalt15 = findViewById(R.id.vizsgalt15_mezo);vizsgalt16 = findViewById(R.id.vizsgalt16_mezo);

        hiba1 = findViewById(R.id.hiba1_mezo);hiba2 = findViewById(R.id.hiba2_mezo);hiba3 = findViewById(R.id.hiba3_mezo);hiba4 = findViewById(R.id.hiba4_mezo);hiba5 = findViewById(R.id.hiba5_mezo);hiba6 = findViewById(R.id.hiba6_mezo);
        hiba7 = findViewById(R.id.hiba7_mezo);hiba8 = findViewById(R.id.hiba8_mezo);hiba9 = findViewById(R.id.hiba9_mezo);hiba10 = findViewById(R.id.hiba10_mezo);hiba11 = findViewById(R.id.hiba11_mezo);hiba12 = findViewById(R.id.hiba12_mezo);
        hiba13 = findViewById(R.id.hiba13_mezo);hiba14 = findViewById(R.id.hiba14_mezo);hiba15 = findViewById(R.id.hiba15_mezo);hiba16 = findViewById(R.id.hiba16_mezo);

        arany1 = findViewById(R.id.arany1_mezo);arany2 = findViewById(R.id.arany2_mezo);arany3 = findViewById(R.id.arany3_mezo);arany4 = findViewById(R.id.arany4_mezo);arany5 = findViewById(R.id.arany5_mezo);arany6 = findViewById(R.id.arany6_mezo);
        arany7 = findViewById(R.id.arany7_mezo);arany8 = findViewById(R.id.arany8_mezo);arany9 = findViewById(R.id.arany9_mezo);arany10 = findViewById(R.id.arany10_mezo);arany11 = findViewById(R.id.arany11_mezo);arany12 = findViewById(R.id.arany12_mezo);
        arany13 = findViewById(R.id.arany13_mezo);arany14 = findViewById(R.id.arany14_mezo);arany15 = findViewById(R.id.arany15_mezo);arany16 = findViewById(R.id.arany16_mezo);

    }

}