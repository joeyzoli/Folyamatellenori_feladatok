package com.example.folyamatellenori_feladatok;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    static final String URL = "jdbc:mysql://172.20.22.29?autoReconnect=true&useSSL=false";
    static final String USER = "veasquality";
    static final String PASSWORD = "kg6T$kd14TWbs9&gd";
    static int id;
    static String Nev;
    static String Datum;
    static String Instruktor;
    static String Muvez;
    EditText datum;
    EditText ellenor;
    EditText instruktor;
    EditText muvez;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
    Date date = new Date();
    static ArrayList<String[]> muszak = new ArrayList<>();
    ArrayList<String> ellenorok = new ArrayList<>();
    String[] de = {"6:00-6:30","6:30-7:00","7:00-7:30","7:30-8:00","8:00-8:30","8:30-9:00","9:00-9:30","9:30-10:00","10:00-10:30","10:30-11:00","11:00-11:30","11:30-12:00","12:00-12:30","12:30-13:00","13:00-13:30","13:30-14:00"};
    String[] du = {"14:00-14:30","14:30-15:00","15:00-15:30","15:30-16:00","16:00-16:30","16:30-17:00","17:00-17:30","17:30-18:00","18:00-18:30","18:30-19:00","19:00-19:30","19:30-20:00","20:00-20:30","20:30-21:00","21:00-21:30","21:30-22:00"};
    String[] ej = {"22:00-22:30","22:30-23:00","23:00-23:30","23:30-0:00","0:00-0:30","0:30-1:00","1:00-1:30","1:30-2:00","2:00-2:30","2:30-3:00","3:00-3:30","3:30-4:00","4:00-4:30","4:30-5:00","5:00-5:30","5:30-6:00"};
    RadioButton De;
    RadioButton Du;
    RadioButton Ej;
    private int muszakell = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datum = findViewById(R.id.datum_mezo);
        datum.setText(formatter.format(date), TextView.BufferType.EDITABLE);
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

    private void uzenet(String hibaszoveg){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(hibaszoveg);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                return;
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void ellenorzes() {
        ellenor = findViewById(R.id.nev_mezo);
        String neve = ellenor.getText().toString();
        muszak();
        if(neve.equals("")) {
            uzenet("Nem adtál meg nevet!!");
        }
        else {
            if (muszakell < 1) {
                uzenet("Nem választottál ki műszakot!!");
            }
            else {
                datum = findViewById(R.id.datum_mezo);
                ellenor = findViewById(R.id.nev_mezo);
                instruktor = findViewById(R.id.instruktor_mezo);
                muvez = findViewById(R.id.muvez_mezo);
                Nev = ellenor.getText().toString();
                Datum = datum.getText().toString();
                Instruktor = instruktor.getText().toString();
                Muvez = muvez.getText().toString();
                Intent intent = new Intent(MainActivity.this, nxt_valasztas.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }

}