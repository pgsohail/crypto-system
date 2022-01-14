/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package securityz;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class SigninCheck {
     public int ValidAll(String userName,String password,String confirmPassword){
         int i=0;
         
         if(userName.equals("")){
            i= 1;  
         }
         else if(password.equals(""))
            i=2;
         else if(password.equals(confirmPassword))
            i= 4;
         else
            i= 3;
        
         System.out.println(i);
        return i;
     }
     public int validUserName(String userName){
           int i=0;
         try{ 
           
             Class.forName("com.mysql.jdbc.Driver");
             Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/security-z","root","");
             System.out.println("lakshay10");
             Statement stmt=con.createStatement();
             ResultSet rs =stmt.executeQuery("select * from signin_info");
             while(rs.next()){
                 System.out.println(rs.getString(1));
                 if(userName.equals(rs.getString(2)))
                     return 0;
                   else
                     i=1;
            }
          
             
         }
         catch(Exception e){
           System.out.println(e.getMessage()+""+e.getStackTrace()+""+e);
            
         }
         return i;
     }
}
