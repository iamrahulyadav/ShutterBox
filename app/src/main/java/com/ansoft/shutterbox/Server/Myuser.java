package com.ansoft.shutterbox.Server;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Abinash on 4/19/2016.
 */
public class Myuser {

    public static String server_url = "jdbc:mysql://166.62.10.142/Shutterbox";
    public static String database_username = "abinash";
    public static String database_password = "password";

    public String email, firstname, lastname, password, phonenumber;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public interface SignupCallBack {
        void OnDone();
    }

    public void Signup(final SignupCallBack callback){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
            Statement st = conn.createStatement();
            String sql = "INSERT INTO users (email, firstname, lastname, password, phonenumber) VALUES ('" + email + "','" + firstname + "','" + lastname + "','" + password + "','"+phonenumber+"' )";
            st.execute(sql);
            callback.OnDone();
        } catch (SQLException e) {
            callback.OnDone();
        } catch (Exception e) {
            callback.OnDone();
        }
    }


}
