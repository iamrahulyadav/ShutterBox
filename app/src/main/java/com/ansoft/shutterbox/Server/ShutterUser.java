package com.ansoft.shutterbox.Server;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.ansoft.shutterbox.Data.AppData;
import com.ansoft.shutterbox.Data.InboxData;
import com.ansoft.shutterbox.Data.ListItemData;
import com.ansoft.shutterbox.R;
import com.ansoft.shutterbox.Util.AlertDialogs.LoadingAlertDialog;
import com.ansoft.shutterbox.Util.AlertDialogs.PurchaseAlertDialog;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ShutterUser {

    String email;
    String firstname;
    String lastname;
    String password;
    String phonenumber;
    String EmailVerified;
    String gender;
    String birthday;
    String birthmonth;
    String birthyear;
    String nationality;
    String mycredits;
    public static String IMAGES_URL = "http://166.62.10.142/Shutterbox/EventImages";
    public static String server_url = "jdbc:mysql://166.62.10.142/Shutterbox";
    public static String database_username = "abinash";
    public static String database_password = "password";

    Activity activity;

    public ShutterUser(Activity activity) {
        this.activity = activity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmailVerified() {
        return EmailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        EmailVerified = emailVerified;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthmonth() {
        return birthmonth;
    }

    public void setBirthmonth(String birthmonth) {
        this.birthmonth = birthmonth;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMycredits() {
        return mycredits;
    }

    public void setMycredits(String mycredits) {
        this.mycredits = mycredits;
    }

    public interface RegsisterCallback {

        void onDone(String result);
    }

    public interface LoginCallBack {
        void OnDone(String result);
    }

    public interface LogOutCallBack {
        void OnDone();
    }

    public interface PhotoSaveCallBack {
        void OnDone();
    }

    public interface CommentCallBack {
        void OnDone();
    }

    public void SaveComment(final String cname, final String cemail, final String cphnumber, final String cbody, final CommentCallBack cb) {
        final CommentCallBack callBack = cb;
        class BGTASK extends AsyncTask<Void, Void, Void> {


            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    Statement st = conn.createStatement();
                    String sql = "INSERT INTO comments (email, phonenumber, name, commentbody) VALUES ('" + cemail + "','" + cphnumber + "','" + cname + "','" + cbody + "')";
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

    public void SavePhotoInBackGround(final String blobPhoto, final PhotoSaveCallBack cb) {
        final PhotoSaveCallBack callBack = cb;
        class BGTASK extends AsyncTask<Void, Void, Void> {


            @Override
            protected Void doInBackground(Void... params) {
                String email = new CurrentUser(activity).getEmail();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    Statement st = conn.createStatement();
                    String sql = "UPDATE users SET profileimage='" + blobPhoto + "' WHERE email='" + email + "';";
                    st.execute(sql);
                    CurrentUser user = new CurrentUser(activity);
                    user.setProfilePhoto(blobPhoto);
                    cb.OnDone();
                } catch (SQLException e) {
                    Log.e("Save photo err", e.getMessage());
                    cb.OnDone();
                } catch (Exception e) {
                    cb.OnDone();
                    Log.e("Save photo err", e.getMessage());
                }
                return null;
            }
        }
        new BGTASK().execute();

    }

    public void logOutInBackGround(LogOutCallBack cb) {
        final LogOutCallBack callback = cb;
        CurrentUser user = new CurrentUser(activity);
        user.SaveUserInfo("", "", "" + "", "", "", 0, 0, 0, 0, "", "");

        CurrentUser user2 = new CurrentUser(activity);
        user2.logout();

        CurrentUser user3 = new CurrentUser(activity);
        user3.setProfilePhoto("");
        callback.OnDone();


    }


    public void incrementCredit(final int incrementedCredit) {
        class BGTASK extends AsyncTask<Void, Void, Void> {


            @Override
            protected Void doInBackground(Void... params) {
                String email = new CurrentUser(activity).getEmail();
                int newCredit = new CurrentUser(activity).getMycredits() + incrementedCredit;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    Statement st = conn.createStatement();
                    String sql = "UPDATE users SET mycredit=" + newCredit + " WHERE email='" + email + "';";
                    st.execute(sql);
                    CurrentUser user = new CurrentUser(activity);
                    user.setCredits(newCredit);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        new BGTASK().execute();
    }

    public void saveFullName(final String fullname) {
        class BGTASK extends AsyncTask<Void, Void, Void> {


            @Override
            protected Void doInBackground(Void... params) {
                String email = new CurrentUser(activity).getEmail();
                try {
                    Class.forName("com.mysql.jdbc.Driver");

                    Log.e("Save name", "Connecting to a selected database...");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);

                    Log.e("Save name", "Connected to database...");
                    Statement st = conn.createStatement();
                    String sql = "UPDATE users SET firstname='" + fullname + "' WHERE email='" + email + "';";
                    st.execute(sql);
                    Log.e("Save name", "updated");
                    CurrentUser user = new CurrentUser(activity);
                    user.setFullname(fullname);
                } catch (SQLException e) {
                    Log.e("Save name err", e.getMessage());
                } catch (Exception e) {
                    Log.e("Save name err", e.getMessage());
                }
                return null;
            }
        }
        new BGTASK().execute();
    }

    public void saveEmailField(final String newemail) {
        class BGTASK extends AsyncTask<Void, Void, Void> {


            @Override
            protected Void doInBackground(Void... params) {
                String email = new CurrentUser(activity).getEmail();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    Statement st = conn.createStatement();
                    String sql = "UPDATE users SET email='" + newemail + "' WHERE email='" + email + "';";
                    st.execute(sql);

                    CurrentUser user = new CurrentUser(activity);
                    user.setEmail(newemail);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        new BGTASK().execute();
    }

    public void savePhonenumberField(final String phnumber) {
        class BGTASK extends AsyncTask<Void, Void, Void> {


            @Override
            protected Void doInBackground(Void... params) {
                String email = new CurrentUser(activity).getEmail();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    Statement st = conn.createStatement();
                    String sql = "UPDATE users SET phonenumber='" + phnumber + "' WHERE email='" + email + "';";
                    st.execute(sql);

                    CurrentUser user = new CurrentUser(activity);
                    user.setPhoneNumber(phnumber);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        new BGTASK().execute();
    }

    public void savePasswordField(final String password) {
        class BGTASK extends AsyncTask<Void, Void, Void> {


            @Override
            protected Void doInBackground(Void... params) {
                String email = new CurrentUser(activity).getEmail();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    Statement st = conn.createStatement();
                    String sql = "UPDATE users SET password='" + password + "' WHERE email='" + email + "';";
                    st.execute(sql);
                    CurrentUser user = new CurrentUser(activity);
                    user.setPassword(password);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        new BGTASK().execute();
    }

    public void login(LoginCallBack lb) {
        final LoginCallBack callback = lb;
        class BGTASK extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Log.e("LOGIN ERROR", "Connecting to a selected database...");
                    System.out.println("Connecting to a selected database...");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    System.out.println("Connected database successfully...");

                    Log.e("LOGIN ERROR", "Connected database successfully...");
                    Statement st = conn.createStatement();
                    Log.e("LOGIN ERROR", "Fetching records without condition...");

                    System.out.println("Fetching records without condition...");
                    String sql = "SELECT password FROM users WHERE email='" + email + "';";
                    ResultSet rs = st.executeQuery(sql);
                    String pw = "";
                    while (rs.next()) {
                        pw = rs.getString("password");
                        Log.e("LOGIN ERROR", "Credential..." + pw);
                    }
                    if (!pw.equalsIgnoreCase(password)) {
                        return ServerConfig.LOGIN_INCORRECT_CREDENTIAL;
                    } else {
                        String sql2 = "SELECT firstname, userid, email, gender, nationality, birthday, birthmonth, birthyear, phonenumber, mycredit, profileimage FROM users WHERE email='" + email + "';";
                        ResultSet rs2 = st.executeQuery(sql2);
                        String firstname = "", email = "", gender = "", nationality = "", birthday = "", birthmonth = "", birthyear = "", phonenum = "";
                        String profileimg = "";
                        int userID = 0, myCredits = 0;
                        while (rs2.next()) {
                            firstname = rs2.getString("firstname");
                            userID = rs2.getInt("userid");
                            email = rs2.getString("email");
                            gender = rs2.getString("gender");
                            nationality = rs2.getString("nationality");
                            birthday = rs2.getString("birthday");
                            birthmonth = rs2.getString("birthmonth");
                            birthyear = rs2.getString("birthyear");
                            phonenum = rs2.getString("phonenumber");
                            myCredits = rs2.getInt("mycredit");
                            profileimg = rs2.getString("profileimage");
                            Log.e("LOGIN ERROR", "Credential" + firstname + userID + email + gender + nationality + birthday + phonenum);
                        }
                        CurrentUser user = new CurrentUser(activity);
                        user.SaveUserInfo(firstname, email, userID + "", gender, nationality, myCredits, Integer.parseInt(birthyear), Integer.parseInt(birthmonth), Integer.parseInt(birthday), phonenum, password);
                        CurrentUser user2 = new CurrentUser(activity);
                        user2.login();

                        CurrentUser user3 = new CurrentUser(activity);
                        user3.setProfilePhoto(profileimg);
                        return ServerConfig.LOGIN_SUCCESS;
                    }

                } catch (SQLException e) {
                    Log.e("LOGIN ERROR", e.getMessage());
                    return ServerConfig.LOGIN_INCORRECT_CREDENTIAL;
                } catch (Exception e) {
                    Log.e("LOGIN ERROR", e.getMessage());
                    return ServerConfig.LOGIN_INCORRECT_CREDENTIAL;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                callback.OnDone(s);
            }

        }
        new BGTASK().execute();
    }
    public interface forgotPassword{
        void onDone(String response);
    }

    public void forgotpassword(forgotPassword lb) {
        final forgotPassword callback = lb;
        class BGTASK extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Log.e("LOGIN ERROR", "Connecting to a selected database...");
                    System.out.println("Connecting to a selected database...");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    System.out.println("Connected database successfully...");

                    Log.e("LOGIN ERROR", "Connected database successfully...");
                    Statement st = conn.createStatement();
                    Log.e("LOGIN ERROR", "Fetching records without condition...");

                    System.out.println("Fetching records without condition...");
                    String sql = "SELECT password FROM users WHERE email='" + email + "';";
                    ResultSet rs = st.executeQuery(sql);
                    String pw = "";
                    while (rs.next()) {
                        pw = rs.getString("password");
                        Log.e("LOGIN ERROR", "Credential..." + pw);
                    }

                    return pw;

                } catch (SQLException e) {
                    Log.e("LOGIN ERROR", e.getMessage());
                    return "NE";
                } catch (Exception e) {
                    Log.e("LOGIN ERROR", e.getMessage());
                    return "NE";
                }
            }

            @Override
            protected void onPostExecute(String s) {
                callback.onDone(s);
            }

        }
        new BGTASK().execute();
    }

    public void register(RegsisterCallback cb) {
        final RegsisterCallback callback = cb;

        class BGTASK extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    Statement st = conn.createStatement();

                    Bitmap photoBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_sample_profile);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photoBitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.
                    byte[] byte_arr = stream.toByteArray();
                    String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);


                    String sqlstmt = (new StringBuilder()).append("INSERT INTO users(email, firstname, lastname, password, phonenumber, emailverified, gender, birthday, birthmonth, birthyear, nationality, mycredit, profileimage)VALUES ('")
                            .append(email).append("',").append("'").
                                    append(firstname).append("',").append(" '").
                                    append(lastname).append("',").append(" '").
                                    append(password).append("',").append(" '").
                                    append(phonenumber).append("',").append(" '").
                                    append(EmailVerified).append("',").append(" '").
                                    append(gender).append("',").append(" '").
                                    append(birthday).append("',").append(" '").
                                    append(birthmonth).append("', ").append("'").
                                    append(birthyear).append("',").append(" '").
                                    append(nationality).append("',").append(" '").
                                    append("0").append("',").append(" '").
                                    append(image_str).append("')").toString();
                    st.execute(sqlstmt);
                    String userId = "";
                    String postReg = "SELECT userId FROM users WHERE email = '" + email + "';";
                    ResultSet rs = st.executeQuery(postReg);
                    while (rs.next()) {
                        userId = "" + rs.getInt("userId");
                    }
                    CurrentUser user = new CurrentUser(activity);
                    user.login();
                    user.SaveUserInfo(firstname, email, userId, gender, nationality, 100, Integer.parseInt(birthyear), Integer.parseInt(birthmonth), Integer.parseInt(birthday), phonenumber, password);

                    return ServerConfig.REGISTER_FEEDBACK_SUCCESS;
                } catch (SQLException se) {
                    Log.e("ERR1", se.getMessage() + "");
                    if (se.getErrorCode() == 1062) {
                        return ServerConfig.ERR_DUPLICATE_EMAIL;
                    }
                    se.printStackTrace();
                } catch (Exception e) {
                    Log.e("ERR2", e.getMessage());
                    e.printStackTrace();
                }
                return null;


            }

            @Override
            protected void onPostExecute(String s) {
                callback.onDone(s);
            }
        }
        new BGTASK().execute();


    }

    public interface getEventsCallback {
        void onDone();
    }

    public void getEventsInBackground(final getEventsCallback cb) {
        final getEventsCallback callback = cb;
        class BGTASK extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Log.e("Getting Events", "Connecting to a selected database...");
                    System.out.println("Connecting to a selected database...");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    System.out.println("Connected database successfully...");

                    Log.e("Getting Events", "Connected database successfully...");
                    Statement st = conn.createStatement();
                    Log.e("Getting Events", "Fetching records without condition...");

                    System.out.println("Fetching records without condition...");
                    String sql = "SELECT * FROM events;";
                    ResultSet rs = st.executeQuery(sql);

                    ArrayList<ListItemData> data;
                    data = new ArrayList<>();
                    while (rs.next()) {
                        int eventid = rs.getInt("eventid");

                        ListItemData dt1 = new ListItemData();
                        dt1.setEventLocation(rs.getString("eventlocation"));
                        String[] images = new String[4];
                        images[0] = IMAGES_URL + "/" + eventid + "/img1.png";
                        images[1] = IMAGES_URL + "/" + eventid + "/img2.png";
                        images[2] = IMAGES_URL + "/" + eventid + "/img3.png";
                        images[3] = IMAGES_URL + "/" + eventid + "/img4.png";
                        Log.e("LOGIN ERROR", images[0]);
                        dt1.setImagesLink(images);
                        dt1.setEventType(rs.getString("eventtype"));
                        dt1.setEventTheme(rs.getString("eventtheme"));
                        dt1.setEventName(rs.getString("eventtitle"));
                        dt1.setEventCost(rs.getInt("eventcost"));
                        dt1.setEventDate(rs.getString("eventdate"));
                        dt1.setEventDescription(rs.getString("eventdescription"));
                        dt1.setEventDuration(rs.getString("eventduration"));
                        dt1.setEventMaxPart(rs.getInt("eventmaxpart"));
                        data.add(dt1);
                    }
                    AppData.setLists(data);
                    callback.onDone();

                } catch (SQLException e) {
                } catch (Exception e) {
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                callback.onDone();
            }
        }
        new BGTASK().execute();


    }

    public interface getMsgCallback {
        void onDone();
    }

    public void getMessagesInBackground(final getMsgCallback cb) {
        final getMsgCallback callback = cb;
        class BGTASK extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    String currentUserId = new CurrentUser(activity).getUserid();
                    Class.forName("com.mysql.jdbc.Driver");
                    Log.e("Getting Msgs", "Connecting to a selected database...");
                    System.out.println("Connecting to a selected database...");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    System.out.println("Connected database successfully...");

                    Log.e("Getting Msgs", "Connected database successfully...");
                    Statement st = conn.createStatement();
                    Log.e("Getting Msgs", "Fetching records without condition...");

                    System.out.println("Fetching records without condition...");
                    String sql = "SELECT * FROM message where toid=" + "'" + currentUserId + "' OR toid='all';";
                    ResultSet rs = st.executeQuery(sql);

                    ArrayList<InboxData> data;
                    data = new ArrayList<>();
                    while (rs.next()) {
                        Log.e("Getting Msgs", rs.getString("toid"));
                        InboxData dt1 = new InboxData();
                        Log.e("MSG ID", "" + rs.getInt("msdid"));
                        dt1.setMsgId("" + rs.getInt("msdid"));
                        if (rs.getInt("read") == 0) {
                            dt1.setIsRead(false);
                        } else {
                            dt1.setIsRead(true);
                        }
                        dt1.setMsgTitle(rs.getString("msgtitle"));
                        Log.e("Getting Msgs", rs.getString("msgtitle"));
                        dt1.setMsgBody(rs.getString("msgbody"));
                        if (rs.getString("msgtype").equalsIgnoreCase("news")) {
                            dt1.setIsTransaction(false);
                        } else {
                            dt1.setIsTransaction(true);
                        }
                        data.add(dt1);

                        AppData.setInboxdata(data);
                    }
                    callback.onDone();

                } catch (SQLException e) {
                    Log.e("Getting Msgs", e.getMessage());
                } catch (Exception e) {
                    Log.e("Getting Msgs", e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                callback.onDone();
            }
        }
        new BGTASK().execute();


    }

    public void setRead(final String id) {
        class BGTASK extends AsyncTask<Void, Void, Void> {


            @Override
            protected Void doInBackground(Void... params) {
                try {

                    Log.e("Getting Msgs", "Setting read...");
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    Statement st = conn.createStatement();
                    String sql = "UPDATE message SET read='"+1+"' WHERE msdid='" + Integer.parseInt(id) + "';";
                    st.execute(sql);
                } catch (SQLException e) {
                    Log.e("Setting read...", e.getMessage());
                    e.printStackTrace();
                } catch (Exception e) {
                    Log.e("Setting read...", e.getMessage());
                    e.printStackTrace();
                }
                return null;
            }
        }
        new BGTASK().execute();
    }

    public interface notificationcallback {
        void onGet(int x);
    }

    public void getNotification(final notificationcallback cb) {
        class BGTASK extends AsyncTask<Void, Void, Integer> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected Integer doInBackground(Void... params) {
                try {

                    String currentUserId = new CurrentUser(activity).getUserid();
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    Statement st = conn.createStatement();
                    String sql = "SELECT * FROM message WHERE toid=" + "'" + currentUserId + "' OR toid='all';";
                    int totalunread = 0;
                    Log.e("Notification", totalunread + "");
                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
                        int onoff = rs.getInt("read");
                        Log.e("Notification", onoff + "");
                        if (onoff == 0) {
                            totalunread += 1;
                        }
                    }

                    Log.e("Notification", totalunread + "");
                    return totalunread;

                } catch (SQLException e) {
                    Log.e("Notification", e.getMessage());
                } catch (Exception e) {
                    Log.e("Notification", e.getMessage());
                }
                return 0;
            }

            @Override
            protected void onPostExecute(Integer x) {
                cb.onGet(x);
            }
        }
        new BGTASK().execute();
    }

    public void requestCredit(final int credit) {
        class BGTASK extends AsyncTask<Void, Void, Void> {

            final LoadingAlertDialog alert = new LoadingAlertDialog(activity, "Requesting Credit ...");

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                alert.show();

            }

            @Override
            protected Void doInBackground(Void... params) {
                CurrentUser us = new CurrentUser(activity);
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(server_url, database_username, database_password);
                    Statement st = conn.createStatement();
                    String sql = "INSERT INTO topuprequests (userid, email, requestedcredit) VALUES ('" + us.getUserid() + "','" + us.getEmail() + "','" + credit + "')";
                    st.execute(sql);

                } catch (SQLException e) {
                    Log.e("Save photo err", e.getMessage());
                } catch (Exception e) {
                    Log.e("Save photo err", e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                alert.dismiss();
                final PurchaseAlertDialog alertDialog = new PurchaseAlertDialog(activity, "Thank you", "Your credit will be\n" +
                        "reflected in your account\n" +
                        "after approval", new PurchaseAlertDialog.OkClickCallBack() {
                    @Override
                    public void onBtnClick() {

                    }
                });
                alertDialog.show();
            }
        }
        new BGTASK().execute();
    }


}
