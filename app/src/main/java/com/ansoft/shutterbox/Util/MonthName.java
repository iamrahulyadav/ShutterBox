// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.ansoft.shutterbox.Util;


public class MonthName
{

    public MonthName()
    {
    }

    public String getName(int i)
    {
        switch (i)
        {
        default:
            return "";

        case 1: // '\001'
            return "Jan";

        case 2: // '\002'
            return "Feb";

        case 3: // '\003'
            return "Mar";

        case 4: // '\004'
            return "Apr";

        case 5: // '\005'
            return "May";

        case 6: // '\006'
            return "Jun";

        case 7: // '\007'
            return "Jul";

        case 8: // '\b'
            return "Aug";

        case 9: // '\t'
            return "Sep";

        case 10: // '\n'
            return "Oct";

        case 11: // '\013'
            return "Nov";

        case 12: // '\f'
            return "Dec";
        }
    }
}
