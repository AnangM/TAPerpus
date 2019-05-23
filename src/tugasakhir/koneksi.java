/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author AnangM
 */
public class koneksi {
    Connection Con;
    void openDB() {
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Con = DriverManager.getConnection("jdbc:mysql:"+"//localhost/dbakhir","aoki","anangaja");
        System.out.println("Connected to Database");
        }
        catch(ClassNotFoundException e){
            System.out.print("Driver Not Found");
        } catch (Exception e) {
        System.out.println(e);
    }
    }
}
