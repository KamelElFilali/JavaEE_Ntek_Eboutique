/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author narib
 */
public class BD {
    private static final String identifiant = "root";
    private static final String password = "abc123...";
    private static final String mySqlUrl = "jdbc:mysql://localhost:3308/NTeK?serverTimezone=UTC";
    
    public static Connection ConnexionToBD()
    {
        try {
            //charger le driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //se connecter a la BD
            Connection connexion = DriverManager.getConnection(mySqlUrl, identifiant, password);
            
            //retourner la connection pour l'utiliser apres dans la query
            return connexion;
        }
        catch(ClassNotFoundException | SQLException e )
        {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return null;
    }
    
    public static void CloseConnexionToBD(Connection connexion)
    {
        try {
            connexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}