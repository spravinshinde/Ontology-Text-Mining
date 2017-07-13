/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package otmm;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author admin
 */
public class DBConnection 
{
    Statement stt;
    DBConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://127.0.0.1:3306/otmm";
            Connection con=DriverManager.getConnection(url,"root","");
            stt=con.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }    
}
