package com.example.folyamatellenori_feladatok;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

public class elso_ellenorzes extends AppCompatActivity
{
    String csek1= "nem";String csek2= "nem";String csek3= "nem";String csek4= "nem";String csek5= "nem";String csek6= "nem";
    String csek7= "nem";String csek8= "nem";String csek9= "nem";
    TextView nxt_mezo;
    RadioButton gomb1; RadioButton gomb2;RadioButton gomb3;RadioButton gomb4;RadioButton gomb5;
    RadioButton gomb6;RadioButton gomb7;RadioButton gomb8;RadioButton gomb9;
    AlertDialog.Builder builder;
    Button tovabb;
    TextView nev;
    int csekkolva = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elso_ellenorzes);
        nxt_mezo = (TextView) findViewById(R.id.nxt_mezo);
        Intent beillesztes = getIntent();
        String nxt = beillesztes.getStringExtra("Kuldo");
        nxt_mezo.setText(nxt);
        nxt_mezo.setTextColor(Color.BLUE);
        nev = findViewById(R.id.nev3_mezo);
        nev.setText(MainActivity.Nev);
        letrehoz_mezok();
        tovabb = (Button) findViewById(R.id.tovabb_gomb);
        tovabb.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          ellenorzes_elsocsekk();
                                      }
                                  });
        gomb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gomb1.isSelected()) {
                    gomb1.setChecked(true);
                    gomb1.setSelected(true);
                } else {
                    gomb1.setChecked(false);
                    gomb1.setSelected(false);
                }
            }
        });
        gomb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gomb2.isSelected()) {
                    gomb2.setChecked(true);
                    gomb2.setSelected(true);
                } else {
                    gomb2.setChecked(false);
                    gomb2.setSelected(false);
                }
            }
        });
        gomb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gomb3.isSelected()) {
                    gomb3.setChecked(true);
                    gomb3.setSelected(true);
                } else {
                    gomb3.setChecked(false);
                    gomb3.setSelected(false);
                }
            }
        });
        gomb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gomb4.isSelected()) {
                    gomb4.setChecked(true);
                    gomb4.setSelected(true);
                } else {
                    gomb4.setChecked(false);
                    gomb4.setSelected(false);
                }
            }
        });
        gomb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gomb5.isSelected()) {
                    gomb5.setChecked(true);
                    gomb5.setSelected(true);
                } else {
                    gomb5.setChecked(false);
                    gomb5.setSelected(false);
                }
            }
        });
        gomb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gomb6.isSelected()) {
                    gomb6.setChecked(true);
                    gomb6.setSelected(true);
                } else {
                    gomb6.setChecked(false);
                    gomb6.setSelected(false);
                }
            }
        });
        gomb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gomb7.isSelected()) {
                    gomb7.setChecked(true);
                    gomb7.setSelected(true);
                } else {
                    gomb7.setChecked(false);
                    gomb7.setSelected(false);
                }
            }
        });
        gomb8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gomb8.isSelected()) {
                    gomb8.setChecked(true);
                    gomb8.setSelected(true);
                } else {
                    gomb8.setChecked(false);
                    gomb8.setSelected(false);
                }
            }
        });
        gomb9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gomb9.isSelected()) {
                    gomb9.setChecked(true);
                    gomb9.setSelected(true);
                } else {
                    gomb9.setChecked(false);
                    gomb9.setSelected(false);
                }
            }
        });
    }

    public void ujoldal(View view)
    {
        ellenorzes_elsocsekk();
    }

    public void nxt(View view)
    {

        Intent intent = new Intent(elso_ellenorzes.this, nxt_valasztas.class);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    public class Csekk1 extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();

            if(gomb1.isChecked()) {
                csek1 = "igen";
                csekkolva++;
            }
            if(gomb2.isChecked()) {
                csek2 = "igen";
                csekkolva++;
            }
            if(gomb3.isChecked()) {
                csek3 = "igen";
                csekkolva++;
            }
            if(gomb4.isChecked()) {
                csek4 = "igen";
                csekkolva++;
            }
            if(gomb5.isChecked()) {
                csek5 = "igen";
                csekkolva++;
            }
            if(gomb6.isChecked()) {
                csek6 = "igen";
                csekkolva++;
            }
            if(gomb7.isChecked()) {
                csek7 = "igen";
                csekkolva++;
            }
            if(gomb8.isChecked()) {
                csek8 = "igen";
                csekkolva++;
            }
            if(gomb9.isChecked()) {
                csek9 = "igen";
                csekkolva++;
            }

            try (Connection connection = DriverManager.getConnection(MainActivity.URL, MainActivity.USER, MainActivity.PASSWORD)) {
                String sql = "UPDATE qualitydb.Folyamatellenori_alap set Csekk1 = '" + csek1 +
                        "', Csekk2 = '" + csek2 + "', Csekk3 = '" + csek3 + "', Csekk4 = '" + csek4 + "', Csekk5 = '" + csek5 +
                        "', Csekk6 = '" + csek6 + "', Csekk7 = '" + csek7 + "', Csekk8 = '" + csek8 + "', Csekk9 = '" + csek9 +
                        "' where Ellenor = '" + MainActivity.Nev + "' and NXT = '" + nxt_mezo.getText().toString() + "' and Datum = '" + MainActivity.Datum + "'";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.executeUpdate();
                Intent intent = new Intent(elso_ellenorzes.this, Cikk_valaszto.class);
                intent.putExtra("Kuldo", nxt_mezo.getText().toString() );
                startActivity(intent);
            }
            catch (Exception e) {
                System.out.println(e);
            }
            return info;
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }

    private void ellenorzes_elsocsekk() {

        if(gomb1.isChecked()) {
            csek1 = "igen";
            csekkolva++;
        }
        if(gomb2.isChecked()) {
            csek2 = "igen";
            csekkolva++;
        }
        if(gomb3.isChecked()) {
            csek3 = "igen";
            csekkolva++;
        }
        if(gomb4.isChecked()) {
            csek4 = "igen";
            csekkolva++;
        }
        if(gomb5.isChecked()) {
            csek5 = "igen";
            csekkolva++;
        }
        if(gomb6.isChecked()) {
            csek6 = "igen";
            csekkolva++;
        }
        if(gomb7.isChecked()) {
            csek7 = "igen";
            csekkolva++;
        }
        if(gomb8.isChecked()) {
            csek8 = "igen";
            csekkolva++;
        }
        if(gomb9.isChecked()) {
            csek9 = "igen";
            csekkolva++;
        }
        if (csekkolva < 9) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            new Csekk1().execute();

                        case DialogInterface.BUTTON_NEGATIVE:
                            Intent intent = new Intent(elso_ellenorzes.this, elso_ellenorzes.class);
                            intent.putExtra("Kuldo", nxt_mezo.getText().toString() );
                            startActivity(intent);
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Biztos tovább mész? \n Amit nem pipáltál ki ott NOK eredmény keletkezik!").setPositiveButton("Igen", dialogClickListener)
                    .setNegativeButton("Nem", dialogClickListener).show();
        }
    }

    private void letrehoz_mezok() {
        gomb1 = (RadioButton)findViewById(R.id.csekk1);gomb2 = (RadioButton)findViewById(R.id.csekk2);gomb3 = (RadioButton)findViewById(R.id.csekk3);
        gomb4 = (RadioButton)findViewById(R.id.csekk4);gomb5 = (RadioButton)findViewById(R.id.csekk5);gomb6 = (RadioButton)findViewById(R.id.csekk6);
        gomb7 = (RadioButton)findViewById(R.id.csekk7);gomb8 = (RadioButton)findViewById(R.id.csekk8);gomb9 = (RadioButton)findViewById(R.id.csekk9);
    }
}