package com.example.folyamatellenori_feladatok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Hiba_leiras extends AppCompatActivity {

    TextView nev;
    TextView nxt_mezo6;
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
    }

    public void ujoldal_nxt2(View view)
    {
        Intent intent = new Intent(Hiba_leiras.this, nxt_valasztas.class);
        startActivity(intent);
    }
}