package com.example.folyamatellenori_feladatok;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Cikk_valaszto extends AppCompatActivity
{
    Spinner spinner;
    TextView nxt_mezo3;
    TextView nev;
    EditText ujcikk;
    static ArrayList<String[]> nxt = new ArrayList<>();
    ArrayList<String> valasztott_nxt = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    private static Object zar_2 = new Object();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cikk_valaszto);
        nxt_mezo3 = findViewById(R.id.nxt_mezo3);
        Intent beillesztes = getIntent();
        String nxt = beillesztes.getStringExtra("Kuldo");
        nxt_mezo3.setText(nxt);
        nxt_mezo3.setTextColor(Color.BLUE);
        nev = findViewById(R.id.nev_mezo);
        nev.setText(MainActivity.Nev);
        melyik_nxt();
        spinner = findViewById(R.id.cikk_box);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, valasztott_nxt);  //simple_spinner_item
        spinner.setAdapter(arrayAdapter);
        ujcikk = findViewById(R.id.ujcikk_mezo);
        new Cikkszam().execute();
        synchronized(zar_2)
        {
            try
            {
                zar_2.wait();			// Várakozunk a jelzésre
            }
            catch (InterruptedException e)
            {
                System.out.print(e);
            }
        }
    }

    public void ujoldal_kezdes(View view)
    {
        if (ujcikk.getText().toString().equals("")) {
            uzenet("Nem adtál meg cikkszámot");
        }
        else {
            ujcikk = findViewById(R.id.ujcikk_mezo);
            String[] kontener = {nxt_mezo3.getText().toString() ,ujcikk.getText().toString()};
            nxt.add(kontener);
            Intent intent = new Intent(Cikk_valaszto.this, masodik_ellenorzes.class);
            intent.putExtra("Kuldo", nxt_mezo3.getText().toString() );
            intent.putExtra("Cikkszam", ujcikk.getText().toString() );
            startActivity(intent);
        }
    }

    public void nxt_valsztas(View view)
    {
        Intent intent = new Intent(Cikk_valaszto.this, nxt_valasztas.class);
        startActivity(intent);
    }

    public void ujoldal_folytatas(View view)
    {
        if (valasztott_nxt.size() < 1) {
            uzenet("Nincs cikkszám kiválasztva!!");
        }
        else {
            Intent intent = new Intent(Cikk_valaszto.this, masodik_ellenorzes.class);
            intent.putExtra("Kuldo", nxt_mezo3.getText().toString());
            intent.putExtra("Cikkszam", spinner.getSelectedItem().toString());
            startActivity(intent);
        }
    }

    private void melyik_nxt()
    {
        valasztott_nxt.clear();
        for(int szamlalo = 0; szamlalo < nxt.size(); szamlalo++)
        {
            if(nxt.get(szamlalo)[0].equals(nxt_mezo3.getText().toString()))
            {
                valasztott_nxt.add(nxt.get(szamlalo)[1]);
            }
        }
    }

    private void uzenet(String hibaszoveg){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(hibaszoveg);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Ok", (arg0, arg1) -> {
            return;
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @SuppressLint("StaticFieldLeak")
    public class Cikkszam extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();
            String url = "jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD";
            String userpass = "ZKOVACS";
            try
            {
                //step1 load the driver class
                //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.driver.OracleDriver");  //.driver
                Connection con = DriverManager.getConnection(url, userpass, userpass);
                Statement stmt = con.createStatement();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                Date date = new Date();

                ResultSet rs = stmt.executeQuery("select PART_NO,\n"
                        + "        manuf_date\n"
                        + "from ifsapp.C_OPER_TRACY_OVW \n"
                        + "where 3=3 \n"
                        + "and SCAN_LOC = '"+ nxt_mezo3.getText().toString() +"'\n"
                        + "and MANUF_DATE between to_date( '"+ formatter.format(date) +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ formatter.format(date) +"235959', 'YYYYMMDDHH24:MI:SS' )\n"
                        + "order by manuf_date DESC \n"
                        + "FETCH FIRST 1 ROWS ONLY");
                while(rs.next())
                {
                    System.out.println(rs.getString(1));
                    ujcikk.setText(rs.getString(1));
                }

                synchronized(zar_2)
                {
                    zar_2.notify();		// Értesítjük a zar_2-t, hogy mehet
                }
                con.close();
            }
            catch (Exception e) {
                System.out.println(e);
                Log.e("tag", e.toString());
                Toast.makeText(getApplicationContext(),"Hiba történt! \n Újratöltődik az oldal",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Cikk_valaszto.this, Cikk_valaszto.class);
                intent.putExtra("Kuldo", nxt_mezo3.getText().toString() );
                startActivity(intent);
            }
            return info;
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }

}