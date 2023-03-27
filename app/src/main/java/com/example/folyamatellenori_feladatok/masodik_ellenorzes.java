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
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class masodik_ellenorzes extends AppCompatActivity
{
    String csek1= "";String csek2= "";String csek3= "";String csek4= "";String csek5= "";String csek6= "";
    String csek7= "";String csek8= "";String csek9= "";String csek10= "";String csek11= "";String csek12= "";
    String csek13= "";String csek14= "";
    EditText gomb1; EditText gomb2;EditText gomb3;EditText gomb4;EditText gomb5;
    EditText gomb6;EditText gomb7;EditText gomb8;EditText gomb9;
    EditText gomb10;EditText gomb11;EditText gomb12;EditText gomb13;EditText gomb14;
    TextView nxt_mezo;
    TextView cikkszam;
    EditText megjegyzes;
    Boolean van;
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
        new Visszatolt().execute();
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
        intent.putExtra("Kuldo", nxt_mezo.getText().toString() );
        intent.putExtra("Cikkszam", cikkszam.getText().toString() );
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    public class Csekk2 extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();
            System.out.println("Csekk2 ****************************************************************************");
            gomb1 = findViewById(R.id.csekk2_1);gomb2 = findViewById(R.id.csekk2_2);gomb3 = findViewById(R.id.csekk2_3);
            gomb4 = findViewById(R.id.csekk2_4);gomb5 = findViewById(R.id.csekk2_5);gomb6 = findViewById(R.id.csekk2_6);
            gomb7 = findViewById(R.id.csekk2_7);gomb8 = findViewById(R.id.csekk2_8);gomb9 = findViewById(R.id.csekk2_9);
            gomb10 = findViewById(R.id.csekk2_10);gomb11 = findViewById(R.id.csekk2_11);gomb12 = findViewById(R.id.csekk2_12);
            gomb13 = findViewById(R.id.csekk2_13);gomb14 = findViewById(R.id.csekk2_14);
            megjegyzes = findViewById(R.id.megjegyzes_mezo);

            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                if(van == false) {
                    String sql = "INSERT INTO  qualitydb.Folyamatellenori_gyartas (Nev, Datum, nxt, Cikkszam, Csekk1,Csekk2,Csekk3,Csekk4,Csekk5,Csekk6,Csekk7,Csekk8,Csekk9" +
                            ",Csekk10,Csekk11,Csekk12,Csekk13,Csekk14, Megjegyzes) Values('" + MainActivity.Nev + "', '" + MainActivity.Datum +
                            "', '" + nxt_mezo.getText().toString() + "', '" + cikkszam.getText().toString() + "','"+ gomb1.getText().toString() + "','"+ gomb2.getText().toString() +
                            "','"+ gomb3.getText().toString() + "','"+ gomb4.getText().toString() +"','"+ gomb5.getText().toString() +"','"+ gomb6.getText().toString() +"','"+
                            gomb7.getText().toString() +"','"+gomb8.getText().toString() +"','"+gomb9.getText().toString() +"','"+gomb10.getText().toString() +"','"+
                            gomb11.getText().toString() +"','"+gomb12.getText().toString() +"','"+gomb13.getText().toString() +"','"+gomb14.getText().toString() +"','"+
                            megjegyzes.getText().toString() + "')";

                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
                else{
                    String sql = "UPDATE qualitydb.Folyamatellenori_gyartas set Csekk1 = '" + csek1 +
                            "', Csekk2 = '" + csek2 + "', Csekk3 = '" + csek3 + "', Csekk4 = '" + csek4 + "', Csekk5 = '" + csek5 +
                            "', Csekk6 = '" + csek6 + "', Csekk7 = '" + csek7 + "', Csekk8 = '" + csek8 + "', Csekk9 ='" + csek9 +
                            "', Csekk10 = '" + csek10 + "', Csekk11 = '" + csek11 + "', Csekk12 ='" + csek12 + "', Csekk13 = '" + csek13 +
                            "', Csekk14 = '" + csek14 + "'', Megjegzes = '" + megjegyzes.getText().toString() + "'" +
                            " where Nev = '"+ MainActivity.Nev + "' and Datum = '" + MainActivity.Datum +
                            "' and NXT = '" + nxt_mezo.getText().toString() + "' and Cikkszam = '" + cikkszam.getText().toString() + "'";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.executeUpdate();
                }
            }
            catch (Exception e) {
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

            gomb1 = findViewById(R.id.csekk2_1);gomb2 = findViewById(R.id.csekk2_2);gomb3 = findViewById(R.id.csekk2_3);
            gomb4 = findViewById(R.id.csekk2_4);gomb5 = findViewById(R.id.csekk2_5);gomb6 = findViewById(R.id.csekk2_6);
            gomb7 = findViewById(R.id.csekk2_7);gomb8 = findViewById(R.id.csekk2_8);gomb9 = findViewById(R.id.csekk2_9);
            gomb10 = findViewById(R.id.csekk2_10);gomb11 = findViewById(R.id.csekk2_11);gomb12 = findViewById(R.id.csekk2_12);
            gomb13 = findViewById(R.id.csekk2_13);gomb14 = findViewById(R.id.csekk2_14);
            megjegyzes = findViewById(R.id.megjegyzes_mezo);
            System.out.println("Ellenorzés 1 ****************************************************************************");
            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                String sql = "select * from qualitydb.Folyamatellenori_gyartas where nev = '"+ MainActivity.Nev +"' and datum = '"+ MainActivity.Datum +
                        "' and NXT = '"+ nxt_mezo.getText().toString() +"' and Cikkszam ='"+ cikkszam.getText().toString() +"'";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next()){
                    gomb1.setText(resultSet.getString(5));
                    gomb2.setText(resultSet.getString(6));
                    gomb3.setText(resultSet.getString(7));
                    gomb4.setText(resultSet.getString(8));
                    gomb5.setText(resultSet.getString(9));
                    gomb6.setText(resultSet.getString(10));
                    gomb7.setText(resultSet.getString(11));
                    gomb8.setText(resultSet.getString(12));
                    gomb9.setText(resultSet.getString(13));
                    gomb10.setText(resultSet.getString(14));
                    gomb11.setText(resultSet.getString(15));
                    gomb12.setText(resultSet.getString(16));
                    gomb13.setText(resultSet.getString(17));
                    gomb14.setText(resultSet.getString(18));
                    van = true;
                }
                else {
                    van = false;
                }
                System.out.println("ellenőrzés 2 ****************************************************************************");
                System.out.println(van);
            }
            catch (Exception e) {
                System.out.println(e);
            }

            return info;
        }
    }
}