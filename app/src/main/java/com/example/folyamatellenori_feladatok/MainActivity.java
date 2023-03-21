package com.example.folyamatellenori_feladatok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    EditText datum;
    EditText ellenor;
    EditText instruktor;
    EditText muvez;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
    Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        File letezik = new File("\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Vevői proglove.csv");
        if(letezik.exists())
        {
            System.out.println("Létezik, látja***************************************************************");
        }
        else
        {
            System.out.println("nem létezik, nem látja********************************************************");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datum = findViewById(R.id.datum_mezo);
        datum.setText(formatter.format(date), TextView.BufferType.EDITABLE);

    }

    public void ujoldal(View view)
    {
        System.out.println("Lefutott*************************************************************************************");
        Intent intent = new Intent(MainActivity.this, nxt_valasztas.class);
        startActivity(intent);
        alap_mentes();
    }

    private void alap_mentes()
    {
        datum = findViewById(R.id.datum_mezo);
        ellenor = findViewById(R.id.nev_mezo);
        instruktor = findViewById(R.id.instruktor_mezo);
        muvez = findViewById(R.id.muvez_mezo);
        DB_iras iras = new DB_iras();
        iras.iro_ellenor_alap(datum.getText(), ellenor.getText(), instruktor.getText(), muvez.getText());
    }
}