// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.ansoft.shutterbox.DB;

import android.os.AsyncTask;

// Referenced classes of package DB:
//            IDBTaskListener

public class DBTask extends AsyncTask
{
    private DBTask dbt;
    private IDBTaskListener mDownloadListener;
    public DBTask(IDBTaskListener idbtasklistener)
    {

        mDownloadListener = idbtasklistener;
        dbt=new DBTask(mDownloadListener);
    }

    @Override
    protected Object doInBackground(Object[] params) {
        if (mDownloadListener != null)
        {
            mDownloadListener.onDoInBackground();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        if (mDownloadListener != null)
        {
            mDownloadListener.onPreExcute();
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        if (mDownloadListener != null)
        {
            mDownloadListener.onPostExcute();
        }
    }
}
