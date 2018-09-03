// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.ansoft.shutterbox.Util;

import android.app.Activity;
import android.graphics.Typeface;

public class Font
{

    Activity ac;

    public Font(Activity activity)
    {
        ac = activity;
    }

    public Typeface getFont()
    {
        return Typeface.createFromAsset(ac.getAssets(), "fonts/Arial MT Std Light.otf");
    }
}
