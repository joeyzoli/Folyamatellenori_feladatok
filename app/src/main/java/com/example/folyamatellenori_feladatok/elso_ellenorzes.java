package com.example.folyamatellenori_feladatok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class elso_ellenorzes extends AppCompatActivity
{
    String csekk1;String csekk2;String csekk3;String csekk4;String csekk5;String csekk6;
    String csekk7;String csekk8;String csekk9;
    TextView nxt_mezo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elso_ellenorzes);
        nxt_mezo = (TextView) findViewById(R.id.nxt_mezo);
        Intent beillesztes = getIntent();
        String nxt = beillesztes.getStringExtra("Kuldo");
        nxt_mezo.setText(nxt);
    }

    public void ujoldal(View view)
    {
        //elso_ellenorzes.nxt = spinner.getSelectedItem().toString();
        Intent intent = new Intent(elso_ellenorzes.this, masodik_ellenorzes.class);
        intent.putExtra("Kuldo", nxt_mezo.getText().toString() );
        startActivity(intent);
    }

    public void nxt(View view)
    {
        //elso_ellenorzes.nxt = spinner.getSelectedItem().toString();
        Intent intent = new Intent(elso_ellenorzes.this, nxt_valasztas.class);
        startActivity(intent);
    }
}