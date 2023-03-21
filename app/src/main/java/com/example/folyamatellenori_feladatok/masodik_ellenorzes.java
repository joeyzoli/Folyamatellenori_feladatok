package com.example.folyamatellenori_feladatok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class masodik_ellenorzes extends AppCompatActivity
{

    TextView nxt_mezo;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masodik_ellenorzes);
        nxt_mezo = (TextView) findViewById(R.id.nxt_mezo);
        Intent beillesztes = getIntent();
        String nxt = beillesztes.getStringExtra("Kuldo");
        nxt_mezo.setText(nxt);
    }

    public void nxt(View view)
    {
        //elso_ellenorzes.nxt = spinner.getSelectedItem().toString();
        Intent intent = new Intent(masodik_ellenorzes.this, nxt_valasztas.class);
        startActivity(intent);
    }
}