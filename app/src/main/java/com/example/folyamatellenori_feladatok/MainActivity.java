package com.example.folyamatellenori_feladatok;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    static final String URL = "jdbc:mysql://172.20.22.29?autoReconnect=true&useSSL=false";
    static final String USER = "veasquality";
    static final String PASSWORD = "kg6T$kd14TWbs9&gd";
    static final String Felhasznalo = "veasnxt";
    static final String Jelszo = "Veas8000";
    static final String IP = "jdbc:mysql://172.20.22.68?autoReconnect=true&useSSL=false";
    static String Nev;
    static String Datum;
    static String Instruktor;
    static String Muvez;
    private EditText datum;
    private static final Object zar_1 = new Object();
    Spinner ellenor;
    Spinner instruktor;
    Spinner muvez;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
    Date date = new Date();
    static ArrayList<String[]> muszak = new ArrayList<>();
    ArrayList<String> ellenorok = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String[] de = {"6:00-6:30","6:30-7:00","7:00-7:30","7:30-8:00","8:00-8:30","8:30-9:00","9:00-9:30","9:30-10:00","10:00-10:30","10:30-11:00","11:00-11:30","11:30-12:00","12:00-12:30","12:30-13:00","13:00-13:30","13:30-14:00"};
    String[] du = {"14:00-14:30","14:30-15:00","15:00-15:30","15:30-16:00","16:00-16:30","16:30-17:00","17:00-17:30","17:30-18:00","18:00-18:30","18:30-19:00","19:00-19:30","19:30-20:00","20:00-20:30","20:30-21:00","21:00-21:30","21:30-22:00"};
    String[] ej = {"22:00-22:30","22:30-23:00","23:00-23:30","23:30-0:00","0:00-0:30","0:30-1:00","1:00-1:30","1:30-2:00","2:00-2:30","2:30-3:00","3:00-3:30","3:30-4:00","4:00-4:30","4:30-5:00","5:00-5:30","5:30-6:00"};
    RadioButton De;
    RadioButton Du;
    RadioButton Ej;
    private int muszakell = 0;
    static Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datum = findViewById(R.id.datum_mezo);
        datum.setText(formatter.format(date), TextView.BufferType.EDITABLE);
        new Nevsor().execute();
        synchronized(zar_1)
        {
            try
            {
                zar_1.wait();			// Várakozunk a jelzésre
            }
            catch (InterruptedException e)
            {
                System.out.print(e);
            }
        }
        ellenor = findViewById(R.id.nev_box);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ellenorok);
        ellenor.setAdapter(arrayAdapter);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                new Atallas_ellenorzo().execute();
            }
        }, 0, 1*60*1000); //1 perc*/
    }

    public void ujoldal(View view)
    {
        ellenorzes();
    }

    private void muszak()
    {
        muszak.clear();
        De = findViewById(R.id.csekk_de);
        Du = findViewById(R.id.csekk_du);
        Ej = findViewById(R.id.csekk_ej);
        if(De.isChecked()){
            muszak.add(de);
            muszakell = 1;
        }
        if (Du.isChecked()){
            muszak.add(du);
            muszakell = 1;
        }
        if(Ej.isChecked()){
            muszak.add(ej);
            muszakell = 1;
        }
    }

    public void uzenet(String hibaszoveg){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(hibaszoveg);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void ellenorzes() {
        muszak();
            if (muszakell < 1) {
                uzenet("Nem választottál ki műszakot!!");
            }
            else {
                datum = findViewById(R.id.datum_mezo);
                ellenor = findViewById(R.id.nev_box);
                instruktor = findViewById(R.id.instruktor_mezo);
                muvez = findViewById(R.id.muvez_mezo);
                Nev = ellenor.getSelectedItem().toString();
                Datum = datum.getText().toString();
                Instruktor = instruktor.getSelectedItem().toString();
                Muvez = muvez.getSelectedItem().toString();
                Intent intent = new Intent(MainActivity.this, nxt_valasztas.class);
                startActivity(intent);
            }
        }

    @Override
    public void onBackPressed() {
    }

    @SuppressLint("StaticFieldLeak")
    public class Nevsor extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();
            /*try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }*/
            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {

                String sql = "select Nev from qualitydb.Alapadatok_ellenorok where 3 = 3";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
                ResultSet eredmeny = statement.getResultSet();

                while (eredmeny.next()) {
                    ellenorok.add(eredmeny.getString(1));
                }
                synchronized(zar_1)
                {
                    zar_1.notify();		// Értesítjük a zar_1-t, hogy mehet
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
            return info;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class Atallas_ellenorzo extends AsyncTask<Void, Void, Map<String, String>> {
        @SuppressLint("WrongThread")
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();
            /*try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }*/
            Looper.prepare();
            try (Connection connection = DriverManager.getConnection(MainActivity.IP, MainActivity.Felhasznalo, MainActivity.Jelszo)) {

                String sql = "SELECT\n" +
                        "veasnxtmonitor.folyamat_tabla.id,\n" +
                        "veasnxtmonitor.folyamat_tabla.user_id,\n" +
                        "veasnxtmonitor.folyamat_tabla.machine_id,\n" +
                        "veasnxtmonitor.folyamat_tabla.allas_id,\n" +
                        "veasnxtmonitor.folyamat_tabla.allas_ok_id,\n" +
                        "veasnxtmonitor.folyamat_tabla.`comment`,\n" +
                        "veasnxtmonitor.folyamat_tabla.start_tstamp,\n" +
                        "veasnxtmonitor.folyamat_tabla.end_tstamp,\n" +
                        "veasnxtmonitor.folyamat_tabla.auto_store,\n" +
                        "veasnxtmonitor.folyamat_tabla.end_date,\n" +
                        "veasnxtmonitor.folyamat_tabla.nxt_job_id,\n" +
                        "veasnxtmonitor.allas_tabla.allas_name,\n" +
                        "veasnxtmonitor.allas_ok_tabla.allas_ok_name\n" +
                        "FROM\n" +
                        "veasnxtmonitor.folyamat_tabla\n" +
                        "INNER JOIN veasnxtmonitor.allas_tabla ON veasnxtmonitor.allas_tabla.id = veasnxtmonitor.folyamat_tabla.allas_id\n" +
                        "INNER JOIN veasnxtmonitor.allas_ok_tabla ON veasnxtmonitor.allas_ok_tabla.id_allas = veasnxtmonitor.folyamat_tabla.allas_id AND veasnxtmonitor.allas_ok_tabla.id = veasnxtmonitor.folyamat_tabla.allas_ok_id\n" +
                        "where ((allas_id = 1 and (allas_ok_id = 3 or allas_ok_id = 10)) or (allas_id = 3 and allas_ok_id = 5))\n" +
                        " and start_tstamp > date_add(now(),interval -130 MINUTE)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
                ResultSet eredmeny = statement.getResultSet();
                if (eredmeny.next()) {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();
                    Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vb.vibrate(2000);
                    System.out.println("Lefutott a szál");
                    runOnUiThread(new Runnable(){
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            try {
                                builder.setMessage("Átállás a következő soron:"+ eredmeny.getString(3))
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //do things
                                            }
                                        });
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    });
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
            return info;
        }
    }
}