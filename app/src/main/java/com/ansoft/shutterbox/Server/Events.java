package com.ansoft.shutterbox.Server;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Abinash on 3/24/2016.
 */
public class Events {

    Activity activity;

    public Events(Activity activity) {
        this.activity = activity;
    }

    public static String server_url = "jdbc:mysql://192.168.0.101/shutterbox";
    public static String database_username = "abinash";
    public static String database_password = "password";
    public interface EventsSaveCallBack {
        void OnDone();
    }
    public void SaveComment(final String eventType, final String eventTitle, final String eventDate, final String blobobe, final String blobtwo, final String blobthree ,
                            final String blobfour, final String eventDuration, final String eventDescription,
                            final String eventTheme,
                            final int eventmaxpart,
                            final int eventcost,
                            final String eventLocation, EventsSaveCallBack cb
                            ){
        final EventsSaveCallBack callBack=cb;
        class BGTASK extends AsyncTask<Void, Void, Void> {


            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    Statement st = conn.createStatement();

                    Log.e("Save photo err", "Saving...");
                    String sql=(new StringBuilder()).append("INSERT INTO events (eventtype, eventtitle, eventdate, eventimgone, eventimgtwo, " +
                            "eventimgthree, eventimgfour, eventduration, eventdescription, eventtheme, eventmaxpart, " +
                            "eventcost, eventlocation) VALUES ('").append(eventType).append("',").append("'").
                            append(eventTitle).append("',").append("'").
                            append(eventDate).append("',").append("'").
                            append(blobobe).append("',").append("'").
                            append(blobtwo).append("',").append("'").
                            append(blobthree).append("',").append("'").
                            append(blobfour).append("',").append("'").
                            append(eventDuration).append("',").append("'").
                            append(eventDescription).append("',").append("'").
                            append(eventTheme).append("',").append("'").
                            append(""+eventmaxpart+"").append("',").append("'").
                            append(""+eventcost+"").append("',").append("'").
                            append(eventLocation).append("')").toString();
                    st.execute(sql);
                    callBack.OnDone();
                } catch (SQLException e) {
                    Log.e("Save photo err", e.getMessage());
                    callBack.OnDone();
                } catch (Exception e) {
                    callBack.OnDone();
                    Log.e("Save photo err", e.getMessage());
                }
                return null;
            }
        }
        new BGTASK().execute();
    }
}
