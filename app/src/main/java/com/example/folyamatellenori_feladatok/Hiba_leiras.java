package com.example.folyamatellenori_feladatok;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Hiba_leiras extends AppCompatActivity {

    TextView nev;
    TextView nxt_mezo6;
    int letezik = 0;
    private static final Object zar_5 = new Object();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiba_leiras);
        nxt_mezo6 = findViewById(R.id.nxt_mezo5);
        nev = findViewById(R.id.nev6_mezo);
        nev.setText(MainActivity.Nev);
        nev.setTextColor(Color.BLUE);
        Intent beillesztes2 = getIntent();
        String nxt = beillesztes2.getStringExtra("Kuldo");
        nxt_mezo6.setText(nxt);
        new Hiba_Visszatolt().execute();
        synchronized(zar_5)
        {
            try
            {
                zar_5.wait();			// Várakozunk a jelzésre
            }
            catch (InterruptedException e)
            {
                System.out.print(e);
            }
        }
    }

    public void ujoldal_nxt2(View view)
    {
        new Hiba_Mentes().execute();
        Intent intent = new Intent(Hiba_leiras.this, nxt_valasztas.class);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    public class Hiba_Mentes extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();

            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                String sql2 = "select * from qualitydb.Folyamatellenori_hiba where Nev ='" + MainActivity.Nev + "' and " +
                        "Datum = '" + MainActivity.Datum + "' and NXT = '"+ nxt_mezo6.getText().toString() +"'";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.execute();
                ResultSet eredmeny2 = statement2.getResultSet();
                if (eredmeny2.next()) {
                    letezik = 1;
                }
                TableLayout jtable = (TableLayout) findViewById(R.id.hiba_leir);
                TableRow row = (TableRow) jtable.getChildAt(2);
                EditText hibaleiras = (EditText) row.getChildAt(0);
                EditText hibaok = (EditText) row.getChildAt(1);
                EditText azonnali = (EditText) row.getChildAt(2);
                EditText helyesbito = (EditText) row.getChildAt(3);
                Spinner muvezeto = findViewById(R.id.muvez_csoport);

                String sql = "";

                if (letezik == 1) {
                    sql = "update qualitydb.Folyamatellenori_hiba set Hibaleiras = '"+ hibaleiras.getText().toString() +"', Hibaoka = '"+ hibaok.getText().toString() +"', Azonnali = '"+ azonnali.getText().toString() +
                            "', Helyesbito = '"+ helyesbito.getText().toString() +
                            "', Muvez = '"+ muvezeto.getSelectedItem().toString() +"' where Nev = '"+ MainActivity.Nev +"' and Datum = '"+ MainActivity.Datum +"' and " +
                            " NXT = '"+ nxt_mezo6.getText().toString() +"'" ;

                }
                else {
                    sql = "INSERT INTO qualitydb.Folyamatellenori_hiba (Nev,Datum,NXT,Hibaleiras,Hibaoka,Azonnali,Helyesbito,Muvez) " +
                            "Values('" + MainActivity.Nev + "','" + MainActivity.Datum + "','" + nxt_mezo6.getText().toString() + "','" + hibaleiras.getText().toString() +
                            "','" + hibaok.getText().toString() + "','" + azonnali.getText().toString() + "','" + helyesbito.getText().toString() + "','" + muvezeto.getSelectedItem().toString() + "')";
                }
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
                Looper.prepare();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
            return info;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class Hiba_Visszatolt extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();

            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                String sql = "select * from qualitydb.Folyamatellenori_hiba where Nev ='" + MainActivity.Nev + "' and " +
                        "Datum = '" + MainActivity.Datum + "' and NXT = '"+ nxt_mezo6.getText().toString() +"'";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
                ResultSet eredmeny = statement.getResultSet();

                if (eredmeny.next()) {
                    TableLayout jtable = (TableLayout) findViewById(R.id.hiba_leir);
                    TableRow row = (TableRow) jtable.getChildAt(2);
                    EditText hibaleiras = (EditText) row.getChildAt(0);
                    EditText hibaok = (EditText) row.getChildAt(1);
                    EditText azonnali = (EditText) row.getChildAt(2);
                    EditText helyesbito = (EditText) row.getChildAt(3);
                    Spinner muvezeto = (Spinner) row.getChildAt(4);

                    hibaleiras.setText(eredmeny.getString(4));
                    hibaok.setText(eredmeny.getString(5));
                    azonnali.setText(eredmeny.getString(6));
                    helyesbito.setText(eredmeny.getString(7));
                    muvezeto.setSelection(((ArrayAdapter)muvezeto.getAdapter()).getPosition(eredmeny.getString(8)));
                }

                synchronized (zar_5) {
                    zar_5.notify();        // Értesítjük a zar_2-t, hogy mehet
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e);
            }

            return info;
        }
    }
}