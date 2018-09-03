// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.ansoft.shutterbox.DB;


public interface IDBTaskListener
{

    public abstract void onDoInBackground();

    public abstract void onPostExcute();

    public abstract void onPreExcute();
}
