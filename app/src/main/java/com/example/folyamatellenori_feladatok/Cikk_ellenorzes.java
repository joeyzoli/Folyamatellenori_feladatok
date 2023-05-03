package com.example.folyamatellenori_feladatok;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cikk_ellenorzes extends AppCompatActivity
{
    TextView nxt_mezo;
    TextView cikkszam;
    TextView idoo1;TextView idoo2;TextView idoo3;TextView idoo4;TextView idoo5;TextView idoo6;TextView idoo7;TextView idoo8;TextView idoo9;TextView idoo10;TextView idoo11;TextView idoo12;
    TextView idoo13;TextView idoo14;TextView idoo15;TextView idoo16;
    int letezik = 0;
    TextView nev;
    private static final Object zar_4 = new Object();
    static ArrayList<String> kepnev = new ArrayList<>();
    static ArrayList<String> kephely = new ArrayList<>();
    private Button buttonBrowse;
    int SELECT_PICTURE = 200;
    Uri kepuri;
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
        nev = findViewById(R.id.nev4_mezo);
        nev.setText(MainActivity.Nev);
        nev.setTextColor(Color.BLUE);
        muszakido();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", cikkszam.getText().toString());
        clipboard.setPrimaryClip(clip);
        new Visszatolt().execute();
        synchronized(zar_4)
        {
            try
            {
                zar_4.wait();			// Várakozunk a jelzésre
            }
            catch (InterruptedException e)
            {
                System.out.print(e);
            }
        }
        buttonBrowse = (Button) findViewById(R.id.kep_gomb);
        buttonBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });
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

                }/*
                for(int szamlalo = 0; szamlalo < kephely.size(); szamlalo++) {
                    PreparedStatement stmt = null;
                    //File image = new File(kephely.get(szamlalo));
                    //FileInputStream fis = new FileInputStream (image);
                    //InputStream fis = getContentResolver().openInputStream(kepuri);
                    String sql3 = "INSERT INTO qualitydb.Folyamatellenori_kepek(Nev, Datum, NXT, Cikkszam, Kep_nev) VALUES(?,?,?,?,?)";
                    stmt = connection.prepareStatement(sql3);
                    stmt.setString(1, MainActivity.Nev);
                    stmt.setString(2, MainActivity.Datum);
                    stmt.setString(3, nxt_mezo.getText().toString());
                    stmt.setString(4, cikkszam.getText().toString());
                    stmt.setString(5, kepnev.get(szamlalo));
                    //stmt.setBinaryStream (6, fis, (int) image.length());
                    Toast.makeText(getApplicationContext(), "Lefutott!!", Toast.LENGTH_SHORT).show();
                    stmt.executeUpdate();
                }
                kephely.clear();
                kepnev.clear();*/
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
    public class Visszatolt extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();

            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {

                String sql = "select * from qualitydb.Folyamatellenori_nxt where Nev ='" + MainActivity.Nev + "' and " +
                        "Datum = '" + MainActivity.Datum + "' and NXT = '" + nxt_mezo.getText().toString() + "'";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
                ResultSet eredmeny = statement.getResultSet();

                TableLayout jtable = (TableLayout) findViewById(R.id.tabla);
                for (int szamlalo = 2; szamlalo < jtable.getChildCount(); szamlalo++) {
                    if (eredmeny.next()) {
                        TableRow row = (TableRow) jtable.getChildAt(szamlalo);
                        CheckBox reff1 = (CheckBox) row.getChildAt(1);
                        CheckBox smdd1 = (CheckBox) row.getChildAt(2);
                        EditText cikkk = (EditText) row.getChildAt(3);
                        EditText batchh = (EditText) row.getChildAt(4);
                        EditText vizsgaltt = (EditText) row.getChildAt(5);
                        EditText hibaa = (EditText) row.getChildAt(6);
                        EditText aranyy = (EditText) row.getChildAt(7);
                        if (eredmeny.getString(5).equals("Reflow")) {
                            reff1.setChecked(true);
                        } else if (eredmeny.getString(5).equals("SMD")) {
                            smdd1.setChecked(true);
                        }
                        cikkk.setText(eredmeny.getString(6));
                        batchh.setText(eredmeny.getString(7));
                        vizsgaltt.setText(eredmeny.getString(8));
                        hibaa.setText(eredmeny.getString(9));
                        aranyy.setText(eredmeny.getString(10));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e);
                Intent intent = new Intent(Cikk_ellenorzes.this, Cikk_ellenorzes.class);
                startActivity(intent);
            }
            synchronized (zar_4) {
                zar_4.notify();        // Értesítjük a zar_2-t, hogy mehet
            }
            return info;
        }
    }

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("*/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                kepuri = data.getData();
                if (null != kepuri) {
                    // update the preview image in the layout
                    //IVPreviewImage.setImageURI(selectedImageUri);
                    String[] koztes = kepuri.getPath().split(":");
                    kephely.add(koztes[1]);
                    File fajl = new File(kepuri.getPath());
                    kepnev.add(fajl.getName());
                    Toast.makeText(getApplicationContext(), koztes[1], Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }
}