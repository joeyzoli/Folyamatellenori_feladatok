import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_iras
{
    void iro_gyartas()
    {
        Connection conn = null;
        Statement stmt = null;
        try
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");								//Driver meghívása
            }
            catch (Exception e)
            {
                System.out.println(e);
                String hibauzenet2 = e.toString();
            }

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");							//kapcsolat létrehozása
            stmt = (Statement) conn.createStatement();																									//csatlakozás
            String query1 = "INSERT INTO qualitydb.Gyartasi_adatok";		//String ami magát az SQL utasítást tartalmazza
            stmt.executeUpdate(query1);																													//sql utasítás végrehajtása
            //+ szam +",
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
