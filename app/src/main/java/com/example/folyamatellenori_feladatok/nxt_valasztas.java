package com.example.folyamatellenori_feladatok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class nxt_valasztas extends AppCompatActivity
{
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nxt_valasztas);
        spinner = (Spinner)findViewById(R.id.nxt_box);
    }

    public void ujoldal(View view)
    {
        //elso_ellenorzes.nxt = spinner.getSelectedItem().toString();
        Intent intent = new Intent(nxt_valasztas.this, elso_ellenorzes.class);
        intent.putExtra("Kuldo", spinner.getSelectedItem().toString() );
        startActivity(intent);
    }

    public void ujoldal2(View view)
    {
        //elso_ellenorzes.nxt = spinner.getSelectedItem().toString();
        Intent intent = new Intent(nxt_valasztas.this, MainActivity.class);
        startActivity(intent);
    }


}