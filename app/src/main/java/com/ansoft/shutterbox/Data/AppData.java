package com.ansoft.shutterbox.Data;

import java.util.ArrayList;


public class AppData {

    public static int totalNotification;

    public static int getTotalNotification() {
        return totalNotification;
    }

    public static void setTotalNotification(int totalNotification) {
        AppData.totalNotification = totalNotification;
    }

    public static ArrayList<ListItemData> lists;

    public static ArrayList<ListItemData> getLists() {
        return lists;
    }

    public static void setLists(ArrayList<ListItemData> lists) {
        AppData.lists = lists;
    }

    public static ArrayList<InboxData> inboxdata;

    public static ArrayList<InboxData> getInboxdata() {
        return inboxdata;
    }

    public static void setInboxdata(ArrayList<InboxData> inboxdata) {
        AppData.inboxdata = inboxdata;
    }
}
