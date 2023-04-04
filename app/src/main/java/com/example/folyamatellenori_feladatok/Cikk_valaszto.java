package com.example.folyamatellenori_feladatok;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Cikk_valaszto extends AppCompatActivity
{
    Spinner spinner;
    TextView nxt_mezo3;
    EditText ujcikk;
    static ArrayList<String[]> nxt = new ArrayList<>();
    ArrayList<String> valasztott_nxt = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
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
        melyik_nxt();
        spinner = findViewById(R.id.cikk_box);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, valasztott_nxt);
        spinner.setAdapter(arrayAdapter);
    }

    public void ujoldal_kezdes(View view)
    {
        ujcikk = findViewById(R.id.ujcikk_mezo);
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

    @Override
    public void onBackPressed() {
        return;
    }

}