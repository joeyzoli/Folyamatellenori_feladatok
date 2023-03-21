package com.example.folyamatellenori_feladatok;

import android.os.StrictMode;
import android.text.Editable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_iras
{
    void iro_ellenor_alap(Editable datum, Editable ellenor, Editable instruktor, Editable muvez)
    {
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        Statement stmt = null;
        System.out.println("Osztály meghívva***************************************************");
        try
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();								//Driver meghívása
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");							//kapcsolat létrehozása
            stmt = conn.createStatement();																									//csatlakozás
            String query1 = "INSERT INTO qualitydb.Folyamatellenori_alap (Datum, Ellenor, Instruktor, Muvez) VALUES('2023.03.21', 'Szilvi', 'Mitya', 'Joey')" ;		//String ami magát az SQL utasítást tartalmazza
            stmt.executeUpdate(query1);																													//sql utasítás végrehajtása
            System.out.println("Lefutott");
        }
        catch (SQLException e1) 													//kivétel esetén történik
        {
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            String hibauzenet2 = e.toString();
        }
        finally 																	//finally rész mindenképpen lefut, hogy hiba esetén is lezárja a kacsolatot
        {
            try
            {
                if (stmt != null)
                    conn.close();
            }
            catch (SQLException se) {}
            try
            {
                if (conn != null)
                    conn.close();
            }
            catch (SQLException se)
            {
                se.printStackTrace();
            }
        }
    }
}
