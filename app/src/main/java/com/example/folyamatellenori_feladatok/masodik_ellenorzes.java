package com.example.folyamatellenori_feladatok;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class masodik_ellenorzes extends AppCompatActivity
{
    EditText gomb1; EditText gomb2;
    Spinner gomb3;EditText gomb4;EditText gomb5;
    Spinner gomb6;EditText gomb7;EditText gomb8;EditText gomb9;
    EditText gomb10;EditText gomb11;EditText gomb12;EditText gomb13;EditText gomb14;
    Spinner gomb15;Spinner gomb16;
    TextView nxt_mezo;
    TextView cikkszam;
    EditText megjegyzes;
    private static Object zar_3 = new Object();
    static int van = 0;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
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
        nxt_mezo.setText(nxt + "  "+ MainActivity.Nev);
        nxt_mezo.setTextColor(Color.BLUE);
        cikkszam.setText(cik);
        cikkszam.setTextColor(Color.BLUE);
        new Visszatolt_masodik().execute();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", cikkszam.getText().toString());
        clipboard.setPrimaryClip(clip);
        synchronized(zar_3)
        {
            try
            {
                zar_3.wait();			// Várakozunk a jelzésre
            }
            catch (InterruptedException e)
            {
                System.out.print(e);
            }
        }
    }

    public void nxt(View view)
    {
        new Vicsek2_organelles().execute();
        Intent intent = new Intent(masodik_ellenorzes.this, nxt_valasztas.class);
        startActivity(intent);
    }

    public void ujoldal_cikk(View view)
    {
        new Vicsek2_organelles().execute();
        Intent intent = new Intent(masodik_ellenorzes.this, Cikk_ellenorzes.class);
        intent.putExtra("Kuldo", nxt_mezo.getText().toString() );
        intent.putExtra("Cikkszam", cikkszam.getText().toString() );
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    public class Vicsek2_organelles extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();
            gombok();
            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                String sql2 = "select * from qualitydb.Folyamatellenori_gyartas where Nev ='" + MainActivity.Nev + "' and " +
                        "Datum = '" + MainActivity.Datum + "' and NXT = '"+ nxt_mezo.getText().toString() +"' and Cikkszam = '"+ cikkszam.getText().toString() +"'";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.execute();
                ResultSet resultSet2 = statement2.getResultSet();
                if(resultSet2.next()) {
                    String sql = "UPDATE qualitydb.Folyamatellenori_gyartas set Csekk1 = '" + gomb1.getText().toString() +
                            "', Csekk2 = '" + gomb2.getText().toString() + "', Csekk3 = '" + gomb3.getSelectedItem().toString()  + "', Csekk4 = '" + gomb4.getText().toString()  + "', Csekk5 = '" + gomb5.getText().toString() +
                            "', Csekk6 = '" + gomb6.getSelectedItem().toString() + "', Csekk7 = '" + gomb15.getSelectedItem().toString() + "', Csekk8 = '" + gomb16.getSelectedItem().toString() +
                            "', Csekk9 = '" + gomb7.getText().toString() + "', Csekk10 = '" + gomb8.getText().toString() + "', Csekk11 ='" + gomb9.getText().toString() +
                            "', Csekk12 = '" + gomb10.getText().toString() + "', Csekk13 = '" + gomb11.getText().toString() + "', Csekk14 = '" + gomb12.getText().toString() + "', Csekk15 = '" + gomb13.getText().toString() +
                            "', Csekk16 = '" + gomb14.getText().toString() + "', Megjegyzes = '" + megjegyzes.getText().toString() + "'" +
                            " where Nev = '"+ MainActivity.Nev + "' and Datum = '" + MainActivity.Datum +
                            "' and NXT = '" + nxt_mezo.getText().toString() + "' and Cikkszam = '" + cikkszam.getText().toString() + "'";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                else{
                    String[] nxtsor = nxt_mezo.getText().toString().split(" ");
                    String sql = "INSERT INTO  qualitydb.Folyamatellenori_gyartas (Nev, Datum, nxt, Cikkszam, Csekk1,Csekk2,Csekk3,Csekk4,Csekk5,Csekk6,Csekk7,Csekk8,Csekk9" +
                            ",Csekk10,Csekk11,Csekk12,Csekk13,Csekk14,Csekk15,Csekk16, Megjegyzes) Values('" + MainActivity.Nev + "', '" + MainActivity.Datum +
                            "', '" + nxtsor[0] + "', '" + cikkszam.getText().toString() + "','"+ gomb1.getText().toString() + "','"+ gomb2.getText().toString() +
                            "','"+ gomb3.getSelectedItem().toString() + "','"+ gomb4.getText().toString() +"','"+ gomb5.getText().toString() +"','"+ gomb6.getSelectedItem().toString() +"','"+
                            gomb15.getSelectedItem().toString() +"','"+ gomb16.getSelectedItem().toString() +"','"+
                            gomb7.getText().toString() +"','"+gomb8.getText().toString() +"','"+gomb9.getText().toString() +"','"+gomb10.getText().toString() +"','"+
                            gomb11.getText().toString() +"','"+gomb12.getText().toString() +"','"+gomb13.getText().toString() +"','"+gomb14.getText().toString() +"','"+
                            megjegyzes.getText().toString() + "')";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
            }
            catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
            return info;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class Visszatolt_masodik extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();

            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                String sql = "select * from qualitydb.Folyamatellenori_gyartas where Nev ='" + MainActivity.Nev + "' and " +
                        "Datum = '" + MainActivity.Datum + "' and NXT = '"+ nxt_mezo.getText().toString() +"' and Cikkszam = '"+ cikkszam.getText().toString() +"'";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                gombok();
                if(resultSet.next()){
                    //int resultid = 5;
                    van = 1;
                    /*
                    TableLayout jtable = (TableLayout) findViewById(R.id.masodik_ell);
                    for(int szamlalo = 1; szamlalo < jtable.getChildCount(); szamlalo++) {
                        TableRow row = (TableRow) jtable.getChildAt(szamlalo);
                        EditText valasz = (EditText) row.getChildAt(1);
                        valasz.setText(resultSet.getString(resultid));
                        resultid++;
                    }*/
                    gomb1.setText(resultSet.getString(5));
                    gomb2.setText(resultSet.getString(6));
                    //gomb3.setText(resultSet.getString(7));
                    gomb3.setSelection(((ArrayAdapter)gomb3.getAdapter()).getPosition(resultSet.getString(7)));
                    gomb4.setText(resultSet.getString(8));
                    gomb5.setText(resultSet.getString(9));
                    //gomb6.setText(resultSet.getString(10));
                    gomb6.setSelection(((ArrayAdapter)gomb6.getAdapter()).getPosition(resultSet.getString(10)));
                    gomb15.setSelection(((ArrayAdapter)gomb15.getAdapter()).getPosition(resultSet.getString(11)));
                    gomb16.setSelection(((ArrayAdapter)gomb16.getAdapter()).getPosition(resultSet.getString(12)));
                    gomb7.setText(resultSet.getString(13));
                    gomb8.setText(resultSet.getString(14));
                    gomb9.setText(resultSet.getString(15));
                    gomb10.setText(resultSet.getString(16));
                    gomb11.setText(resultSet.getString(17));
                    gomb12.setText(resultSet.getString(18));
                    gomb13.setText(resultSet.getString(19));
                    gomb14.setText(resultSet.getString(20));
                    megjegyzes.setText(resultSet.getString(21));
                }
                else {
                    van = 0;
                }

                synchronized(zar_3)
                {
                    zar_3.notify();		// Értesítjük a zar_3-t, hogy mehet
                }
            }
            catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }

            return info;
        }
    }

    public void gombok(){
        gomb1 = findViewById(R.id.csekk2_1);gomb2 = findViewById(R.id.csekk2_2);gomb3 = findViewById(R.id.csekk2_3);
        gomb4 = findViewById(R.id.csekk2_4);gomb5 = findViewById(R.id.csekk2_5);gomb6 = findViewById(R.id.csekk2_6);
        gomb7 = findViewById(R.id.csekk2_7);gomb8 = findViewById(R.id.csekk2_8);gomb9 = findViewById(R.id.csekk2_9);
        gomb10 = findViewById(R.id.csekk2_10);gomb11 = findViewById(R.id.csekk2_11);gomb12 = findViewById(R.id.csekk2_12);
        gomb13 = findViewById(R.id.csekk2_13);gomb14 = findViewById(R.id.csekk2_14);
        gomb15 = findViewById(R.id.csekk2_15);gomb16 = findViewById(R.id.csekk2_16);
        megjegyzes = findViewById(R.id.megjegyzes_mezo);
    }

    @Override
    public void onBackPressed() {
        return;
    }
}